/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;

/**
 *
 * @author Y
 */
public class FlooringMasteryInvalidProductException extends Exception {

    public FlooringMasteryInvalidProductException(String message) {
        super(message);
    }

    public FlooringMasteryInvalidProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
