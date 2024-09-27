import java.util.ArrayList;
public class Item implements Comparable<Item>{
    private String id;
    private String name;
    private Day ArrDate;
    private ItemStatus status;
    private ArrayList<Member> queue;
    public Item(String id, String name) throws Exarleadyname{
        for(Item i:Club.getInstance().getItem()){
            if(i.id.equals(id)){
                throw new Exarleadyname("Item ID already in use: "+i.id+" "+i.name);
            }
        }
        this.id=id;
        this.name=name;
        this.ArrDate=SystemDate.getInstance().clone();
        queue= new  ArrayList<Member>();
        this.status=new ItemStatusAvailable();

        Club.getInstance().addItem(this);
    }
    @Override
    public String toString() {
        if(this.queue.size()==0){
            return String.format("%-5s%-17s%11s   %s", id, name, ArrDate, status.getStatus());  
        }
        return String.format("%-5s%-17s%11s   %s + %d request(s):%s", id, name, ArrDate, status.getStatus(),queue.size(),this.printqueuelist());  
    }
    @Override
    public int compareTo(Item another) {
        if (this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0)return 1;
        else return -1;
    }
    public String getid() {
        return this.id;
    }
    public String getname() {
        return this.name;
    }
    public static String getListingHeaderItem() {
        return String.format("%-5s%-17s%11s   %s", "ID", "Name", "  Arrival  ", "Status");
    }
    public void change_borrow(Member member,Day loanDate){
        this.status=new ItemStatusBorrowed(member, loanDate);
    }
    public void change_onhold(Member member,Day Date){
        this.status=new ItemStatusOnhold(member, Date);
    }
    public void change_available(){
        this.status=null;
        this.status=new ItemStatusAvailable();
    }
    public boolean isavailable(String id)throws ExBorrowbyother{
        if(this.status.getStatus().equals("Available")){
            return true;
        }
        else if((this.status.real_getStatus().equals("On hold"))&&this.status.b_person().equals(id)){
            return true;
        }
        throw new ExBorrowbyother("Item not available.");
    }
    public boolean notavailable()throws Exavailable{
        if(!(this.status.getStatus().equals("Available"))){
            return true;
        }
        throw new Exavailable("The item is currently available.");
    }
    public boolean borrowed_id(String checkin_id)throws ExNotborrowedbyyou{
        if(this.status.b_person().equals(checkin_id)){
            return true;
        }
        throw new ExNotborrowedbyyou("The item is not borrowed by this member.");
    }
    public boolean notborrowed_id(String checkin_id)throws Exborrowedbyyou{
        if(!(this.status.b_person().equals(checkin_id))){
            return true;
        }
        throw new Exborrowedbyyou("The item is already borrowed by the same member.");
    }
    public Day borrowed_day(){
        return this.status.b_day();
    }
    public String printqueuelist(){
        String ans="";
        for(Member m: queue){
            ans+=" "+m.getid();
        }
        return ans;
    }
    public void getrequest(Member m){
        this.queue.add(m);
    }
    public void getrequestposition(int position,Member m){
        this.queue.add(position,m);
    }
    public void backrequest(Member m){
        this.queue.remove(m);
    }
    public ArrayList<Member> queuelist(){
        return this.queue;
    }
    public boolean notonhold_id(String checkin_id)throws Exavailable{
        if(!(this.status.real_getStatus().equals("On hold")&&this.status.b_person().equals(checkin_id))){
            return true;
        }
        throw new Exavailable("The item is currently available.");
    }
    public boolean onhold_id(String checkin_id)throws ExBorrowbyother{
        if(this.status.real_getStatus().equals("On hold")&&this.status.b_person().equals(checkin_id)){
            return true;
        }
        throw new ExBorrowbyother("Item not available.");
    }
    public boolean onhold_check(String checkin_id){
        if(this.status.real_getStatus().equals("On hold")&&this.status.b_person().equals(checkin_id)){
            return true;
        }
        return false;
    }
    public boolean onhold_due(int new_day){
        if(this.status.real_getStatus().equals("On hold")&&this.status.b_day().returnday()<new_day){
            return true;
        }
        return false;
    }
    public Member first(){
        return queue.get(0);
    }
    public boolean requestexist(String id)throws Exnorequest{
        for(Member m:queue){
            if(m.getid().equals(id)){
                return true;
            }
        }
        throw new Exnorequest("Request record is not found.");
    }
}
