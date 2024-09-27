public class ExNotborrowedbyyou extends Exception{
    public ExNotborrowedbyyou() { super("You did not borrowed this item"); }
    public ExNotborrowedbyyou(String message) { super(message); }
}
