import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	// step 1
	DesiredCapabilities caps = new DesiredCapabilities();

	AndroidDriver driver;

	@BeforeTest
	public void myBeforeTest() throws MalformedURLException {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "aboood");

//		caps.setCapability("chromedriverExecutable",
//				"C:\\Users\\User\\OneDrive\\Desktop\\myDriverrrrrr\\chromedriver.exe");
//					caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");

		File CalculatorApp = new File("src/myApplications/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, CalculatorApp.getAbsolutePath());

	}

	@Test()
	public void ClickonAllDigits() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		List<WebElement> myButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < myButtons.size(); i++) {
			if (myButtons.get(i).getAttribute("resource-id").contains("2")
					|| myButtons.get(i).getAttribute("resource-id").contains("4")
					|| myButtons.get(i).getAttribute("resource-id").contains("6")
					|| myButtons.get(i).getAttribute("resource-id").contains("8")) {
				myButtons.get(i).click();
			}
		}
		Assertion myAssert = new Assertion();

		String ExpectedValue = "8462";
		String Actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		myAssert.assertEquals(Actual, ExpectedValue);

	}

	@AfterTest()
	public void AftermyTest() {
	}

}
