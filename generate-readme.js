const fs = require("fs");
const path = require("path");
const { execSync } = require("child_process");

const javaDir = path.join(__dirname, "java");

// Recursively get all .java files
function getJavaFiles(dir) {
  let results = [];
  const list = fs.readdirSync(dir);

  list.forEach(file => {
    const filePath = path.join(dir, file);
    const stat = fs.statSync(filePath);

    if (stat.isDirectory()) {
      results = results.concat(getJavaFiles(filePath));
    } else if (file.endsWith(".java")) {
      results.push(filePath);
    }
  });

  return results;
}

const files = getJavaFiles(javaDir).sort().reverse();

// Difficulty counters
let easyCount = 0;
let mediumCount = 0;
let hardCount = 0;

files.forEach(file => {
  if (file.includes("Easy")) easyCount++;
  else if (file.includes("Medium")) mediumCount++;
  else if (file.includes("Hard")) hardCount++;
});

const totalSolved = easyCount + mediumCount + hardCount;

// Progress calculation (target = 100 problems, change if needed)
const target = 100;
const progressPercent = Math.floor((totalSolved / target) * 100);

const barLength = 10;
const filled = Math.floor((progressPercent / 100) * barLength);
const empty = barLength - filled;
const progressBar = "█".repeat(filled) + "░".repeat(empty);

// Generate README
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

// Add table rows
files.forEach((file, index) => {
  const filename = path.basename(file);
  const cleanName = filename.replace(".java", "");

  let difficulty = "";
  if (file.includes("Easy")) difficulty = "Easy";
  else if (file.includes("Medium")) difficulty = "Medium";
  else if (file.includes("Hard")) difficulty = "Hard";

  let performance = "—";

  try {
    const commitMsg = execSync(
      `git log -1 --pretty=format:"%s" -- "${file}"`
    )
      .toString()
      .trim();

    const match = commitMsg.match(/Runtime\s*:\s*.*$/i);
    if (match) performance = match[0];
  } catch (err) {
    performance = "—";
  }

  readmeContent += `| ${index + 1} | ${cleanName} | ${difficulty} | ${performance} |\n`;
});

fs.writeFileSync(path.join(javaDir, "README.md"), readmeContent);

console.log("✅ README generated successfully!");