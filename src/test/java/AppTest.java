import com.gft.qa.teixeira.Login;
import com.gft.qa.teixeira.LoginPage;
import com.gft.qa.teixeira.FormsPage;
import com.gft.qa.teixeira.Startup;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AppTest {

    AndroidDriver driver;

    @Before
    public void setUp() {
        Startup startup = new Startup();
        try {
            driver = startup.startApp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() {
        if (driver.getSessionId() != null) {
            driver.quit();
        }
    }


    @Test
    @DisplayName("Test Login")
    public void testLogin() {
        Login login = new Login(driver);
        LoginPage loginPage = new LoginPage(driver);
        String respostaLogin;
        try {
            login.logOnApp("email@valido.com", "V@l0rV2l1d0");
            respostaLogin = loginPage.getLoginResponse();
            loginPage.confirmLoginMessage();
            Assert.assertEquals("You are logged in!", respostaLogin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLoginInvalido() throws Exception {
        Login login = new Login(driver);
        LoginPage loginPage = new LoginPage(driver);
        login.logOnApp("emailAvalido.com", "V@l0rV2");
        Assert.assertTrue(loginPage.getInvalidEmailResponse());
    }

    @Test
    public void testOpenForms() throws Exception {
        FormsPage formsPage = new FormsPage(driver);
        formsPage.openFormsPagePage();
    }

    @Test
    public void fillOutForm() throws Exception {
        FormsPage formsPage = new FormsPage(driver);
        formsPage.openFormsPagePage();
        formsPage.fillInInputFieldAndValidateYouHaveTyped("Is it something");
        formsPage.clickAndValidateSwitch();
        formsPage.validateDropdown();
    }

}