import org.example.model.*;
import org.example.port.FacturePrinter;
import org.example.services.Facturateur;
import org.example.services.Taxeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.Boolean.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        Produit livre = new ProduitExempt("livre", new BigDecimal("12.49"));
        Produit cdMusical = new ProduitTaxe("CD musical", new BigDecimal("14.99"));
        Produit chocolat = new ProduitExempt("barre de chocolat", new BigDecimal("0.85"));
        Commande commande = new Commande(List.of(livre, cdMusical, chocolat));

        facturateur.facture(commande);

        ProduitTTC cdMusicalTTC = new ProduitTTC("CD musical", new BigDecimal("16.49"));
        ProduitTTC livreTTC = new ProduitTTC("livre", new BigDecimal("12.49"));
        ProduitTTC chocolatTTC = new ProduitTTC("barre de chocolat", new BigDecimal("0.85"));

        Facture expectedFacture = new Facture(List.of(livreTTC, cdMusicalTTC, chocolatTTC), new BigDecimal("1.50"), new BigDecimal("29.83"));


        verify(facturePrinter).printFacture(expectedFacture);

    }

    @Test
    public void accepteTest_input2() {
        Produit parfumImporte = new ProduitTaxe("flacon de parfum importé", new BigDecimal("47.50"), TRUE);
        Produit chocolatImportees = new ProduitExempt("boîte de chocolats importée", new BigDecimal("10.00"), TRUE);
        Commande commande = new Commande(List.of(parfumImporte, chocolatImportees));

        facturateur.facture(commande);

        ProduitTTC parfumImportelTTC = new ProduitTTC("flacon de parfum importé", new BigDecimal("54.65"));
        ProduitTTC chocolatImporteesTTC = new ProduitTTC("boîte de chocolats importée", new BigDecimal("10.50"));

        Facture expectedFacture = new Facture(List.of(parfumImportelTTC, chocolatImporteesTTC), new BigDecimal("7.65"), new BigDecimal("65.15"));

        verify(facturePrinter).printFacture(expectedFacture);
    }

    @Test
    public void accepteTest_input3() {
        Produit parfumImporte = new ProduitTaxe("flacon de parfum importé", new BigDecimal("27.99"), TRUE);
        Produit parfum = new ProduitTaxe("flacon de parfum", new BigDecimal("18.99"));
        Produit pilules = new ProduitExempt("boite de pilules contre la migraine", new BigDecimal("9.75"));
        Produit chocolatImportees = new ProduitExempt("chocolats importées", new BigDecimal("11.25"), TRUE);
        Commande commande = new Commande(List.of(parfumImporte, parfum, pilules, chocolatImportees));

        facturateur.facture(commande);

        ProduitTTC parfumImportelTTC = new ProduitTTC("flacon de parfum importé", new BigDecimal("32.19"));
        ProduitTTC parfumTTC = new ProduitTTC("flacon de parfum", new BigDecimal("20.89"));
        ProduitTTC pilulesTTC = new ProduitTTC("boite de pilules contre la migraine", new BigDecimal("9.75"));
        ProduitTTC chocolatImporteesTTC = new ProduitTTC("chocolats importées", new BigDecimal("11.85"));

        Facture expectedFacture = new Facture(List.of(parfumImportelTTC, parfumTTC, pilulesTTC, chocolatImporteesTTC), new BigDecimal("6.70"), new BigDecimal("74.68"));

        verify(facturePrinter).printFacture(expectedFacture);
    }


}
