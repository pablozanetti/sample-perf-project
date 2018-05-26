package test.scala.plan

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.reflect.runtime.universe

class SimpleTest extends Simulation {

  //def getResponseHeader(response: io.gatling.http.response.Response, headerName: String):String = {
  //  if (response.header(headerName).isEmpty) {return "NA"}
  //  else {response.header(headerName).get}
  //}

  val httpConf =  http.baseURL(System.getProperty("urlBase"))
    .extraInfoExtractor(extraInfo => {
      List[String](extraInfo.request.getUrl())
    })

  val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
  val module = runtimeMirror.staticModule(System.getProperty("request"))
  val obj = runtimeMirror.reflectModule(module)
  val scnObject: test.scala.request.Request = obj.instance.asInstanceOf[test.scala.request.Request]

  val scenarios_actions = scenario("single test")
    //.exec(scnLoginObject.loginScenario())
    .exec(scnObject.scenario())
    //.exec(coreOnJCX.atom.Logout.logout)

  setUp(scenarios_actions.inject(atOnceUsers(1))).protocols(httpConf)

}
