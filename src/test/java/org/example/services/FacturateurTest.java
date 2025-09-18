package org.example.services;

import org.example.model.*;
import org.example.port.FacturePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.example.model.TypeProduit.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturateurTest {
    Facturateur facturateur;
    Taxeur taxeur;
    FacturePrinter facturePrinter;

    @BeforeEach
    void setUp() {
        taxeur  = mock();
        facturePrinter = mock();
        facturateur = new Facturateur(taxeur, facturePrinter);
    }

    @Test
    void quand_je_facture_une_commande_a_1_produit_je_facture_1_produit_avec_les_taxes() {

        Produit cdMusical = new Produit("CD musical", new BigDecimal("14.99"), Autre);
        Commande commande = new Commande(List.of(cdMusical));

        when(taxeur.taxe(any())).thenReturn(new BigDecimal("1.50"));

        facturateur.facture(commande);

        ProduitTTC cdMusicalTTC = new ProduitTTC("CD musical", new BigDecimal("16.49"));
        Facture expectedFacture = new Facture(List.of(cdMusicalTTC), new BigDecimal("1.50"), new BigDecimal("16.49"));

        verify(taxeur, times(1)).taxe(any());
        verify(facturePrinter, times(1)).printFacture(expectedFacture);
    }

    @Test
    void quand_je_facture_une_commande_a_3_produits_je_facture_3_produits_avec_les_taxes_appliquées() {
        Produit produit1 = new Produit("produit1", new BigDecimal("10.00"), Autre);
        Produit produit2 = new Produit("produit2", new BigDecimal("10.00"), Autre);
        Produit produit3 = new Produit("produit3", new BigDecimal("10.00"), Autre);
        Commande commande = new Commande(List.of(produit1, produit2, produit3));

        when(taxeur.taxe(any())).thenReturn(new BigDecimal("1.00"));

        facturateur.facture(commande);

        ProduitTTC produit1TTC = new ProduitTTC("produit1", new BigDecimal("11.00"));
        ProduitTTC produit2TTC = new ProduitTTC("produit2", new BigDecimal("11.00"));
        ProduitTTC produit3TTC = new ProduitTTC("produit3", new BigDecimal("11.00"));
        Facture expectedFacture = new Facture(
                List.of(produit1TTC, produit2TTC, produit3TTC),
                new BigDecimal("3.00"),
                new BigDecimal("33.00"));

        verify(taxeur, times(3)).taxe(any());
        verify(facturePrinter, times(1)).printFacture(expectedFacture);
    }

}