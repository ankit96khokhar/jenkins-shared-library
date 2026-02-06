def call(String owner, String repo) {

  def apiUrl = "https://api.github.com/repos/${owner}/${repo}/branches?per_page=100"

  def output = sh(
    script: """
      curl -i -s -H "Authorization: token \$GITHUB_API_TOKEN" \
        ${apiUrl}
    """,
    returnStdout: true
  ).trim()

  echo "RAW GITHUB RESPONSE:"
  echo output

  return ["DEBUG_MODE"]
}
