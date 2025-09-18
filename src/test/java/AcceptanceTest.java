import org.example.model.*;
import org.example.port.FacturePrinter;
import org.example.services.Facturateur;
import org.example.services.Taxeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.Boolean.*;
import static org.example.model.TypeProduit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AcceptanceTest {

    FacturePrinter facturePrinter;
    Facturateur facturateur;

    @BeforeEach
    void setUp() {
        facturePrinter = mock();
        facturateur = new Facturateur(new Taxeur(), facturePrinter);
    }

    @Test
    public void accepteTest_input1() {
        Produit livre = new Produit("livre", new BigDecimal("12.49"), Livre);
        Produit cdMusical = new Produit("CD musical", new BigDecimal("14.99"), Autre);
        Produit chocolat = new Produit("barre de chocolat", new BigDecimal("0.85"), Nourriture);
        Commande commande = new Commande(List.of(livre, cdMusical, chocolat));

        facturateur.facture(commande);

        ProduitTTC cdMusicalTTC = new ProduitTTC("CD musical", new BigDecimal("16.49"));
        ProduitTTC livreTTC = new ProduitTTC("livre", new BigDecimal("12.49"));
        ProduitTTC chocolatTTC = new ProduitTTC("barre de chocolat", new BigDecimal("0.85"));

        ArgumentCaptor<Facture> factureCaptor = ArgumentCaptor.forClass(Facture.class);
        verify(facturePrinter, times(1)).printFacture(factureCaptor.capture());

        Facture capturedFacture = factureCaptor.getValue();

        assertEquals(new BigDecimal("1.50"), capturedFacture.getTaxes());
        assertEquals(new BigDecimal("29.83"), capturedFacture.getPrixTTc());
        assertEquals(List.of(livreTTC, cdMusicalTTC, chocolatTTC), capturedFacture.getProduitsTtc());

    }

    @Test
    public void accepteTest_input2() {
        Produit parfumImporte = new Produit("flacon de parfum importé", new BigDecimal("47.50"), Autre, TRUE);
        Produit chocolatImportees = new Produit("boîte de chocolats importée", new BigDecimal("10.00"), Nourriture, TRUE);
        Commande commande = new Commande(List.of(parfumImporte, chocolatImportees));

        facturateur.facture(commande);

        ProduitTTC parfumImportelTTC = new ProduitTTC("flacon de parfum importé", new BigDecimal("54.65"));
        ProduitTTC chocolatImporteesTTC = new ProduitTTC("boîte de chocolats importée", new BigDecimal("10.50"));

        ArgumentCaptor<Facture> factureCaptor = ArgumentCaptor.forClass(Facture.class);
        verify(facturePrinter, times(1)).printFacture(factureCaptor.capture());

        Facture capturedFacture = factureCaptor.getValue();

        assertEquals(new BigDecimal("7.65"), capturedFacture.getTaxes());
        assertEquals(new BigDecimal("65.15"), capturedFacture.getPrixTTc());
        assertEquals(List.of(parfumImportelTTC, chocolatImporteesTTC), capturedFacture.getProduitsTtc());
    }

    @Test
    public void accepteTest_input3() {
        Produit parfumImporte = new Produit("flacon de parfum importé", new BigDecimal("27.99"), Autre, TRUE);
        Produit parfum = new Produit("flacon de parfum", new BigDecimal("18.99"), Autre);
        Produit pilules = new Produit("boite de pilules contre la migraine", new BigDecimal("9.75"), Medicament);
        Produit chocolatImportees = new Produit("chocolats importées", new BigDecimal("11.25"), Nourriture, TRUE);
        Commande commande = new Commande(List.of(parfumImporte, parfum, pilules, chocolatImportees));

        facturateur.facture(commande);

        ProduitTTC parfumImportelTTC = new ProduitTTC("flacon de parfum importé", new BigDecimal("32.19"));
        ProduitTTC parfumTTC = new ProduitTTC("flacon de parfum", new BigDecimal("20.89"));
        ProduitTTC pilulesTTC = new ProduitTTC("boite de pilules contre la migraine", new BigDecimal("9.75"));
        ProduitTTC chocolatImporteesTTC = new ProduitTTC("chocolats importées", new BigDecimal("11.85"));


        ArgumentCaptor<Facture> factureCaptor = ArgumentCaptor.forClass(Facture.class);
        verify(facturePrinter, times(1)).printFacture(factureCaptor.capture());

        Facture capturedFacture = factureCaptor.getValue();

        assertEquals(new BigDecimal("6.70"), capturedFacture.getTaxes());
        assertEquals(new BigDecimal("74.68"), capturedFacture.getPrixTTc());
        assertEquals(List.of(parfumImportelTTC, parfumTTC, pilulesTTC, chocolatImporteesTTC), capturedFacture.getProduitsTtc());
    }


}
