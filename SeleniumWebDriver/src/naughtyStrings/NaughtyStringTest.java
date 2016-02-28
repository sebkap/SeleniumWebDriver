package naughtyStrings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NaughtyStringTest {
	WebDriver driver;
	WebDriverWait wait;
	
	By lastPostLocator = By.xpath("//lastPost");
	
	// Przed każdym testem poniższe instrukcje są wykonywane
	@Before
	public void setUp() {	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://google.pl");
	}
	
	@Test
	public void naughtyString() throws Exception {
		
		// Tworzy obiekt URL, adres zawiera wszytkie naugthy strings w formacie tekstowym
		// (za każdym razem w przypadku aktualizacji jest możliwe pobranie nowej listy)
		URL naughtyStringsTxtUrl = new URL("https://raw.githubusercontent.com/minimaxir/big-list-of-naughty-strings/master/blns.txt");
		
		// Poniższe komendy z pętlą umożliwiają sparsowanie otrzymanego pliku tekstowego
		// pętla umożliwia zapisanie wyniku w obiekcie List z pominięciem linii zawierających znak #
		// które stanowią komentarze
		InputStream naughtyStringsStream = naughtyStringsTxtUrl.openStream();
		InputStreamReader naughtyStringStreamReader = new InputStreamReader(naughtyStringsStream);
        BufferedReader bufferedReader = new BufferedReader(naughtyStringStreamReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
        	if (!line.contains("#")) {
        		lines.add(line);
        		// System.out.println(line);
        	}
        }
        bufferedReader.close();
        
        // Nasza pętla testowa, która wprowadzi każdy ciąg znaków z listy, a następnie zweryfikuje, czy w danym
        // elemencie jest on poprawnie wyświetlany
		for (int i = 0; i < lines.size(); i++) {
			WebElement textComment = driver.findElement(By.xpath("//lokatorPolaTekstowego"));
			textComment.sendKeys(lines.get(i));
			WebElement sendButton = driver.findElement(By.xpath("//lokatorPrzyciskuWysylajacegoPost"));
			sendButton.click();
			
			// Oczekiwanie do 5 na wyświetlenie posta w temacie, gdy go nie będzie zostanie zalogowany jaki String
			// był problemem, aby możliwa była późniejsza weryfikacja
			boolean resultIsDisplayed = explicitWaitElementPresent(driver, lastPostLocator, lines.get(i), 5);
			if (!resultIsDisplayed) {
				System.out.println("String not displayed: " + lines.get(i));
			}
		}
        
	}
	
	// Metoda, która czeka przez określony, aż dany element będzie wyświetlony w danym elemencie znalezionym za pomocą
	// lokatora By
	private boolean explicitWaitElementPresent(WebDriver driver, By byLocator, String textToShowUp ,int duration) {
		
		// Zmienna którą będzie zwracać metoda, czyli wynik oczekiwania
		boolean isDisplayed = false;
		
		// Czas oczekiwania na element
		wait = new WebDriverWait(driver, duration);
		
		// Gdy znajdzie weblement i znajduje się się w nim określony tekst zmienna boolean zostanie nadpisana i zwróci wartość true
		// + obsługa wyjątku Timeout, gdy tekst w elemencie np. nie będzie wyświetlony
		try {
			isDisplayed = wait.until(ExpectedConditions.textToBePresentInElementLocated(byLocator, textToShowUp));
		} catch (TimeoutException e) {
			return isDisplayed;
		}
		// zmienna zwracana przez metodą
		return isDisplayed;
	}
}
