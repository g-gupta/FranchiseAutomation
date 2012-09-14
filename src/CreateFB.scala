/**
 * Created with IntelliJ IDEA.
 * User: ggupta
 * Date: 9/13/12
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import Page.CreateFBPage
import lib.CommonFuncs
import org.scalatest.BeforeAndAfterAll

class CreateFB extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll {
  val commonFuncs = new CommonFuncs()
  val CreateFBPage = new CreateFBPage()

  override def afterAll {
    CreateFBPage.close()
  }


  feature("Facebook Page Creation") {
    info("As a valid user")
    info("I should be able to login to the facebook successfully and Create the Fanpage")

    val start: Int = commonFuncs.ReadConf("fbPageStartCount").toInt
    val end: Int = commonFuncs.ReadConf("fbPageEndCount").toInt
    scenario("Create Facebook Fanpage") {
        given("I am on Facebook site")
        CreateFBPage.navigate()
        when("I enter valid user credentials and click login")
        CreateFBPage.login(commonFuncs.ReadConf("fbuserEmail"), commonFuncs.ReadConf("fbuserPwd"))
        then("I should create FB Page")
      for (itr <- start to end) {
        CreateFBPage.createPage(itr)
      }
    }
  }
}
