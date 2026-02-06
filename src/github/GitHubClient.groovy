package github

class GitHubClient {

  static List listRepos(String token, String username) {
    def url = "https://api.github.com/users/${username}/repos?per_page=100"
    def cmd = [
      "bash", "-c",
      "curl -s -H \"Authorization: token ${token}\" ${url} | grep '\"name\"' | cut -d '\"' -f4"
    ]

    def p = cmd.execute()
    p.waitFor()

    return p.in.text.trim().split("\n") as List
  }
}
