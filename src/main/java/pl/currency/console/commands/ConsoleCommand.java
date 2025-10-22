
package pl.currency.console.commands;

/**
 * Command interface for console actions.
 */
public interface ConsoleCommand {
    /** menu parameter like "1", "2", or "display.simply" */
    String parameter();
    /** human readable label */
    String label();
    /** handle the command; return true if the app should exit */
    boolean handle();
}
