import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 12.10.2016.
 */
public class LoginPage extends Page {
    private String baseUrl;
    private WebDriver driver;
    private Logger logger;


    public static LoginPage openLoginPage(){
        LoginPage loginPage = new LoginPage();
        loginPage.logger = LogManager.getLogger("Logger " + loginPage.getClass());
        loginPage.logger.debug("Opening login page");

        Context context = Context.getContext();
        loginPage.baseUrl = context.getBaseUrl();
        loginPage.driver = context.getDriver();
        loginPage.driver.get(loginPage.baseUrl);

        loginPage.logger.debug("Login page opened");
        return loginPage;
    }

    public AdminPage doLogin(String login, String pass){
        logger.debug("Logging in with login " + login + "and pass " + pass);


        driver.findElement(By.id("login-username")).clear();
        driver.findElement(By.id("login-username")).sendKeys(login);
        driver.findElement(By.id("login-password")).clear();
        driver.findElement(By.id("login-password")).sendKeys(pass);
        driver.findElement(By.id("login-button")).click();

        AdminPage adminPage = AdminPage.openAdminPage();


        logger.debug("Login done");
        return adminPage;
    }



}
