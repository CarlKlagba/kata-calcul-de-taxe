package org.example;

import org.example.infra.SysOutFacturePrinter;
import org.example.model.Commande;
import org.example.model.Produit;
import org.example.model.ProduitExempt;
import org.example.model.ProduitTaxe;
import org.example.services.Facturateur;
import org.example.services.Taxeur;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class Main {
    public static void main(String[] args) {

        Produit parfumImporte = new ProduitTaxe("flacon de parfum importé", new BigDecimal("27.99"), TRUE);
        Produit parfum = new ProduitTaxe("flacon de parfum", new BigDecimal("18.99"));
        Produit pilules = new ProduitExempt("boite de pilules contre la migraine", new BigDecimal("9.75"));
        Produit chocolatImportees = new ProduitExempt("chocolats importées", new BigDecimal("11.25"), TRUE);
        Commande commande = new Commande(List.of(parfumImporte, parfum, pilules, chocolatImportees));

        Facturateur facturateur = new Facturateur(new Taxeur(), new SysOutFacturePrinter());

        facturateur.facture(commande);
    }
}