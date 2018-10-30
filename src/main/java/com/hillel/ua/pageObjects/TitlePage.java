package com.hillel.ua.pageObjects;

import com.hillel.ua.utils.LocatorsUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class TitlePage extends AbstractPage{
  private final WebDriver driver;
  
  static private final String SEARCH_BUTTON=
  LocatorsUtils.getLocatorsInstance().getLocatorValue("titlePage.search.button");
  
  static private final String INPUT_PANEL=
  LocatorsUtils.getLocatorsInstance().getLocatorValue("titlePage.search.input_field");
  
  private final WebElement search;
  
  private WebElement searchPanel;
  
  
  public TitlePage(WebDriver driver) {
    super(driver);
    this.driver=driver;
    search=driver.findElement(By.xpath(SEARCH_BUTTON));
  }
  public ResultPage inputText(String text){
    searchPanel=driver.findElement(By.xpath(INPUT_PANEL));
    WebDriverWait driverWait=getWebDriverWait(4);
    driverWait.until(ExpectedConditions.presenceOfElementLocated(
    By.xpath(INPUT_PANEL)));
    searchPanel.sendKeys(text, Keys.ENTER);
    return new ResultPage(driver);
  } 
  public void clickOnSearch(){
    search.click();
  }
}
