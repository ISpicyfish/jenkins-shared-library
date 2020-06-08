def call(String chartRepoURL, String chartRepo, String user, String pwd) {
  println "==================== 添加 chart 到仓库 ===================="
  sh "/var/jenkins_home/helm repo add ${chartRepo} http://${chartRepoURL}/chartrepo/${chartRepo} --username=${user} --password=${pwd}"
}