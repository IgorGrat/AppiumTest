package com.hillel.ua.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
  private final WebDriver driver;

  public AbstractPage(WebDriver driver) {
    this.driver=driver;
  }
  public void navigateTo(String link){
    driver.get(link);
  }
  public WebDriver getWebDriver(){
    return driver;
  }
  public WebDriverWait getWebDriverWait(long timeWait){
    return new WebDriverWait(driver, timeWait);
  }
  public Actions getWebActions(){
    return new Actions(driver);
  }
  

}
