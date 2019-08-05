import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class base {
		public static  AndroidDriver<AndroidElement> capabilities() throws MalformedURLException
		{
			

		AndroidDriver<AndroidElement>  driver;
		 File appDir = new File("src");
		 File app = new File(appDir, "General-Store.apk");
		
	     DesiredCapabilities capabilities = new DesiredCapabilities();
	     
	     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pooemulator");
	     capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	     driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		    
		 return driver;
		}
}
