package selektory;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jUnitTests {
	WebDriver driver;
	GoogleMainPage googleMainPage;
	
	// Przed każdym testem poniższe instrukcje są wykonywane
	@Before
	public void setUp() {	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
	
	// Szukanie frazy 'test by ID' w google i znalezienie paska wyszukiwania za pomocą ID
	@Test
	public void searchForPhraseID() {
		googleMainPage.searchForStringUsingIdLocator("test by ID");
	}
	
	// Szukanie frazy 'test by Xpath' w google i znalezienie paska wyszukiwania za pomocą Xpath
	@Test
	public void searchForPhraseXpath() {
		googleMainPage.searchForStringUsingXpathLocator("test by Xpath");
	}
	
	// Szukanie frazy 'test by CssSelector' w google i znalezienie paska wyszukiwania za pomocą CssSelector
	@Test
	public void searchForPhraseCss() {
		googleMainPage.searchForStringUsingCssSelectorLocator("test by CssSelector");
	}
	
	// Kliknięcie na link Gmail
	@Test
	public void clickGmailLink() {
		googleMainPage.clickGmailLink();
	}
	
	// Kliknięcie na link prowadzący do wyszukiwarki grafiki
	@Test
	public void clickGrafikaLink() {
		googleMainPage.clickGrafikaLink();
	}
	
	// Kliknięcie na przycisk wyświetlający googleApps
	@Test
	public void clickGoogleAppsButton() {
		googleMainPage.clickGoogleAppsButton();
	}
	
	// Wyświetla widoczny tekst dla użytkownika w tagu Body
	@Test
	public void displayBody() {
		googleMainPage.displayBody();
	}
}
