package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class ProduitTTC {
    private final String nom;
    private final BigDecimal prix;

    public ProduitTTC(String nom, BigDecimal prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public BigDecimal getPrix() {
        return prix;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProduitTTC that = (ProduitTTC) o;
        return Objects.equals(nom, that.nom) && Objects.equals(prix, that.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prix);
    }

    @Override
    public String toString() {
        return "ProduitTTC{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }


}
