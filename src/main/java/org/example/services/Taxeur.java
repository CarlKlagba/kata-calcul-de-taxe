package org.example.services;

import org.example.model.Produit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Taxeur {

    private static final BigDecimal TAXES = new BigDecimal("0.1");
    private static final BigDecimal TAXES_IMPORT = new BigDecimal("0.05");
    private static final BigDecimal INCREMENT_SUPERIEURS = new BigDecimal("0.05");

    public BigDecimal determineTaxes(Produit produit) {
        var pourcentage = BigDecimal.ZERO;
        if(produit.isTaxe()) {
            pourcentage = TAXES;
        }
        if(produit.isImporte()){
            pourcentage = pourcentage.add(TAXES_IMPORT);
        }

        BigDecimal taxes = produit.getPrix().multiply(pourcentage);
        return arrondi(taxes);
    }

    private BigDecimal arrondi(BigDecimal taxes) {
        BigDecimal increment = INCREMENT_SUPERIEURS;
        return taxes.divide(increment, 0, RoundingMode.UP)
                .multiply(increment)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
