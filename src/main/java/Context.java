/**
 * Created by User on 13.10.2016.
 */
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Context {
    private static Context context;
    private static WebDriver driver;
    private static String baseUrl;
    private Logger logger;



    private Context() {
    }

    public static void initInstance() throws Exception{
        context = new Context();
        context.logger = LogManager.getLogger("Logger " + context.getClass());
        context.logger.debug("Init context. Driver location: " + Constants.driverLocation + "; timeout: " + Constants.timeout);
        System.setProperty("webdriver.gecko.driver",Constants.driverLocation);

        driver = new FirefoxDriver();
        baseUrl = Constants.webUrl;
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Constants.timeout, TimeUnit.SECONDS);
        context.logger.debug("Context initiated");
    }

    public static Context getContext(){
        if (context == null) {
            throw new IllegalStateException("Context is not initialized");
        }
        return context;
    }

    public void close(){
        context.logger.debug("Closing driver");
        driver.quit();
        context.logger.debug("Driver closed");

    }

    public String getBaseUrl(){
        return baseUrl;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
