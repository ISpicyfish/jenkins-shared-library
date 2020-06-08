import java.util.regex.Matcher
import java.util.regex.Pattern
def call(String hubUrl, String projectName, String repositoryName, Pattern tagPattern){  
  def response =  httpRequest contentType: 'APPLICATION_JSON',
      httpMode: "GET",
      url: "http://" + hubUrl + "/api/v2.0/projects/"+ projectName + "/repositories/"+ repositoryName +"/artifacts";
  if (response != null && 200 == response.status){
    def tagJson = jsonParse(response.content)
    def tagList = []
    tagJson.each {
      if (it.tags != null && it.tags[0].name != null && tagPattern.matcher(it.tags[0].name).matches()) {
        tagList.add(it.tags[0].name)
      }
    }
    tagList.sort { 
      a, b -> return b.compareTo(a)
    }
    return tagList[0];
  }
  return null
}