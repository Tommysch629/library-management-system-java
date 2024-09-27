public class ExInsufficientcommand extends Exception{
    public ExInsufficientcommand() { super("Insufficient command arguments."); }
    public ExInsufficientcommand(String message) { super(message); }
}
