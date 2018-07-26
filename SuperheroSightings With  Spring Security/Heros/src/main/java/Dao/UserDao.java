 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.User;

/**
 *
 * @author Farhan
 */
public interface UserDao {

    public User addUser(User newUser);

    public void deleteUser(String username);

    public List<User> getAllUser();

    public User updateUser(User user);

    public void enableOrDisableUser(String username) ;
}
