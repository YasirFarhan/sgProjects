/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.advice;

import flooringmastery.dao.FlooringMasteryPersistenceException;
import org.aspectj.lang.JoinPoint;
import flooringmastery.dao.AuditDao;

/**
 *
 * @author Y
 */
public class LoggingAdvice {

    AuditDao auditDao;

    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Throwable error) {
        Object[] args = jp.getArgs();

        try {
            auditDao.writeAuditEntry(args[0].toString() + " | Exception:" + error.getMessage());
        } catch (FlooringMasteryPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
