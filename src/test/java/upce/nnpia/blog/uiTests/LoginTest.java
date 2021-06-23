package upce.nnpia.blog.uiTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import upce.nnpia.blog.BlogApplication;
import upce.nnpia.blog.dao.RoleDao;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dataFactory.Creator;
import upce.nnpia.blog.entity.Role;
import upce.nnpia.blog.entity.RoleType;
import upce.nnpia.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;


@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(Creator.class)
class LoginTest {
    private WebDriver driver;
    private final int PORT = 3000;
    private LocalStorage localStorage;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        URL url = LoginTest.class.getResource("/chromedriver.exe");
        String chromeDriverPath = null;
        try {
            chromeDriverPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }


    @BeforeEach
    public void setup() {
        String circleCIChromedriverPath = "/usr/local/bin/chromedriver";
        if (new File(circleCIChromedriverPath).exists()) {
            System.setProperty("webdriver.chrome.driver", circleCIChromedriverPath);
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(false);

        driver = new ChromeDriver(chromeOptions);

        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        localStorage = webStorage.getLocalStorage();

        Role role = new Role();
        role.setRoleName(RoleType.ROLE_USER);
        roleDao.saveAndFlush(role);

        User user = new User();
        user.setRole(role);
        user.setUsername("username");
        user.setLastName("lastname");
        user.setFirstName("firstname");
        user.setId(1L);
        user.setPassword("testRootPassword");

        userDao.saveAndFlush(user);

    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void successfulUserLogin() {
        driver.get("http://localhost:" + PORT + "/login");
        driver.findElement(By.name("username")).sendKeys("username");
        driver.findElement(By.name("password")).sendKeys("testRootPassword");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        WebDriverWait wt = new WebDriverWait(driver, 7);
        wt.until(ExpectedConditions.urlContains("/home"));
        Assertions.assertNotNull(localStorage.getItem("tokens"));
    }

    @Test
    public void unsuccessfulUserLogin() {
//        driver.get("http://localhost:" + PORT + "/login");
//        driver.findElement(By.name("username")).sendKeys("usernameusername");
//        driver.findElement(By.name("password")).sendKeys("wrongPassword");
//        driver.findElement(By.name("//[text()='Login")).click();
//        Assertions.assertNull(localStorage.getItem("tokens"));
    }
}
