
package pl.currency.parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import pl.currency.model.ExchangeRate;
import pl.currency.model.ExchangeTable;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * Concrete parser that understands NBP LastA.xml format.
 */
public class XMLDocument implements Document {

    @Override
    public ExchangeTable getTable(String content) throws Exception {
        var builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        var is = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        var doc = builder.parse(is);
        doc.getDocumentElement().normalize();

        String tableNo = doc.getElementsByTagName("numer_tabeli").item(0).getTextContent();
        LocalDate date = LocalDate.parse(doc.getElementsByTagName("data_publikacji").item(0).getTextContent());

        ExchangeTable table = new ExchangeTable(tableNo, date);

        NodeList positions = doc.getElementsByTagName("pozycja");
        for (int i = 0; i < positions.getLength(); i++) {
            Node p = positions.item(i);
            NodeList childs = p.getChildNodes();
            String code = null, name = null;
            double mid = Double.NaN;
            int multiplier = 1;
            for (int j = 0; j < childs.getLength(); j++) {
                Node n = childs.item(j);
                String tag = n.getNodeName();
                if (tag.equals("kod_waluty")) code = n.getTextContent();
                else if (tag.equals("nazwa_waluty")) name = n.getTextContent();
                else if (tag.equals("kurs_sredni")) {
                    // NBP uses comma as decimal separator
                    String raw = n.getTextContent().trim().replace(" ", "").replace(",", ".");
                    mid = Double.parseDouble(raw);
                } else if (tag.equals("przelicznik")) {
                    multiplier = Integer.parseInt(n.getTextContent().trim());
                }
            }
            if (code != null && name != null && !Double.isNaN(mid)) {
                double perUnit = mid / multiplier; // adjust by multiplier
                table.addRate(new ExchangeRate(code, name, perUnit));
            }
        }
        return table;
    }
}
