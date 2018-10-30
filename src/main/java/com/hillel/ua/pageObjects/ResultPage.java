package com.hillel.ua.pageObjects;

import com.hillel.ua.dto.ResultDTO;
import com.hillel.ua.utils.LocatorsUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultPage extends AbstractPage{
  
  private final String LOCATOR_NEWS=
    LocatorsUtils.getLocatorsInstance().getLocatorValue("resultPage.news");
  private final String TITLE_NEWS=LocatorsUtils.getLocatorsInstance()
    .getLocatorValue("resultPage.title_news");
  private final String BODY_NEWS=LocatorsUtils.getLocatorsInstance()
    .getLocatorValue("resultPage.body_news");
  
  
  @FindBy(tagName = "footer")
  private WebElement footer;
  
  @FindBy(xpath="//label[@for='collection_video']")
  private WebElement videoButton;
  
  
  public ResultPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }
  public void clickOnVideoFilter(){
    videoButton.click();
  }
  public List<ResultDTO> getResultList(){
    System.out.println("Hi Jack");
    return getWebDriver().findElements(By.xpath(LOCATOR_NEWS))
        .stream().map((element) -> {
        ResultDTO ndto=new ResultDTO();
        ndto.setHeadline(element.findElement(By.xpath(TITLE_NEWS)).getText().trim());
        ndto.setBody(element.findElement(By.xpath(BODY_NEWS)).getText().trim());
        return ndto;}).collect(Collectors.toList());
  }
  public List<ResultDTO>getRefreshResultList(){
    getWebDriverWait(10).until(
      ExpectedConditions.refreshed(ExpectedConditions
        .stalenessOf(getWebDriver().findElement(By.xpath(LOCATOR_NEWS)))));
    return getResultList();
  }
}
