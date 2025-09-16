package org.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Facture {
    private final List<ProduitTTC> produitsTtc;
    private final BigDecimal taxes;
    private final BigDecimal prixTTc;

    public Facture(List<ProduitTTC> produitsTtc, BigDecimal taxes, BigDecimal prixTTc) {
        this.produitsTtc = produitsTtc;
        this.taxes = taxes;
        this.prixTTc = prixTTc;
    }


    public List<ProduitTTC> getProduitsTtc() {
        return produitsTtc;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getPrixTTc() {
        return prixTTc;
    }

    public static class Builder {
        List<ProduitTTC> produitsTTC = new ArrayList<>();
        List<BigDecimal> listOfTaxes =  new ArrayList<>();

        public Builder() {};

        public void ajouteProduitAvecTaxe(Produit produit, BigDecimal taxes){
            ProduitTTC produitTTC = produit.appliqueTaxes(taxes);
            produitsTTC.add(produitTTC);
            listOfTaxes.add(taxes);
        }

        public Facture build() {
            BigDecimal prixTotalTTC = produitsTTC.stream()
                    .map(ProduitTTC::getPrix)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalTaxes = listOfTaxes.stream()
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return new Facture(produitsTTC, totalTaxes, prixTotalTTC);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return Objects.equals(produitsTtc, facture.produitsTtc) && Objects.equals(taxes, facture.taxes) && Objects.equals(prixTTc, facture.prixTTc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produitsTtc, taxes, prixTTc);
    }

    @Override
    public String toString() {
        return "Facture{" +
                "produitsTtc=" + produitsTtc +
                ", taxes=" + taxes +
                ", prixTTc=" + prixTTc +
                '}';
    }
}
