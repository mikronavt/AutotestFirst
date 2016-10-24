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
        context.logger.debug("Init context + params");//todo add params
        System.setProperty("webdriver.gecko.driver","C:\\tests\\geckodriver.exe");

        driver = new FirefoxDriver();
        baseUrl = "http://at.pflb.ru/matrixboard2/";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
