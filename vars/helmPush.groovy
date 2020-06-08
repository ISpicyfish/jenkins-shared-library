def call(String chartName, String chartRepo) {
  println "==================== push chart并更新repo ===================="
  // 上传helm包
  sh "/var/jenkins_home/helm push ${chartName} ${chartRepo}"
}