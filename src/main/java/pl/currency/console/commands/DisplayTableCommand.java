
package pl.currency.console.commands;

import pl.currency.model.ExchangeRate;
import pl.currency.model.ExchangeTable;

import java.util.Comparator;

public class DisplayTableCommand implements ConsoleCommand {

    private final ExchangeTable table;

    public DisplayTableCommand(ExchangeTable table) {
        this.table = table;
    }

    @Override
    public String parameter() { return "1"; }

    @Override
    public String label() { return "Display table (sorted by code)"; }

    @Override
    public boolean handle() {
        System.out.printf("Table %s (published %s)\n", table.getId(), table.getPublicationDate());
        System.out.println("CODE | NAME                           | PLN per unit");
        System.out.println("-----+--------------------------------+-------------");
        table.getAll().stream()
                .sorted(Comparator.comparing(ExchangeRate::getCode))
                .forEach(r -> System.out.printf("%-4s | %-30s | %10.4f\n", r.getCode(), r.getName(), r.getRatePLNperUnit()));
        return false;
    }
}
