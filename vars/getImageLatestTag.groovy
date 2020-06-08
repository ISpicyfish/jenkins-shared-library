def call(String hubUrl, String projectName, String repositoryName) {
  def response =  httpRequest contentType: 'APPLICATION_JSON',
      httpMode: "GET",
      url: "http://" + hubUrl + "/api/v2.0/projects/"+ projectName + "/repositories/"+ repositoryName +"/artifacts?page=1&page_size=1"
  if (response != null && 200 == response.status){
    def tagJson = jsonParse(response.content)
    return tagJson[0].tags[0].name
  }
  return null
}