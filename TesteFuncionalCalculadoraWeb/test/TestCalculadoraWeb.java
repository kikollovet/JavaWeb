

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCalculadoraWeb {
    
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    //System.setProperty("webdriver.gecko.driver", "c:/mozilladriver/geckodriver.exe");
    //driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", "c:/chromedriver/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testECalculadoraDivisao() throws Exception {
    driver.get(baseUrl + "/CalculadoraWeb/");
    driver.findElement(By.name("v1")).clear();
    driver.findElement(By.name("v1")).sendKeys("10");
    new Select(driver.findElement(By.id("to"))).selectByVisibleText("/");
    driver.findElement(By.name("v2")).clear();
    driver.findElement(By.name("v2")).sendKeys("2");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "O resultado da divisão é 5.0");
  }
  
  @Test
  public void testECalculadoraWebSoma() throws Exception {
    driver.get(baseUrl + "/CalculadoraWeb/");
    driver.findElement(By.name("v1")).clear();
    driver.findElement(By.name("v1")).sendKeys("10");
    new Select(driver.findElement(By.id("to"))).selectByVisibleText("+");
    driver.findElement(By.name("v2")).clear();
    driver.findElement(By.name("v2")).sendKeys("2");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "O resultado da soma é 12.0");
  }
  
  @Test
  public void testECalculadoraWebSubtracao() throws Exception {
    driver.get(baseUrl + "/CalculadoraWeb/");
    driver.findElement(By.name("v1")).clear();
    driver.findElement(By.name("v1")).sendKeys("10");
    new Select(driver.findElement(By.id("to"))).selectByVisibleText("-");
    driver.findElement(By.name("v2")).clear();
    driver.findElement(By.name("v2")).sendKeys("2");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "O resultado da subtração é 8.0");
  }
  
  @Test
  public void testECalculadoraWebMultiplicacao() throws Exception {
    driver.get(baseUrl + "/CalculadoraWeb/");
    driver.findElement(By.name("v1")).clear();
    driver.findElement(By.name("v1")).sendKeys("10");
    new Select(driver.findElement(By.id("to"))).selectByVisibleText("*");
    driver.findElement(By.name("v2")).clear();
    driver.findElement(By.name("v2")).sendKeys("2");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "O resultado da multiplicação é 20.0");
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
