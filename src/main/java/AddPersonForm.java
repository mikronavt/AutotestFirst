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


    public void setFirstName(String firstName){
        this.firstName = firstName;
        driver.findElement(By.id("person-first-name")).clear();
        driver.findElement(By.id("person-first-name")).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
        driver.findElement(By.id("person-last-name")).clear();
        driver.findElement(By.id("person-last-name")).sendKeys(lastName);
    }

    public void setUsername(String username){
        this.username = username;
        driver.findElement(By.id("person-nickname")).clear();
        driver.findElement(By.id("person-nickname")).sendKeys(username);

    }

    public void clickDoneButton() throws Exception{
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        TimeUnit.SECONDS.sleep(1);
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
