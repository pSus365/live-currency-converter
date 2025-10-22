
package pl.currency.app;

import pl.currency.console.ConsoleInterface;
import pl.currency.console.commands.DisplayTableCommand;
import pl.currency.console.commands.ExchangeCurrencyCommand;
import pl.currency.console.commands.ExitCommand;
import pl.currency.model.ExchangeTable;

public class Main {
    public static void main(String[] args) {
        System.out.println("Currency Converter (NBP LastA.xml) - Async Console App");
        System.out.println("Downloading latest table from NBP asynchronously...");

        ExchangeApp app = ExchangeApp.getInstance();

        // Kick off the async load
        var future = app.getTableAsync();

        // While it loads, we could do other work. For simplicity, wait here once.
        ExchangeTable table = future.join(); // join waits without blocking main thread earlier
        System.out.println("Loaded table: " + table.getId() + ", date: " + table.getPublicationDate());

        // Wire up the console UI (commands depend on the loaded data)
        ConsoleInterface ui = new ConsoleInterface();
        ui.register(new ExitCommand());
        ui.register(new DisplayTableCommand(table));
        ui.register(new ExchangeCurrencyCommand(table));
        ui.runLoop();
    }
}
