package org.example;

import org.example.infra.SysOutFacturePrinter;
import org.example.model.Commande;
import org.example.model.Produit;
import org.example.services.Facturateur;
import org.example.services.Taxeur;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.example.model.TypeProduit.*;

public class Main {
    public static void main(String[] args) {

        Produit parfumImporte = new Produit("flacon de parfum importé", new BigDecimal("27.99"), Autre, TRUE);
        Produit parfum = new Produit("flacon de parfum", new BigDecimal("18.99"), Autre);
        Produit pilules = new Produit("boite de pilules contre la migraine", new BigDecimal("9.75"), Medicament);
        Produit chocolatImportees = new Produit("chocolats importées", new BigDecimal("11.25"), Nourriture, TRUE);
        Commande commande = new Commande(List.of(parfumImporte, parfum, pilules, chocolatImportees));

        Facturateur facturateur = new Facturateur(new Taxeur(), new SysOutFacturePrinter());

        facturateur.facture(commande);
    }
}