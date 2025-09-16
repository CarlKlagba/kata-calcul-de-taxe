package org.example.model;

import java.math.BigDecimal;

public final class ProduitExempt extends Produit {
    public ProduitExempt(String nom, BigDecimal prix) {
        super(nom, prix);
    }

    public ProduitExempt(String nom, BigDecimal prix, boolean importe) {
        super(nom, prix, importe);
    }
}
