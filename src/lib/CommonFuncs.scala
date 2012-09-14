package lib

/**
 * Created with IntelliJ IDEA.
 * User: ggupta
 * Date: 9/14/12
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
import xml.XML
import org.apache.commons.io.FileUtils
import org.openqa.selenium._
import java.io.File
import org.openqa.selenium.support.ui.{ExpectedCondition, WebDriverWait}

class CommonFuncs {


  def ReadConf(node: String): String = {
    val dom = XML.loadFile("src/config/config-qa.xml")
    var nodeValue = (dom \\ node).text
    if (System.getProperty(node) != null) nodeValue = System.getProperty(node)
    nodeValue
  }

  def CaptureScreenshot(fileName: String, driver: WebDriver) {
    FileUtils.copyFile(
      (driver.asInstanceOf[TakesScreenshot]).getScreenshotAs(OutputType.FILE),
      new File("ScreenCaptures/" + fileName + ".png")
    )
  }

  def WaitUntilElementPresent(timeout: Int, driver: WebDriver, xpath: String){
    val wait = new WebDriverWait(driver, timeout)
    wait.until(new ExpectedCondition[WebElement] {
      def apply(d: WebDriver) = d.findElement(By.xpath(xpath))
    })
  }
}

