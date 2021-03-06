import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by User on 12.10.2016.
 */
public class AddPersonForm {
    private String firstName;
    private String lastName;
    private String username;
    private WebDriver driver;
    private Logger logger;

    @FindBy(id = "person-first-name")
    private WebElement firstNameField;

    @FindBy(id = "person-last-name")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='button' and contains(., 'Готово')]")
    private WebElement doneButton;

    public static AddPersonForm openAddPersonForm(){
        AddPersonForm addPersonForm = new AddPersonForm();
        addPersonForm.driver = Context.getContext().getDriver();
        addPersonForm.logger = LogManager.getLogger("Logger " + addPersonForm.getClass());

        PageFactory.initElements(addPersonForm.driver, addPersonForm);

        addPersonForm.logger.debug("Opened add person form");
        return addPersonForm;

    }

    public void setFirstName(String firstName){
        logger.debug("Setting first name " + firstName);
        this.firstName = firstName;

        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        logger.debug("First name set");
    }

    public void setLastName(String lastName){
        logger.debug("Setting last name " + lastName);
        this.lastName = lastName;


        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        logger.debug("Last name set");
    }



    public void clickDoneButton() throws Exception{
        logger.debug("Clicking done button");
        doneButton.click();
    }

    public AddPersonForm fillFields(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        return this;
    }

}
