def call(String dir, String chartVersion, Map argMap) {

  println "==================== 更新chart的内容 values.yaml和Chart.yaml ===================="
  def valuesYaml = readYaml file:"${dir}/values.yaml"
  def chartYaml = readYaml file:"${dir}/Chart.yaml"

  echo "==================== values.yaml ===================="
  echo "${valuesYaml}"

  echo "==================== Chart.yaml===================="
  echo "${chartYaml}"

  if (argMap != null) {
    argMap.each { key, value -> 
      def temp = valuesYaml
      def keyArr = key.split('.')
      keyArr.each{ keyItem -> temp = temp[keyItem] }
      temp = value
    }
  }

  chartYaml.version = chartVersion

  // 需要先删除原文件，才能重新覆盖内容
  sh "rm ${dir}/values.yaml"
  sh "rm ${dir}/Chart.yaml"
  
  writeYaml file:"${dir}/values.yaml",data:valuesYaml
  writeYaml file:"${dir}/Chart.yaml",data:chartYaml
}