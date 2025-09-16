package org.example.services;

import org.example.model.Produit;
import org.example.model.ProduitTaxe;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Taxeur {

    public BigDecimal taxe(Produit produit) {
        var pourcentage = new BigDecimal("0.0");
        if(produit instanceof ProduitTaxe) {
            pourcentage = new BigDecimal("0.1");
        }
        if(produit.isImporte()){
            pourcentage = pourcentage.add(new BigDecimal("0.05"));
        }

        BigDecimal taxes = produit.getPrix().multiply(pourcentage);
        return arrondi(taxes);
    }

    private BigDecimal arrondi(BigDecimal taxes) {
        BigDecimal increment = new BigDecimal("0.05");
        return taxes.divide(increment, 0, RoundingMode.UP)
                .multiply(increment)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
