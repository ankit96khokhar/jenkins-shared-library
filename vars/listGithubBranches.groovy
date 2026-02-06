def call(String owner, String repo) {

  def apiUrl = "https://api.github.com/repos/${owner}/${repo}/branches?per_page=100"

  def output = sh(
    script: """
      curl -s -H "Authorization: token \$GITHUB_API_TOKEN" \
        ${apiUrl} \
      | grep '"name"' \
      | cut -d '"' -f4
    """,
    returnStdout: true
  ).trim()

  if (!output) {
    return ["NO_BRANCHES_FOUND"]
  }

  def branches = output.split("\\n")

  return branches.size() > 0 ? branches : ["NO_BRANCHES_FOUND"]
}
