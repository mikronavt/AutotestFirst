import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 12.10.2016.
 */
public class LoginPage extends Page {
    private String baseUrl;
    private WebDriver driver;

    public static LoginPage openLoginPage(){
        LoginPage loginPage = new LoginPage();
        Context context = Context.getContext();
        loginPage.baseUrl = context.getBaseUrl();
        loginPage.driver = context.getDriver();

        loginPage.driver.get(loginPage.baseUrl);
        return loginPage;
    }

    public AdminPage doLogin(String login, String pass){
        AdminPage adminPage = new AdminPage();
        adminPage.setDriver(this.driver);
        adminPage.getDriver().findElement(By.id("login-username")).clear();
        adminPage.getDriver().findElement(By.id("login-username")).sendKeys(login);
        adminPage.getDriver().findElement(By.id("login-password")).clear();
        adminPage.getDriver().findElement(By.id("login-password")).sendKeys(pass);
        adminPage.getDriver().findElement(By.id("login-button")).click();
        return adminPage;
    }



}
