def call(String dir) {
  println "====================  检验 chart 模板 ===================="
  sh "/var/jenkins_home/helm lint ${dir}"
}