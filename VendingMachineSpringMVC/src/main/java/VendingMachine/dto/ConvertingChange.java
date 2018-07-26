 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Farhan
 */
public class ConvertingChange {

    private BigDecimal quarterChange;
    private BigDecimal dimesChange;
    private BigDecimal nickelsChange;
    private BigDecimal penniesChange;

    public void calculatingChange(BigDecimal changeReturned) {
        BigDecimal tempChangeHolder;
        BigDecimal tempChangeHolder2;

        final BigDecimal quarterValue = new BigDecimal("0.25");
        final BigDecimal dimesValue = new BigDecimal("0.10");
        final BigDecimal nickelsValue = new BigDecimal("0.05");
        final BigDecimal penniesValue = new BigDecimal("0.01");

        this.quarterChange = changeReturned.subtract(changeReturned.remainder(quarterValue)).divide(quarterValue, 0, RoundingMode.HALF_UP);
// dimes Calculation
        this.dimesChange = changeReturned.remainder(quarterValue)
                .subtract(changeReturned.remainder(quarterValue)
                        .remainder(dimesValue))
                .divide(dimesValue, 0, RoundingMode.HALF_UP);

// nickels Calculation
        tempChangeHolder = changeReturned
                .subtract((quarterChange.multiply(quarterValue))
                        .add(dimesChange.multiply(dimesValue))
                );
        tempChangeHolder2 = (tempChangeHolder
                .subtract(changeReturned.subtract(
                        (quarterChange.multiply(BigDecimal.valueOf(0.25)))
                                .add(dimesChange.multiply(BigDecimal.valueOf(0.10))))
                        .remainder(nickelsValue)));
        this.nickelsChange = tempChangeHolder2.divide(nickelsValue, 0, RoundingMode.HALF_UP);

// pennies Calculation
        this.penniesChange = (tempChangeHolder.subtract(nickelsChange.multiply(nickelsValue)))
                .divide(penniesValue, 0, RoundingMode.HALF_UP);
    }

    public BigDecimal getQuarterChange() {
        return quarterChange;
    }

    public BigDecimal getDimesChange() {
        return dimesChange;
    }

    public BigDecimal getNickelsChange() {
        return nickelsChange;
    }

    public BigDecimal getPenniesChange() {
        return penniesChange;
    }
}
