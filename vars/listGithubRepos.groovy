def call(String username) {
  withCredentials([string(credentialsId: 'github-api-token', variable: 'TOKEN')]) {

    def apiUrl = "https://api.github.com/users/${username}/repos?per_page=100"

    def output = sh(
      script: """
        curl -s -H "Authorization: token \$TOKEN" \
          ${apiUrl} \
        | grep '"name"' \
        | cut -d '"' -f4
      """,
      returnStdout: true
    ).trim()

    return output ? output.split("\n") : []
  }
}
