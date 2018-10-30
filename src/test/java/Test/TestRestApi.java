package Test;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRestApi extends AbstractRestApi {

  private WebDriver driver;

  @Before
  public void iniWebDriver() {
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(BASE_LINE_CNN_SITE);
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
