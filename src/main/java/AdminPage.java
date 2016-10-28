import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by User on 12.10.2016.
 */
public class AdminPage extends Page {
    private WebDriver driver;
    private Logger logger;
    private Wait<WebDriver> wait;

    @FindBy(id = "add-person")
    private WebElement addPersonButton;

    @FindBy(css = "#logout > span")
    private WebElement logoutButton;

    @FindBy(css = "span")
    private WebElement spanWithUsername;

    @FindBy(css = "div.name")
    private List<WebElement> elementsWithNames;


    @FindBy(xpath = "//button[@type='button' and contains(., 'Да')]")
    private WebElement confimDeletionButton;



    public static AdminPage openAdminPage(){
        AdminPage adminPage = new AdminPage();
        adminPage.logger = LogManager.getLogger("Logger " + adminPage.getClass());
        adminPage.logger.debug("Opening admin page");

        adminPage.driver = Context.getContext().getDriver();
        PageFactory.initElements(adminPage.driver, adminPage);
        adminPage.wait = new WebDriverWait(adminPage.driver, 5, 1000);

        adminPage.logger.debug("Opened admin page");

        return adminPage;
    }

    public AddPersonForm clickAddPerson(){
        logger.debug("Clicking add person");

        addPersonButton.click();
        AddPersonForm addPersonForm = AddPersonForm.openAddPersonForm();

        logger.debug("Click add person done");
        return addPersonForm;
    }



    public void doLogout(){
        logger.debug("Logging out");
        logoutButton.click();
        logger.debug("Logout done");
    }

    public boolean isLoggedIn(){
        logger.debug("Checking if is logged in");
        try {
            return spanWithUsername.getText().contains("admin");
        } catch (Exception e){
            return false;
        }


    }


    private WebElement findPerson(String firstName, String lastName) {
        logger.debug("Finding person " + firstName + " " + lastName);
        for (WebElement el: elementsWithNames){
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
        el.findElement(By.cssSelector("[title='Удалить человека']")).click();
        confimDeletionButton.click();
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(., '" + firstName + "')]"))));
        } catch(TimeoutException e){}
        logger.debug("Person deleted");
    }

    public void addPerson(String firstName, String lastName) throws Exception{
        logger.debug("Adding person " + firstName + " " + lastName);

        AddPersonForm addPersonForm = this.clickAddPerson();
        addPersonForm = addPersonForm.fillFields(firstName, lastName);
        addPersonForm.clickDoneButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(., '" + firstName + "')]")));

        logger.debug("Person added");

    }
    


}
