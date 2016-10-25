import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 12.10.2016.
 */
public class AdminPage extends Page {
    private WebDriver driver;
    private Logger logger;

    public static AdminPage openAdminPage(){
        AdminPage adminPage = new AdminPage();
        adminPage.driver = Context.getContext().getDriver();
        adminPage.logger = LogManager.getLogger("Logger " + adminPage.getClass());
        adminPage.logger.debug("Opened admin page");

        return adminPage;
    }

    public AddPersonForm clickAddPerson(){
        logger.debug("Clicking add person");

        driver.findElement(By.id("add-person")).click();
        AddPersonForm addPersonForm = AddPersonForm.openAddPersonForm();

        logger.debug("Click add person done");
        return addPersonForm;
    }



    public void doLogout(){
        logger.debug("Logging out");
        driver.findElement(By.cssSelector("#logout > span")).click();
        logger.debug("Logout done");
    }

    public boolean isLoggedIn(){
        logger.debug("Checking if is logged in");
        try {
            return driver.findElement(By.cssSelector("span")).getText().contains("admin");
        } catch (Exception e){
            return false;
        }


    }


    public WebElement findPerson(String firstName, String lastName) {
        logger.debug("Finding person " + firstName + " " + lastName);

        for (WebElement el: driver.findElements(By.cssSelector("div.name"))){
            if(el.getText().contains(firstName) && el.getText().contains(lastName)){
                return el;
            }
        }

        return null;

    }

    public boolean existsPerson(String firstName, String lastName) throws Exception{
        logger.debug("Checking if person " + firstName + " " + lastName + " exists");
        return findPerson(firstName, lastName) != null;

    }



    public void doDelete(String firstName, String lastName) throws Exception{
        logger.debug("Deleting person " + firstName + " " + lastName);
        WebElement el = findPerson(firstName, lastName);
        el.findElements(By.tagName("div")).get(1).click();
        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        TimeUnit.SECONDS.sleep(5);
        logger.debug("Person deleted");
    }

    public void addPerson(String firstName, String lastName) throws Exception{
        AddPersonForm addPersonForm = this.clickAddPerson();
        addPersonForm = addPersonForm.fillFields(firstName, lastName);
        addPersonForm.clickDoneButton();

        //todo возращать id и дальше все делать по id
    }
    


    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
