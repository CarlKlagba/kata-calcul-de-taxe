package org.example.services;

import org.example.model.Commande;
import org.example.model.Facture;
import org.example.port.FacturePrinter;

import java.math.BigDecimal;

public class Facturateur {
    private final Taxeur taxeur;
    private final FacturePrinter facturePrinter;

    public Facturateur(Taxeur taxeur, FacturePrinter facturePrinter) {
        this.taxeur = taxeur;
        this.facturePrinter = facturePrinter;
    }

    public void facture(Commande commande) {
        Facture.Builder factureBuilder = new Facture.Builder();

        commande.produits().forEach(p -> {
            BigDecimal taxes = taxeur.determineTaxes(p);
            factureBuilder.ajouteProduitAvecTaxe(p, taxes);
        });

        Facture facture = factureBuilder.build();
        facturePrinter.printFacture(facture);
    }



}
