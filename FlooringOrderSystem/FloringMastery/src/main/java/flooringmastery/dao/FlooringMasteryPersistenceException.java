/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;

import java.io.IOException;

/**
 *
 * @author Y
 */
public class FlooringMasteryPersistenceException extends Exception {

    FlooringMasteryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlooringMasteryPersistenceException(String message) {
        super(message);
    }

    public FlooringMasteryPersistenceException(String message, IOException e) {
        super(message);
    }

}
