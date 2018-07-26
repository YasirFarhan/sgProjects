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
public class NoInventoryException extends Exception {

    public NoInventoryException(String message) {
        super(message);
    }

    public NoInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
