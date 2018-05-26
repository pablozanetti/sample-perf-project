package test.scala.request

trait Request {
  def scenario(): io.gatling.core.structure.ChainBuilder
}
