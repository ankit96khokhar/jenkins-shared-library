def call(String username) {

  def apiUrl = "https://api.github.com/users/${username}/repos?per_page=100"

  def output = sh(
    script: """
      curl -s -H "Authorization: token \$GITHUB_API_TOKEN" \
        ${apiUrl} \
      | grep '"name"' \
      | cut -d '"' -f4
    """,
    returnStdout: true
  ).trim()

  // 🔥 CRITICAL: Active Choice MUST return non-empty list
  if (!output) {
    return ["NO_REPOS_FOUND"]
  }

  def repos = output.split("\\n")

  // Extra safety
  return repos.size() > 0 ? repos : ["NO_REPOS_FOUND"]
}
