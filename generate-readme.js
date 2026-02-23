const fs = require("fs");
const path = require("path");
const { execSync } = require("child_process");

const javaDir = path.join(__dirname, "java");

function getJavaFiles(dir) {
  let results = [];
  const list = fs.readdirSync(dir);

  list.forEach(file => {
    const filePath = path.join(dir, file);
    const stat = fs.statSync(filePath);

    if (stat && stat.isDirectory()) {
      results = results.concat(getJavaFiles(filePath));
    } else if (file.endsWith(".java")) {
      results.push(filePath);
    }
  });

  return results;
}

const files = getJavaFiles(javaDir).sort().reverse();

let readmeContent = `
# 🧠 Java DSA Learning Tracker

This document records my ongoing journey of practicing and mastering Data Structures and Algorithms problems using Java.

## 📊 Progress Table

| S.No | Problem | Performance |
|------|----------|-------------|
`;

files.forEach((file, index) => {
  const filename = path.basename(file);

  let commitMsg = "";
  try {
    commitMsg = execSync(`git log -1 --pretty=format:"%s" -- "${file}"`)
      .toString()
      .trim();
  } catch (err) {
    commitMsg = "No commit message";
  }

  readmeContent += `| ${index + 1} | ${filename} | ${commitMsg} |\n`;
});

fs.writeFileSync(path.join(javaDir, "README.md"), readmeContent);

console.log("✅ README generated successfully!");