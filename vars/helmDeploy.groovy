
def call(String dir, String chartName, String chartVersion, String chartRepo, 
        String user, String pwd, 
        Map argMap,
        String namespace = "default", 
        boolean dry_run = false, 
        String chartRepoURL = "registry.my.com:5000") {
  helmLint(dir)
  helmAddChart2Repo(chartRepoURL, chartRepo, user, pwd)
  helmUpdateChart(dir, chartVersion, argMap)
  helmPush(chartName, chartRepo)
  println "==================== 更新chart仓库 ===================="
  sh "/var/jenkins_home/helm repo update"
  if (dry_run) {
    sh "/var/jenkins_home/helm upgrade --dry-run --debug  --install ${chartName} ${chartRepo}/${chartName} --namespace ${namespace}"
  } else {
    sh "/var/jenkins_home/helm upgrade --install ${chartName} ${chartRepo}/${chartName} --namespace ${namespace}"
    echo "=========== 应用${chartName}部署成功，可以使用 helm status ${chartName}查看应用状态 ==========="
  }
}