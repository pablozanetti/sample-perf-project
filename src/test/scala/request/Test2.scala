package test.scala.request

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Test2 extends Request {

  def scenario(): io.gatling.core.structure.ChainBuilder = exec(http("test2")
    .get("/")
    .check(status.is(200))
    .check(bodyString.saveAs("getBodyString")))
    .exec((session:Session) => {
      val getBodyString1 = session("getBodyString").asOption[String].get
      System.out.println("@@@@@@@>>>: " + getBodyString1)
      session
    })


}
