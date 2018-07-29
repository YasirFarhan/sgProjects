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
public class FlooringMasteryInvalidConfigException extends Exception {

    public FlooringMasteryInvalidConfigException(String message) {
        super(message);
    }

    public FlooringMasteryInvalidConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
