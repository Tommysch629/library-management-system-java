import java.util.ArrayList;
public class Member implements Comparable<Member>
{
    private String id;
    private String name;
    private Day joinDate;
    private ArrayList<Item> borrowlist;
    private ArrayList<Item> requestlist;
    public Member(String id, String name) throws Exarleadyname{
        for(Member i:Club.getInstance().getMember()){
            if(i.id.equals(id)){
                throw new Exarleadyname("Member ID already in use: "+i.id+" "+i.name);
            }
        }
        this.id=id;
        this.name=name;
        this.joinDate=SystemDate.getInstance().clone();
        borrowlist= new  ArrayList<Item>();
        requestlist= new  ArrayList<Item>();
        Club.getInstance().addMember(this);
    }
    @Override
    public String toString() {
        return String.format("%-5s%-9s%11s%7d%13d", this.id, this.name, this.joinDate, this.totalborrow(), this.totalrequest());
    }
    public static String getListingHeader() {
        return String.format("%-5s%-9s%11s%11s%13s", "ID", "Name",
       "Join Date ", "#Borrowed", "#Requested" );
    }
    @Override
    public int compareTo(Member another) {
        if (this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0)return 1;
        else return -1;
    }
    public String getid() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void borrow(Item i){
        this.borrowlist.add(i);
    }
    public void request(Item i){
        this.requestlist.add(i);
    }
    public int totalborrow(){
        return this.borrowlist.size();
    }
    public int totalrequest(){
        return this.requestlist.size();
    }
    public void antiborrow(Item i){
        this.borrowlist.remove(i);
    }
    public void antirequest(Item i){
        this.requestlist.remove(i);
    }
    public boolean quotacheck() throws ExQuotaexceed{
        if(this.borrowlist.size()<6){
            return true;
        }
        throw new ExQuotaexceed("Loan quota exceeded.");
    }
    public boolean requestquotacheck() throws ExQuotaexceed{
        if(this.requestlist.size()<3){
            return true;
        }
        throw new ExQuotaexceed("Item request quota exceeded.");
    }
    public boolean notyetrequest(String id)throws Exalreadyrequest{
        for(Item i:this.requestlist){
            if(i.getid().equals(id)){
                throw new Exalreadyrequest("The same member has already requested the item.");
            }
        }
        return true;
    }
}
