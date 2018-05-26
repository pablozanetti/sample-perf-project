package test.scala.request

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Test1 extends Request {

  def scenario(): io.gatling.core.structure.ChainBuilder = exec(http("test2")
      .get("/test2")
      .check(status.is(200))
      .check(bodyString.saveAs("getBodyString")))
    .exec((session:Session) => {
      val getBodyString1 = session("getBodyString").asOption[String].get
      //val getBodyString2 = """<rss><channel><title>Yahoo! Weather - Boulder, CO</title><item><title>Conditions for Boulder, CO at 2:54 pm MST</title><forecast day="Thu" date="10 Nov 2011" low="37" high="58" text="Partly Cloudy" code="29" /></item></channel></rss>"""
      val result1 = scala.xml.XML.loadString(getBodyString1)
      //val result2 = scala.xml.XML.loadString(getBodyString2)
      val t1 = (result1 \ "importedOrder" \ "PersubAdvertisementImportedOrder" \ "AgencyId").get(1).text
      //val t2 = result2 \ "channel" \ "item"
      System.out.println("Result1 @@@@@@@>>>: " + result1)
      //System.out.println("Result2 @@@@@@@>>>: " + result2)
      System.out.println("T1 @@@@@@@>>>: " + t1)
      //System.out.println("T2 @@@@@@@>>>: " + t2)
      session
    })

}
