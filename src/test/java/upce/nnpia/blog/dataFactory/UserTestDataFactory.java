package upce.nnpia.blog.dataFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upce.nnpia.blog.dao.UserDao;
import upce.nnpia.blog.entity.User;

@Component
public class UserTestDataFactory {

    @Autowired
    private UserDao userDao;

    public User saveNewUser1() {
        User user = new User();
        user.setFirstName("Karel");
        user.setLastName("Vomáčka");
        user.setUsername("vomkar");
        user.setPassword("hesloKarel");
        userDao.save(user);
        return user;
    }

    public User saveNewUser2() {
        User user = new User();
        user.setFirstName("Klára");
        user.setLastName("Novotná");
        user.setUsername("novkla");
        user.setPassword("hesloKlara");
        userDao.save(user);
        return user;
    }
}
