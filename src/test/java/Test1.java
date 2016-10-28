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
        logger = LogManager.getLogger("Logger " + this.getClass());
        logger.info("Setting up before test");
        Context.initInstance();
    }

    @Test
    public void mainTest() throws Exception{
        logger.info("Start main test");

        String login = "admin";
        String pass = "admin";

        LoginPage loginPage = LoginPage.openLoginPage();

        AdminPage adminPage = loginPage.doLogin(login, pass);
        Assert.assertTrue(adminPage.isLoggedIn());

        String firstName = generateRandomString(7);
        String lastName = generateRandomString(7);

        adminPage.addPerson(firstName, lastName);
        Assert.assertTrue(adminPage.existsPerson(firstName, lastName));

        adminPage.doDelete(firstName, lastName);
        Assert.assertFalse(adminPage.existsPerson(firstName, lastName));

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
        Context.getContext().close();
        logger.info("Work finished");
    }
}
