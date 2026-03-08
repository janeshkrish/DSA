const fs = require("fs");
const path = require("path");
const { execSync } = require("child_process");

const ROOT_DIR = __dirname;
const README_PATH = path.join(ROOT_DIR, "README.md");
const JAVA_DIR = path.join(ROOT_DIR, "java");

const BLOCK_START = "<!-- AUTO-GENERATED-STATS:START -->";
const BLOCK_END = "<!-- AUTO-GENERATED-STATS:END -->";

function run(command) {
  try {
    return execSync(command, {
      cwd: ROOT_DIR,
      stdio: ["ignore", "pipe", "ignore"],
      encoding: "utf8",
    }).trim();
  } catch {
    return "";
  }
}

function getRepoSlug() {
  const remote = run("git config --get remote.origin.url");
  const match = remote.match(/github\.com[:/](.+?)\/(.+?)(?:\.git)?$/i);

  if (match) {
    return {
      owner: match[1],
      repo: match[2],
    };
  }

  return {
    owner: "janeshkrish",
    repo: "DSA",
  };
}

function getBranchName() {
  return process.env.GITHUB_REF_NAME || run("git branch --show-current") || "main";
}

function getDifficultyTotals(rootDir) {
  const totals = {
    easy: 0,
    medium: 0,
    hard: 0,
  };

  function walk(dir) {
    const entries = fs.readdirSync(dir, { withFileTypes: true });

    for (const entry of entries) {
      const fullPath = path.join(dir, entry.name);

      if (entry.isDirectory()) {
        walk(fullPath);
        continue;
      }

      if (!entry.isFile() || !entry.name.endsWith(".java")) {
        continue;
      }

      const segments = fullPath.split(path.sep).map((segment) => segment.toLowerCase());

      if (segments.includes("easy")) {
        totals.easy += 1;
      } else if (segments.includes("medium")) {
        totals.medium += 1;
      } else if (segments.includes("hard")) {
        totals.hard += 1;
      }
    }
  }

  walk(rootDir);
  return totals;
}

function escapeRegex(value) {
  return value.replace(/[.*+?^${}()|[\]\\]/g, "\\$&");
}

function buildStatsBlock(stats, repo, branch) {
  const owner = encodeURIComponent(repo.owner);
  const repoName = encodeURIComponent(repo.repo);
  const branchName = encodeURIComponent(branch);

  return `${BLOCK_START}
<p align="center">
  <img src="https://img.shields.io/badge/Total_Solved-${stats.total}-00F7FF?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Easy-${stats.easy}-brightgreen?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Medium-${stats.medium}-yellow?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Hard-${stats.hard}-red?style=for-the-badge" />
</p>

<p align="center">
  <img src="https://img.shields.io/github/repo-size/${owner}/${repoName}?style=for-the-badge" />
  <img src="https://img.shields.io/github/last-commit/${owner}/${repoName}/${branchName}?style=for-the-badge&label=Last%20Commit" />
  <img src="https://img.shields.io/github/languages/top/${owner}/${repoName}?style=for-the-badge&label=Top%20Language" />
</p>
${BLOCK_END}`;
}

function updateReadme(block) {
  const current = fs.readFileSync(README_PATH, "utf8");
  const pattern = new RegExp(`${escapeRegex(BLOCK_START)}[\\s\\S]*?${escapeRegex(BLOCK_END)}`);

  if (!pattern.test(current)) {
    throw new Error(
      `Could not find stats block markers in README.md. Add:\n${BLOCK_START}\n...\n${BLOCK_END}`
    );
  }

  const updated = current.replace(pattern, block);
  fs.writeFileSync(README_PATH, updated.endsWith("\n") ? updated : `${updated}\n`, "utf8");
}

if (!fs.existsSync(JAVA_DIR)) {
  throw new Error(`Java directory not found: ${JAVA_DIR}`);
}

const totals = getDifficultyTotals(JAVA_DIR);
const stats = {
  easy: totals.easy,
  medium: totals.medium,
  hard: totals.hard,
  total: totals.easy + totals.medium + totals.hard,
};

const repo = getRepoSlug();
const branch = getBranchName();
const statsBlock = buildStatsBlock(stats, repo, branch);

updateReadme(statsBlock);

console.log(
  `README stats updated: total=${stats.total}, easy=${stats.easy}, medium=${stats.medium}, hard=${stats.hard}`
);
