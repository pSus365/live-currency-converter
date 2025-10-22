
package pl.currency.console;

import pl.currency.console.commands.ConsoleCommand;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Minimal console UI using the Command pattern.
 */
public class ConsoleInterface {

    private final Map<String, ConsoleCommand> commands = new LinkedHashMap<>();

    public void register(ConsoleCommand cmd) {
        commands.put(cmd.parameter(), cmd);
    }

    public void runLoop() {
        System.out.println("\nType an option and press Enter:");
        printMenu();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            ConsoleCommand cmd = commands.get(input);
            if (cmd == null) {
                System.out.println("Unknown option. Try again.");
                printMenu();
                continue;
            }
            boolean shouldExit = cmd.handle();
            if (shouldExit) {
                break;
            }
        }
    }

    private void printMenu() {
        System.out.println("0) Exit");
        for (ConsoleCommand cmd : commands.values()) {
            if (!cmd.parameter().equals("0")) {
                System.out.println(cmd.parameter() + ") " + cmd.label());
            }
        }
    }
}
