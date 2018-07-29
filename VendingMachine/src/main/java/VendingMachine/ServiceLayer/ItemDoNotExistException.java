   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.ServiceLayer;

/**
 *
 * @author Farhan
 */
public class ItemDoNotExistException extends Exception{
 public ItemDoNotExistException(String message) {
        super(message);
    }

    public ItemDoNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
