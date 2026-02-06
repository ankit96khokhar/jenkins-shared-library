def call(String username) {
    withCredentials([string(credentialsId: 'github-api-token', variable: 'TOKEN')]) {
        def cmd = """
            curl -s -H "Authorization: token ${TOKEN}" \
            https://api.github.com/users/${username}/repos?per_page=100 \
            | grep '"name"' \
            | cut -d '"' -f4   
        """

        def output = sh(script: cmd, returnStdout: true).trim()

        return output ? output.split("\n") : []
    }
}