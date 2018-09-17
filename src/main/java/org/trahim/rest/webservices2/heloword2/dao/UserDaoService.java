package org.trahim.rest.webservices2.heloword2.dao;

import org.springframework.stereotype.Component;
import org.trahim.rest.webservices2.heloword2.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "David", new Date()));
        users.add(new User(2, "Ranga", new Date()));
        users.add(new User(3, "Nik", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            userCount++;
            user.setId(userCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }

        }

        return null;
    }

    public User delete(int id) {

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

}
