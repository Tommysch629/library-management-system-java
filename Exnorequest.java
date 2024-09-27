public class Exnorequest extends Exception{
    public Exnorequest() { super("No such request"); }
    public Exnorequest(String message) { super(message); }
}
