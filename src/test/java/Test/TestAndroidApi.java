package Test;

import io.appium.java_client.android.AndroidDriver;
import java.net.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestAndroidApi extends AbstractRestApi{
  
  private WebDriver driver;
  
  @Before
  public void initAppiumDriver(){
  final DesiredCapabilities caps = new DesiredCapabilities();
      caps.setCapability("deviceName","Lenovo_P70-A");
      caps.setCapability("udid","JVLJLRUOCUFYDQJZ");
      caps.setCapability("platformName","Android");
      caps.setCapability("platformVersion","5.1");
      caps.setCapability("browserName","Chrome");

      System.setProperty("webdriver.chrome.driver","chromedriver.exe");
      try {
      driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),caps);
      driver.get(BASE_LINE_CNN_SITE);
      } catch (MalformedURLException ex) {
      ex.printStackTrace();
      }
  }
  @Test
   public void testSearchCnnSite(){
    super.testSearchCnnSite(driver);
   }
   @Test
   public void testVideoFilterCnnSite(){
    super.testVideoFilterCnnSite(driver);
   }
  @After
  public void closeDriver() {
    driver.close();
  }
  

}
