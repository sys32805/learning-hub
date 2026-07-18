# How to add learning notes (anytime)

Your site updates when you **push to GitHub**. Phone/laptop can then open the same URL.

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

Then open `http://localhost:3000`.
