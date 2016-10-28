import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by User on 12.10.2016.
 */
public class LoginPage extends Page {
    private String baseUrl;
    private WebDriver driver;
    private Logger logger;

    @FindBy(id = "login-username")
    private WebElement usernameField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;


    public static LoginPage openLoginPage(){
        LoginPage loginPage = new LoginPage();
        loginPage.logger = LogManager.getLogger("Logger " + loginPage.getClass());
        loginPage.logger.debug("Opening login page");

        Context context = Context.getContext();
        loginPage.baseUrl = context.getBaseUrl();
        loginPage.driver = context.getDriver();
        loginPage.driver.get(loginPage.baseUrl);

        PageFactory.initElements(loginPage.driver, loginPage);

        loginPage.logger.debug("Login page opened");
        return loginPage;
    }

    public AdminPage doLogin(String login, String pass){
        logger.debug("Logging in with login " + login + "and pass " + pass);


        usernameField.clear();
        usernameField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(pass);
        loginButton.click();

        AdminPage adminPage = AdminPage.openAdminPage();


        logger.debug("Login done");
        return adminPage;
    }



}
