package selektory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleMainPage {
	private final WebDriver driver;
	
	public GoogleMainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Lokalizacja paska wyszukiwania za pomocą ID
	By googlSearchBarByIdLocator = By.id("lst-ib");
	
	// Lokalizacja paska wyszukiwania za pomocą wyrażenia xpath 
	// (przykład działania, jednak nie zadziała w innej wersji niż PL)
	// można jednak pomyśleć o stworzeniu xpatha, który nie będzie miał tego ograniczenia
	By googleSearchBarByXpathLocator = By.xpath("//div/input[@aria-label='Szukaj']");
	
	// Lokalizacja paska wyszukiwania za pomocą selektora CSS
	By googleSearchBarByCssLocator = By.cssSelector("input.gsfi[type='text']");
	
	// Lokalizacja linku do gmaila za pomocą nazwy
	By googleGmailLinkByLinkTextLocator = By.linkText("Gmail");
	
	// Lokalizacja linku do Grafika za pomocą części nazwy
	By googleGrafikaLinkByPartialTextLocator = By.partialLinkText("Graf");
	
	// Lokalizacja paska wyszukiwania za pomocą atrybutu 'name', ale rzadko stosowana, ponieważ często elementy
	// nie mają tego atrybutu
	By googleSearchBarByNameLocator = By.name("q");
	
	// Lokalizacja przycisku wyświetlającego googleApps za pomocą wartości atrybutu class elementów
	By googleAppsButtonByClassAttributeValueLocator = By.className("gb_Nb");
	
	// Lokalizaca elementu za pomocą nazwy tagu (jednak przy dynamicznych stronach
	// często jest wiele tagów o tej samej nazwie)
	By bodyOfThePageByTagNameLocator = By.tagName("body");
	
	// Poniżej metody obsługujące znalezione elementy
	
	// ID
	public void searchForStringUsingIdLocator(String searchPhrase) {
		driver.findElement(googlSearchBarByIdLocator).sendKeys(searchPhrase);
	}
	
	// Xpath
	public void searchForStringUsingXpathLocator(String searchPhrase) {
		driver.findElement(googleSearchBarByXpathLocator).sendKeys(searchPhrase);
	}
	
	// CssSelector
	public void searchForStringUsingCssSelectorLocator(String searchPhrase) {
		driver.findElement(googleSearchBarByCssLocator).sendKeys(searchPhrase);
	}
	
	// Kliknięcie na link prowadzący do Gmaila
	public void clickGmailLink() {
		driver.findElement(googleGmailLinkByLinkTextLocator).click();
	}
	
	// Kliknięcie na link prowadzący do wyszukiwarki grafiki
	public void clickGrafikaLink() {
		driver.findElement(googleGrafikaLinkByPartialTextLocator).click();
	}
	
	// Wyświetlenie aplikacji google
	public void clickGoogleAppsButton() {
		driver.findElement(googleAppsButtonByClassAttributeValueLocator).click();
	}
	
	// Wyświetlenie całego tesktu w tagu Body
	public void displayBody() {
		System.out.println(driver.findElement(bodyOfThePageByTagNameLocator).getText());
	}
}
