

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class ConversorTest {
    
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
  public void testConversorTemperaturaCelsiusFahrenheit() throws Exception {
    driver.get(baseUrl + "/Conversor/");
    driver.findElement(By.name("temperatura")).clear();
    driver.findElement(By.name("temperatura")).sendKeys("100");
    new Select(driver.findElement(By.name("tipoConversao"))).selectByVisibleText("Celsius para Fahrenheit");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "A temperatura é 212.0F");
  }
  
  @Test
  public void testConversorTemperaturaFahrenheitCelsius() throws Exception {
    driver.get(baseUrl + "/Conversor/");
    driver.findElement(By.name("temperatura")).clear();
    driver.findElement(By.name("temperatura")).sendKeys("212");
    new Select(driver.findElement(By.name("tipoConversao"))).selectByVisibleText("Fahrenheit para Celsius");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "A temperatura é 100.0C");
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
