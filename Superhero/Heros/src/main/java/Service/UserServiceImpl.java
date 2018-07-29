 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.UserDao;
import java.util.List;
import javax.inject.Inject;
import model.User;

/**
 *
 * @author Farhan
 */
public class UserServiceImpl implements UserService {

    UserDao dao;

    @Inject
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User addUser(User newUser) {
        return dao.addUser(newUser);
    }

    @Override
    public void deleteUser(String username) {
        dao.deleteUser(username);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUser();
    }

    @Override
    public User updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public void enableOrDisableUser(String username) {
        dao.enableOrDisableUser(username);
    }

}
