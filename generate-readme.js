const fs = require("fs");
const path = require("path");
const { execFileSync } = require("child_process");

const ROOT_DIR = __dirname;
const JAVA_DIR = path.join(ROOT_DIR, "java");
const TARGET_SOLVED = 100;
const EMPTY_PERFORMANCE = "—";

function getJavaFiles(dir) {
  let files = [];
  const entries = fs.readdirSync(dir, { withFileTypes: true });

  for (const entry of entries) {
    const fullPath = path.join(dir, entry.name);

    if (entry.isDirectory()) {
      files = files.concat(getJavaFiles(fullPath));
      continue;
    }

    if (entry.isFile() && entry.name.endsWith(".java")) {
      files.push(fullPath);
    }
  }

  return files;
}

function toPosix(relativePath) {
  return relativePath.split(path.sep).join("/");
}

function runGit(args) {
  try {
    return execFileSync("git", args, {
      cwd: ROOT_DIR,
      stdio: ["ignore", "pipe", "ignore"],
      encoding: "utf8",
    }).trim();
  } catch {
    return "";
  }
}

function getDifficulty(filePath) {
  const parts = filePath.split(path.sep).map(part => part.toLowerCase());

  if (parts.includes("easy")) return "Easy";
  if (parts.includes("medium")) return "Medium";
  if (parts.includes("hard")) return "Hard";

  return "";
}

function formatPerformance(commitMessage) {
  const cleaned = commitMessage.replace(/\r?\n/g, " ").replace(/\s+/g, " ").trim();

  if (!cleaned) {
    return EMPTY_PERFORMANCE;
  }

  const sections = cleaned.split("|").map(value => value.trim()).filter(Boolean);
  const text = sections.length > 1 ? sections.join("<br>") : sections[0];

  return text.replace(/\|/g, "\\|");
}

function getPerformanceForFile(filePath) {
  const relativePath = toPosix(path.relative(ROOT_DIR, filePath));

  // Prefer the commit where file was added. If unavailable, fall back to latest touch.
  const addedCommitMessage = runGit([
    "log",
    "-1",
    "--follow",
    "--diff-filter=A",
    "--format=%s",
    "--",
    relativePath,
  ]);

  const latestCommitMessage =
    addedCommitMessage ||
    runGit(["log", "-1", "--follow", "--format=%s", "--", relativePath]);

  if (!latestCommitMessage) {
    return EMPTY_PERFORMANCE;
  }

  return formatPerformance(latestCommitMessage);
}

if (!fs.existsSync(JAVA_DIR)) {
  throw new Error(`Java directory not found: ${JAVA_DIR}`);
}

const files = getJavaFiles(JAVA_DIR).sort().reverse();

let easyCount = 0;
let mediumCount = 0;
let hardCount = 0;

for (const file of files) {
  const difficulty = getDifficulty(file);

  if (difficulty === "Easy") {
    easyCount += 1;
  } else if (difficulty === "Medium") {
    mediumCount += 1;
  } else if (difficulty === "Hard") {
    hardCount += 1;
  }
}

const totalSolved = easyCount + mediumCount + hardCount;
const progressPercent = Math.floor((totalSolved / TARGET_SOLVED) * 100);
const barLength = 10;
const filled = Math.floor((progressPercent / 100) * barLength);
const empty = barLength - filled;
const progressBar = "█".repeat(filled) + "░".repeat(empty);

let readmeContent = `
# 🧠 Java DSA Learning Tracker

This document records my ongoing journey of practicing and mastering Data Structures and Algorithms problems using Java.

---

## 📊 DSA Overview

<p align="center">

<img src="https://img.shields.io/badge/Total_Solved-${totalSolved}-00F7FF?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Easy-${easyCount}-brightgreen?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Medium-${mediumCount}-yellow?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Hard-${hardCount}-red?style=for-the-badge"/>

</p>

### 📈 Progress

\`\`\`
Progress: ${progressBar} ${progressPercent}%
\`\`\`

---

## 📋 Progress Table

| S.No | Problem | Difficulty | Performance |
|------|----------|------------|-------------|
`;

for (const [index, file] of files.entries()) {
  const cleanName = path.basename(file, ".java");
  const difficulty = getDifficulty(file);
  const performance = getPerformanceForFile(file);

  readmeContent += `| ${index + 1} | ${cleanName} | ${difficulty} | ${performance} |\n`;
}

fs.writeFileSync(path.join(JAVA_DIR, "README.md"), readmeContent, "utf8");

console.log("README generated successfully.");
