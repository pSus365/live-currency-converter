
package pl.currency.console.commands;

public class ExitCommand implements ConsoleCommand {
    @Override
    public String parameter() { return "0"; }

    @Override
    public String label() { return "Exit"; }

    @Override
    public boolean handle() {
        System.out.println("Bye!");
        return true;
    }
}
