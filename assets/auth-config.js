/**
 * Personal login gate for Learning Hub (client-side only).
 *
 * Default account (change after first deploy):
 *   username: learner
 *   password: Learn@2026
 *
 * To set a new password:
 *   1. Run: node -e "console.log(require('crypto').createHash('sha256').update('YOUR_PASSWORD').digest('hex'))"
 *   2. Paste the hex into passwordSha256 below
 *   3. Update username if desired
 *   4. git push
 *
 * Note: GitHub Pages is static — this locks the app UI, not the raw .md files in a public repo.
 */
window.LEARNING_HUB_AUTH = {
  username: 'learner',
  passwordSha256: '1f980223a82e4e617b6cb088d3b20bad45a9aa1e68d988e6061db8a4a40d322a',
  sessionKey: 'learning-hub-auth'
};
