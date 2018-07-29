 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import model.User;

/**
 *
 * @author Farhan
 */
public interface UserService {

    public User addUser(User newUser);

    public void deleteUser(String username);

    public List<User> getAllUsers();

    public User updateUser(User user);
    public void enableOrDisableUser(String username) ;
}
 