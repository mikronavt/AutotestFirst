import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 12.10.2016.
 */
public class AddPersonForm {
    String firstName;
    String lastName;
    String username;
    WebDriver driver;
    private Logger logger;

    public static AddPersonForm openAddPersonForm(){
        AddPersonForm addPersonForm = new AddPersonForm();
        addPersonForm.driver = Context.getContext().getDriver();
        addPersonForm.logger = LogManager.getLogger("Logger " + addPersonForm.getClass());
        addPersonForm.logger.debug("Opened add person form");
        return addPersonForm;

    }

    public void setFirstName(String firstName){
        logger.debug("Setting first name " + firstName);
        this.firstName = firstName;
        driver.findElement(By.id("person-first-name")).clear();
        driver.findElement(By.id("person-first-name")).sendKeys(firstName);
        logger.debug("First name set");
    }

    public void setLastName(String lastName){
        logger.debug("Setting last name " + lastName);
        this.lastName = lastName;
        driver.findElement(By.id("person-last-name")).clear();
        driver.findElement(By.id("person-last-name")).sendKeys(lastName);
        logger.debug("Last name set");
    }

    public void setUsername(String username){
        this.username = username;
        driver.findElement(By.id("person-nickname")).clear();
        driver.findElement(By.id("person-nickname")).sendKeys(username);

    }

    public void clickDoneButton() throws Exception{
        logger.debug("Clicking done button");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        TimeUnit.SECONDS.sleep(5);
    }

    public AddPersonForm fillFields(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        return this;
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }
}
