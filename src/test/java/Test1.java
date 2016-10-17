import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by User on 12.10.2016.
 */
public class Test1 {
    @BeforeClass(alwaysRun = true)
    public void setUp()throws Exception{
        Context.initInstance();
    }

    @Test
    public void mainTest() throws Exception{
        String login = "admin";
        String pass = "admin";
        LoginPage loginPage = LoginPage.openLoginPage();

        AdminPage adminPage = loginPage.doLogin(login, pass);
        Assert.assertTrue(adminPage.isLoggedIn());

        //Допилить рандом
        String firstName = "Vasya";
        String lastName = "Pupkin";

        AddPersonForm addPersonForm = adminPage.clickAddPerson();
        addPersonForm = addPersonForm.fillFields(firstName, lastName);
        addPersonForm.clickDoneButton();


        Assert.assertTrue(adminPage.existsPerson(firstName, lastName));

        adminPage.doDelete(firstName, lastName);

        Assert.assertFalse(adminPage.existsPerson(firstName, lastName));

        adminPage.doLogout();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        Context.getContext().close();
    }
}
