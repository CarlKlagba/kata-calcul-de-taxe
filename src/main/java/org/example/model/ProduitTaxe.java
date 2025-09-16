package org.example.model;

import java.math.BigDecimal;

public final class ProduitTaxe extends Produit{
    public ProduitTaxe(String nom, BigDecimal prix) {
        super(nom, prix);
    }

    public ProduitTaxe(String nom, BigDecimal prix, boolean importe) {
        super(nom, prix, importe);
    }
}
