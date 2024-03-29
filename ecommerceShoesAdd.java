import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
import junit.framework.Assert;



public class ecommerceShoesAdd extends base{


	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		AndroidDriver<AndroidElement> driver=capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sonia Gulia");
		driver.hideKeyboard();
		
		driver.findElement(By.xpath("//*[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		
		//Scrolling through the list of country's 
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));");
		driver.findElement(By.xpath("//*[@text='India']")).click();

	    driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	    
	    //add items to the cart
	    
	    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
	    driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
	    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	    
	    Thread.sleep(4000);

	    int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();

	    double sum=0;

		for(int i=0;i<count;i++)
	
		{
	
			String amount1= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
		
			double amount=getAmount(amount1);
		
			sum=sum+amount;//280.97+116.97
	
		}

		System.out.println(sum+"sum of products");


		String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		total= total.substring(1);
		double totalValue=Double.parseDouble(total);
		System.out.println(totalValue+"Total value of products");
		Assert.assertEquals(sum, totalValue); 
	
	
	
		//Mobile Gestures
	
		WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		
		//Long Pause for the terms of conditions.
		WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	
		Thread.sleep(7000);
	
		Set<String> contexts=driver.getContextHandles();
	
		for(String contextName :contexts)
		{
			System.out.println(contextName);
		}
		//Switching to the browser.
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("https://www.finastra.com/");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		//Switching back to the App.
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}

		public static double getAmount(String value)
	
		{
			value= value.substring(1);
			double amount2value=Double.parseDouble(value);
			return amount2value;
		}
	}

