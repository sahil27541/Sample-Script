package more;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Smallcase {

	public static void main(String[] args) {

		// *Please change the chrome driver value before running the script

		// A simple automation flow to showcase my basic Automation skills
		// Setting driver browser environments and invoking chrome browser
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sahil\\OneDrive\\Documents\\SELENIUM\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Here starts the journey of a new age boy who comes across the terms Investing and the magic of Compounding
		// Hearing a lot about it from many leaders, he chooses smallcase so that his investment portfolio is diversified
		driver.get("https://www.smallcase.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// And decides to check all the smallcases available so he can take a mindful decision
		driver.findElement(By.cssSelector(".btn.btn-primary-blue.btn-lg.mr12")).click();
		driver.findElement(By.xpath("(//div[@class='RouteTab__tab__2fTX6'])[2]")).click();

		// Going through the list of smallcases excites him, when he looks at those CAGR numbers
		try {
			while (driver.findElement(By.cssSelector(".LoadMoreButton__container__1agDt span")).isDisplayed()) {
				WebElement loadMore = driver.findElement(By.cssSelector(".LoadMoreButton__container__1agDt span"));
				loadMore.click();
			}
		} catch (Exception e) {
			System.out.println("No more smallcases");
		}

		// But at the same time he gets confused looking at the number of smallcases available
		List<WebElement> allSmallcases = driver.findElements(By.className("AllSmallcases__smallcasecard-link__2A7p_"));
		System.out.println(allSmallcases.size());

		// He calms himself down & being a newbie decides to filter the smallcases so that he has low risk at investing
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[@data-testid='undefined_Free Access']"))).click().build().perform();
		act.moveToElement(driver.findElement(By.xpath("//div[text()='Under ₹ 5,000']"))).click().build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='low']")));
		act.moveToElement(driver.findElement(By.xpath("//div[@class='VolatilityFilter__checkbox-container__3pwlv']"))).click().build().perform();

		// He now runs through the filtered list and chooses one that has low investment and decent returns
		List<WebElement> filteredSmallcases = driver.findElements(By.className("AllSmallcases__smallcasecard-link__2A7p_"));
		System.out.println(filteredSmallcases.size());
		for (int i = 0; i < filteredSmallcases.size(); i++) {
			String smallCASE = filteredSmallcases.get(i).getText();
			String myBuy = "Equity & Gold";
			if (smallCASE.contains(myBuy)) {
				filteredSmallcases.get(i).click();
				break;
			}
		}

		// Tries to learn more about the smallcase, checks the chart and decides to move ahead with it
		driver.findElement(By.xpath("//span[text()='Read more']")).click();
		driver.findElement(By.xpath("(//button[.='Invest Now'])[3]")).click();

		// Finally chooses his trusted broker for the investment
		driver.findElement(By.xpath("//span[text()='Zerodha']")).click();

		// He has been driven to Zerodha's terminal and is anxious about his first investment
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String mainId = it.next();
		String subId = it.next();
		driver.switchTo().window(subId);
		System.out.println(driver.getTitle());

		// He tries logging in to his Zerodha account
		driver.findElement(By.id("userid")).sendKeys("994432");
		driver.findElement(By.id("password")).sendKeys("I think we should STOP now!");
		driver.findElement(By.cssSelector(".button-orange.wide")).click();

		// Wait WHAT! he gets an error and his infinite thinking loop starts over again
		String message = driver.findElement(By.cssSelector(".error.text-center")).getText();
		System.out.println(message);
	}

}
