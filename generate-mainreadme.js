const fs = require("fs");
const path = require("path");

const baseDir = "./java";

let easy = 0;
let medium = 0;
let hard = 0;

function countJavaFiles(dir) {
  const files = fs.readdirSync(dir);

  files.forEach(file => {
    const full = path.join(dir, file);
    const stat = fs.statSync(full);

    if (stat.isDirectory()) {
      countJavaFiles(full);
    } else if (file.endsWith(".java")) {
      if (dir.toLowerCase().includes("easy")) easy++;
      else if (dir.toLowerCase().includes("medium")) medium++;
      else if (dir.toLowerCase().includes("hard")) hard++;
    }
  });
}

countJavaFiles(baseDir);

const total = easy + medium + hard;

let readme = fs.readFileSync("README.md", "utf8");

readme = readme
  .replace("{{TOTAL}}", total)
  .replace("{{EASY}}", easy)
  .replace("{{MEDIUM}}", medium)
  .replace("{{HARD}}", hard);

fs.writeFileSync("README.md", readme);

console.log("README updated!");