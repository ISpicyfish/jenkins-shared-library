import groovy.json.JsonSlurperClassic

def call(json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}