package ExplicitWait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.GoogleMainPage;

public class ExplicitWaitTests {
	WebDriver driver;
	WebDriverWait wait;
	GoogleMainPage googleMainPage;
	
	// Przed każdym testem poniższe instrukcje są wykonywane
	@Before
	public void setUp() {	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://google.pl");
		
		googleMainPage = new GoogleMainPage(driver);
	}
	
	// Po każdym teście poniższe instrukcje są wykonywane, czeka 3 sekundy przez zamknięciem przeglądarki, aby można było
	// zobaczyć efekt
	@After
	public void cleanUp() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// Do nothing
		}
		driver.quit();
	}
		
	// Szukanie frazy 'test by ID' w google i znalezienie paska wyszukiwania za pomocą ID NO WAIT
	@Test
	public void searchForPhraseID() {
		googleMainPage.searchForStringUsingIdLocator("test by ID");
	}
	
	// Szukanie frazy 'test by Xpath' w google i znalezienie paska wyszukiwania za pomocą Xpath z explicit wait
	@Test
	public void searchForPhraseXpath() {
		explicitWaitElementClickable(driver, googleMainPage.googlSearchBarByIdLocator, 2);
		googleMainPage.searchForStringUsingXpathLocator("test by Xpath");
	}
	
	
	// Metoda, która czeka przez określony, aż dany element będzie "klikalny" 
	private boolean explicitWaitElementClickable(WebDriver driver, By byLocator, int duration) {
		
		// Zmienna którą będzie zwracać metoda
		boolean isClickable = false;
		
		// Zmienna, która przechowuje obietk WebElement, gdy go znajdzie
		WebElement webElement = null;
		
		// Czas oczekiwania na element
		wait = new WebDriverWait(driver, duration);
		
		// Gdy znajdzie weblement i określi go jako 'klikalny' nadpisana zostanie wartość null
		webElement = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
		
		// Jeśli zmienna webElement nie jest pusta zmień wartość boolean na true
		if (webElement.getSize() != null) {
			isClickable = true;
			return isClickable;
		}
		return isClickable;
	}
}
