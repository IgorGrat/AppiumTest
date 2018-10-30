package Test;

import com.hillel.ua.dto.*;
import com.hillel.ua.pageObjects.*;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import com.jayway.restassured.filter.log.*;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.unitils.reflectionassert.ReflectionAssert;



public abstract class AbstractRestApi {
  
  private final String BASE_PARTIAL_API_LINK_CNN="https://search.api.cnn.io";
  private final String CNN_CONTENT = "/content?";
  private final String PARAMS_TO_GET_REQUEST = "%s=%s&";
  private final String SEARCH_CONTEXT="job";
  private final Map<String, String> params = new HashMap();

  {
    params.put("q", SEARCH_CONTEXT);
    params.put("size", "10");
  }
  protected final String BASE_LINE_CNN_SITE = "https://edition.cnn.com";
  

  public AbstractRestApi() {
    RestAssured.useRelaxedHTTPSValidation();
    RestAssured.baseURI=BASE_PARTIAL_API_LINK_CNN;
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    newConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
  }
  public void testSearchCnnSite(WebDriver driver) {
    ScopeNewsDTO dTO = getJSonByClass(params, ScopeNewsDTO.class);
    TitlePage titlePage = new TitlePage(driver);
    titlePage.clickOnSearch();
    ResultPage resultPage = titlePage.inputText(SEARCH_CONTEXT);
    List<ResultDTO> actual = resultPage.getResultList();
    List<ResultDTO> expected = dTO.getResult();
    ReflectionAssert.assertReflectionEquals("There are no equal objects", actual, expected);
  }
  public void testVideoFilterCnnSite(WebDriver driver){
    params.put("type", "video");
    ScopeNewsDTO dTO = getJSonByClass(params, ScopeNewsDTO.class);
    TitlePage titlePage = new TitlePage(driver);
    titlePage.clickOnSearch();
    ResultPage resultPage = titlePage.inputText(SEARCH_CONTEXT);
    resultPage.clickOnVideoFilter();
    List<ResultDTO> actual = resultPage.getRefreshResultList();
    List<ResultDTO> expected = dTO.getResult();
    ReflectionAssert.assertReflectionEquals("There are no equal objecrs", actual, expected);
  }
  public final<T> T getJSonByClass(Map<String, String> params, Class<T> cls) {
    String navigateTo = CNN_CONTENT;
    for (Map.Entry<String, String> items : params.entrySet()) {
      navigateTo = navigateTo.concat(String.format(PARAMS_TO_GET_REQUEST,
        items.getKey(), items.getValue()));
    }
    return RestAssured
      .get(navigateTo)
      .then()
      .statusCode(200)
      .extract().as(cls);
  }
}
