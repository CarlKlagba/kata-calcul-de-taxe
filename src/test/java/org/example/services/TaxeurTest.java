package org.example.services;

import org.example.model.Produit;
import org.example.model.ProduitExempt;
import org.example.model.ProduitTaxe;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.Boolean.*;
import static org.junit.jupiter.api.Assertions.*;

class TaxeurTest {

    @Test
    void quand_je_taxe_un_produit_taxe_pour_10_pourcent(){
        Taxeur taxeur = new Taxeur();
        Produit cdMusical = new ProduitTaxe("CD musical", new BigDecimal("15.0"));
        BigDecimal taxe = taxeur.taxe(cdMusical);

        assertEquals(new BigDecimal("1.50"), taxe);
    }

    @Test
    void quand_je_taxe_un_produit_taxe_pour_10_pourcent_et_j_arrondie_aux_5_cent_superieurs(){
        Taxeur taxeur = new Taxeur();
        Produit produit1 = new ProduitTaxe("produit1", new BigDecimal("15.1"));
        BigDecimal taxe = taxeur.taxe(produit1);
        assertEquals(new BigDecimal("1.55"), taxe);

        Produit produit2 = new ProduitTaxe("produit2", new BigDecimal("15.7"));
        BigDecimal taxe2 = taxeur.taxe(produit2);
        assertEquals(new BigDecimal("1.60"), taxe2);
    }

    @Test
    void quand_je_taxe_un_produit_exempt_de_taxe_je_facture_0_pourcent() {
        Produit cdMusical = new ProduitExempt("Medicament", new BigDecimal("9.99"));

        Taxeur taxeur = new Taxeur();
        BigDecimal taxe = taxeur.taxe(cdMusical);

        assertEquals(new BigDecimal("0.00"), taxe);
    }

    @Test
    void quand_je_taxe_un_produit_importé_de_taxe_je_facture_15_pourcent() {
        Produit cdMusical = new ProduitTaxe("Parfum importé", new BigDecimal("10.00"), TRUE);

        Taxeur taxeur = new Taxeur();
        BigDecimal taxe = taxeur.taxe(cdMusical);

        assertEquals(new BigDecimal("1.50"), taxe);
    }

    @Test
    void quand_je_taxe_un_produit_exempt_importé_de_taxe_je_facture_5_pourcent() {
        Produit produitImporte = new ProduitExempt("chocola importé", new BigDecimal("10.00"), TRUE);

        Taxeur taxeur = new Taxeur();
        BigDecimal taxe = taxeur.taxe(produitImporte);

        assertEquals(new BigDecimal("0.50"), taxe);
    }

}