package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public sealed abstract class Produit permits ProduitExempt, ProduitTaxe  {
    private final String nom;
    private final BigDecimal prix;
    private final boolean importe;

    public Produit(String nom, BigDecimal prix) {
        this.nom = nom;
        this.prix = prix;
        this.importe = false;
    }

    public Produit(String nom, BigDecimal prix, boolean importe) {
        this.nom = nom;
        this.prix = prix;
        this.importe = importe;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public boolean isImporte() {
        return importe;
    }

    public ProduitTTC appliqueTaxes(BigDecimal taxe) {
        return new ProduitTTC(this.nom, this.prix.add(taxe));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Objects.equals(nom, produit.nom) && Objects.equals(prix, produit.prix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prix);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
