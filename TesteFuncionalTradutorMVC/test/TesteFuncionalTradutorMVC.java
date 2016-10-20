
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteFuncionalTradutorMVC {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "c:/chromedriver/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTradutorPalavraExiste() throws Exception {
    driver.get(baseUrl + "/TradutorMVC/");
    driver.findElement(By.name("palavra")).clear();
    driver.findElement(By.name("palavra")).sendKeys("house");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "A palavra traduzida é casa");
  }
  
  @Test
  public void testTradutorPalavraExiste2() throws Exception {
    driver.get(baseUrl + "/TradutorMVC/");
    driver.findElement(By.name("palavra")).clear();
    driver.findElement(By.name("palavra")).sendKeys("pen");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "A palavra traduzida é caneta");
  }
  
  @Test
  public void testTradutorPalavraNaoExiste() throws Exception {
    driver.get(baseUrl + "/TradutorMVC/");
    driver.findElement(By.name("palavra")).clear();
    driver.findElement(By.name("palavra")).sendKeys("finger");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Nosso banco de dados não tem tradução para a palavra finger");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
