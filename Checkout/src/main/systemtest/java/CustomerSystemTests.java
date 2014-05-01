package java;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerSystemTests extends AbstractSystemTest {
	
	@Test
	public void googleThatForMe() throws Exception {
		driver.get("http://google.com");
		WebElement searchInput = findElementById("gbqfq");
		searchInput.sendKeys("Like a Régua");
		findElementById("gbqfq");
		findElementById("gbqfb").click();
		Thread.sleep(1000L);
		
		assertTrue( driver.getPageSource().contains("Like a Régua #1 - Brasileirismo - YouTube") );
	}

	
}
