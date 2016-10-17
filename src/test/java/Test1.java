import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.util.Random;

/**
 * Created by User on 12.10.2016.
 */
public class Test1 {
    private Logger logger;

    @BeforeClass(alwaysRun = true)
    public void setUp()throws Exception{
        logger = LogManager.getLogger("Test logger");
        logger.info("Setting up before test");
        Context.initInstance();
    }

    @Test
    public void mainTest() throws Exception{
        logger.info("Start main test");

        String login = "admin";
        logger.debug("Setting login: " + login);
        String pass = "admin";
        logger.debug("Setting pass: " + pass);

        logger.debug("Opening login page");
        LoginPage loginPage = LoginPage.openLoginPage();

        logger.debug("Login into system");
        AdminPage adminPage = loginPage.doLogin(login, pass);
        Assert.assertTrue(adminPage.isLoggedIn());
        logger.debug("Login done");

        String firstName = generateRandomString(7);
        String lastName = generateRandomString(7);
        logger.debug("First name: " + firstName);
        logger.debug("Last name: " + lastName);

        logger.debug("Add new person");
        AddPersonForm addPersonForm = adminPage.clickAddPerson();
        addPersonForm = addPersonForm.fillFields(firstName, lastName);
        addPersonForm.clickDoneButton();

        Assert.assertTrue(adminPage.existsPerson(firstName, lastName));
        logger.debug("Person exists");

        logger.debug("Delete person");
        adminPage.doDelete(firstName, lastName);


        Assert.assertFalse(adminPage.existsPerson(firstName, lastName));
        logger.debug("Delete successful");

        logger.debug("Logout");
        adminPage.doLogout();

        logger.info("Main test done");
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
        logger.debug("Tearing down");
        Context.getContext().close();
        logger.info("Work finished");
    }
}
