public class ExNotFound extends Exception{
    public ExNotFound() { super("Name not found"); }
    public ExNotFound(String message) { super(message); }
}
