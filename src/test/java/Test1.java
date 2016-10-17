import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

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
        String firstName = generateRandomString(7);
        String lastName = generateRandomString(7);

        AddPersonForm addPersonForm = adminPage.clickAddPerson();
        addPersonForm = addPersonForm.fillFields(firstName, lastName);
        addPersonForm.clickDoneButton();


        Assert.assertTrue(adminPage.existsPerson(firstName, lastName));

        adminPage.doDelete(firstName, lastName);

        Assert.assertFalse(adminPage.existsPerson(firstName, lastName));

        adminPage.doLogout();
    }

    private String generateRandomString(int len){
        Random random = new Random();
        String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int number = random.nextInt(charList.length());
            char ch = charList.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        Context.getContext().close();
    }
}
