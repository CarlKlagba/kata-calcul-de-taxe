package org.example.infra;

import org.example.model.Facture;
import org.example.port.FacturePrinter;

public class SysOutFacturePrinter  implements FacturePrinter {
    @Override
    public void printFacture(Facture facture) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------- Facture -------------").append('\n');
        facture.getProduitsTtc()
                .forEach(sb::append);
        sb.append('\n');
        sb.append("  Montant de taxes: ").append(facture.getTaxes());
        sb.append("  Total:  ").append(facture.getPrixTTc());

        System.out.println(sb);
    }
}
