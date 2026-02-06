def call(String username, String repo) {
  withCredentials([string(credentialsId: 'github-api-token', variable: 'TOKEN')]) {

    def apiUrl = "https://api.github.com/repos/${username}/${repo}/branches?per_page=100"

    def output = sh(
      script: """
        curl -s -H "Authorization: token \$TOKEN" \
          ${apiUrl} \
        | grep '"name"' \
        | cut -d '"' -f4
      """,
      returnStdout: true
    ).trim()

    return output ? output.split("\\n") : []
  }
}
