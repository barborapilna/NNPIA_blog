package upce.nnpia.blog.dataTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.dataFactory.UserTestDataFactory;
import upce.nnpia.blog.entity.User;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(UserTestDataFactory.class)
class UserTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTestDataFactory userTestDataFactory;

    @Test
    void saveOneUser() {
        userTestDataFactory.saveNewUser1();
        List<User> all = userDao.findAll();
        Assertions.assertEquals(all.size(), 1);
    }

    @Test
    void saveUsers() {
        userTestDataFactory.saveNewUser1();
        userTestDataFactory.saveNewUser2();
        List<User> all = userDao.findAll();
        Assertions.assertEquals(all.size(), 2);
    }

    @Test
    void getUserByUsername() {
        userTestDataFactory.saveNewUser1();
        userTestDataFactory.saveNewUser2();
        User result = userDao.findByUsername("novkla");
        Assertions.assertEquals("Klára", result.getFirstName());
    }

}
