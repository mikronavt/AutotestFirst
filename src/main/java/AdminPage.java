import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 12.10.2016.
 */
public class AdminPage extends Page {
    private WebDriver driver;

    public AddPersonForm clickAddPerson(){
        driver.findElement(By.id("add-person")).click();
        AddPersonForm addPersonForm = new AddPersonForm();
        addPersonForm.setDriver(this.driver);
        return addPersonForm;
    }



    public void doLogout(){
        driver.findElement(By.cssSelector("#logout > span")).click();
    }

    public boolean isLoggedIn(){
        try {
            return driver.findElement(By.cssSelector("span")).getText().contains("admin");
        } catch (Exception e){
            return false;
        }


    }


    public WebElement findPerson(String firstName, String lastName) {

        for (WebElement el: driver.findElements(By.cssSelector("div.name"))){
            if(el.getText().contains(firstName) && el.getText().contains(lastName)){
                return el;
            }
        }

        return null;
    }

    public boolean existsPerson(String firstName, String lastName) throws Exception{

        return findPerson(firstName, lastName) != null;

    }



    public void doDelete(String firstName, String lastName) throws Exception{
        WebElement el = findPerson(firstName, lastName);
        el.findElements(By.tagName("div")).get(1).click();
        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        TimeUnit.SECONDS.sleep(1);
    }


    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
