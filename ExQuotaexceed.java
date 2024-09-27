public class ExQuotaexceed extends Exception{
    public ExQuotaexceed() { super("Quota fulled"); }
    public ExQuotaexceed(String message) { super(message); }
}