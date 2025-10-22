
package pl.currency.console.commands;

import pl.currency.model.ExchangeRate;
import pl.currency.model.ExchangeTable;
import pl.currency.service.Exchange;

import java.util.Locale;
import java.util.Scanner;

public class ExchangeCurrencyCommand implements ConsoleCommand {

    private final ExchangeTable table;
    private final Exchange exchanger = new Exchange();

    public ExchangeCurrencyCommand(ExchangeTable table) {
        this.table = table;
    }

    @Override
    public String parameter() { return "2"; }

    @Override
    public String label() { return "Convert currency (code->code)"; }

    @Override
    public boolean handle() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
        System.out.print("From (code, e.g. USD): ");
        String from = sc.nextLine().trim().toUpperCase(Locale.ROOT);
        System.out.print("To (code, e.g. EUR): ");
        String to = sc.nextLine().trim().toUpperCase(Locale.ROOT);

        ExchangeRate r1 = table.getRate(from);
        ExchangeRate r2 = table.getRate(to);
        if (r1 == null || r2 == null) {
            System.out.println("Unknown currency code. Use option '1' to view available codes.");
            return false;
        }
        double out = exchanger.exchange(amount, r1, r2);
        System.out.printf(Locale.ROOT, "%f %s = %.4f %s\n", amount, from, out, to);
        return false;
    }
}
