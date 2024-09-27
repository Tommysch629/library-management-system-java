import java.util.ArrayList;
import java.util.Collections;
public class Club {
    private ArrayList<Member> allMembers;
    private ArrayList<Item> allItem;
    private static Club instance = new Club();
    private Club() { 
        allMembers = new  ArrayList<Member>(); 
        allItem = new  ArrayList<Item>(); 
    }
    public static Club getInstance() { return instance; }
    public void addMember(Member m) {
        allMembers.add(m);
        Collections.sort(allMembers); 
    }
    public void addItem(Item i) {
        allItem.add(i);
        Collections.sort(allItem); 
    }
    public void listClubMembers() {
        System.out.println(Member.getListingHeader()); 
        for (Member m: allMembers)
        System.out.println(m.toString()); 
    }
    public void listItems() {
        System.out.println(Item.getListingHeaderItem()); 
        for (Item i: allItem)
        System.out.println(i.toString()); 
    }
    public void removeMember(Member m) {
        allMembers.remove(m); 
    }
    public void removeItem(Item i) {
        allItem.remove(i); 
    }
    public ArrayList<Member> getMember(){
        return allMembers;
    } 
    public ArrayList<Item> getItem(){
        return allItem;
    }
    public Member findMember(String other_id)throws ExNotFound{
        for(Member m:this.getMember()){
            if(m.getid().equals(other_id)){
                return m;
            }
        }
        throw new ExNotFound("Member not found.");
    }
    public Item findItem(String other_id)throws ExNotFound{
        for(Item i:this.getItem()){
            if(i.getid().equals(other_id)){
                return i;
            }
        }
        throw new ExNotFound("Item not found.");
    }
}
