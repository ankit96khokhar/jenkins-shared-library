def call(String username, String token) {
    def cmd = """
        curl -s -H "Authorization: token ${token}" \
                https://api.github.com/users/${username}/repos?per_page=100 \
                | jq -r '.[].name'        
    """

    def output = sh(script: cmd, returnStdout: true).trim()

    return output ? output.split("\n") : []
}