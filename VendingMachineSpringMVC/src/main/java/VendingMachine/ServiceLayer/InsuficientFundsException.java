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
public class InsuficientFundsException extends Exception {

    public InsuficientFundsException(String message) {
        super(message);
    }

    public InsuficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
