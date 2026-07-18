# How to add learning notes (anytime)

Your site updates when you **push to GitHub**. Phone/laptop can then open the same URL.

**Site:** https://sys32805.github.io/learning-hub/

## Login (personal study gate)

The site shows a **login screen** before notes. Session lasts until you close the tab (or click Logout).

| | |
|--|--|
| Default username | `learner` |
| Default password | `Learn@2026` |

**Change password after first use:**

1. Create a SHA-256 hash:

```bash
node -e "console.log(require('crypto').createHash('sha256').update('YOUR_NEW_PASSWORD').digest('hex'))"
```

2. Edit [`assets/auth-config.js`](assets/auth-config.js) — set `username` and `passwordSha256`.
3. Push to GitHub.

**Note:** This locks the **website UI**. On a public repo, raw `.md` files on GitHub can still be opened directly. Fine for focused personal study; use a private repo if you need stronger privacy.

## Theme & Study mode

After login, the **toolbar** (always visible under the quick links) has:

| Button | What it does |
|--------|----------------|
| **Study mode** | Hides sidebar + quick links for focused reading. Click **Exit study** to restore. |
| **Light mode / Dark mode** | Theme toggle (also on the login card). |
| **Logout** | Clears session and returns to the login screen. |

- Default theme: **dark teal**. Preferences save in `localStorage`.
- Study mode preference also saves until you turn it off.

## Daily workflow

1. Add or edit a file under the right track (prefer **Markdown** `.md`).
2. Link it from [`_sidebar.md`](_sidebar.md) so it appears in the menu.
3. Commit and push:

```bash
git add .
git commit -m "Add notes on <topic>"
git push
```

4. Wait ~1 minute, refresh the website.

## Where to put what

| You learned… | Put files in… |
|--------------|----------------|
| OOPs / core Java theory | `03-dsa-and-interview/oops/` |
| DSA concept / small demo | `03-dsa-and-interview/concepts/<topic>/` |
| Interview algorithm (Java) | `03-dsa-and-interview/algorithms/` |
| LeetCode solution | `04-leetcode/Leetcode/<problem>/` |
| Spring | `05-spring-boot/` |
| Drupal | `06-drupal/` |

## Tips

- Use `.md` for notes you want to read on the site (best formatting).
- `.java` files are for practice in the IDE; link them from a README if you want them on the site.
- Keep one idea per file; short pages are easier to revise on mobile.
- After adding a new important note, always add one line in `_sidebar.md`.

## Local preview (optional)

```bash
npx --yes docsify-cli serve .
```

Then open `http://localhost:3000`. Local preview still uses paths under `/learning-hub/` for assets — prefer the GitHub Pages URL for the real login + theme experience.
