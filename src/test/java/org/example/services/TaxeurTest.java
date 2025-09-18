package org.example.services;

import org.example.model.Produit;
import org.example.model.TypeProduit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.lang.Boolean.*;
import static org.example.model.TypeProduit.*;
import static org.junit.jupiter.api.Assertions.*;

class TaxeurTest {

    @Test
    void quand_je_taxe_un_produit_taxe_pour_10_pourcent(){
        Taxeur taxeur = new Taxeur();
        Produit cdMusical = new Produit("CD musical", new BigDecimal("15.0"), Autre);
        BigDecimal taxe = taxeur.determineTaxes(cdMusical);

        assertEquals(new BigDecimal("1.50"), taxe);
    }

    @Test
    void quand_je_taxe_un_produit_taxe_pour_10_pourcent_et_j_arrondie_aux_5_cent_superieurs(){
        Taxeur taxeur = new Taxeur();
        Produit produit1 = new Produit("produit1", new BigDecimal("15.1"), Autre);
        BigDecimal taxe = taxeur.determineTaxes(produit1);
        assertEquals(new BigDecimal("1.55"), taxe);

        Produit produit2 = new Produit("produit2", new BigDecimal("15.7"), Autre);
        BigDecimal taxe2 = taxeur.determineTaxes(produit2);
        assertEquals(new BigDecimal("1.60"), taxe2);
    }

    @Test
    void quand_je_taxe_un_produit_exempt_de_taxe_je_facture_0_pourcent() {
        Produit cdMusical = new Produit("Medicament", new BigDecimal("9.99"), Nourriture);

        Taxeur taxeur = new Taxeur();
        BigDecimal taxe = taxeur.determineTaxes(cdMusical);

        assertEquals(new BigDecimal("0.00"), taxe);
    }

    @Test
    void quand_je_taxe_un_produit_importé_de_taxe_je_facture_15_pourcent() {
        Produit cdMusical = new Produit("Parfum importé", new BigDecimal("10.00"), Autre, TRUE);

        Taxeur taxeur = new Taxeur();
        BigDecimal taxe = taxeur.determineTaxes(cdMusical);

        assertEquals(new BigDecimal("1.50"), taxe);
    }

    @Test
    void quand_je_taxe_un_produit_exempt_importé_de_taxe_je_facture_5_pourcent() {
        Produit produitImporte = new Produit("chocola importé", new BigDecimal("10.00"), Nourriture, TRUE);

        Taxeur taxeur = new Taxeur();
        BigDecimal taxe = taxeur.determineTaxes(produitImporte);

        assertEquals(new BigDecimal("0.50"), taxe);
    }

}