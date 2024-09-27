public class ItemStatusOnhold implements ItemStatus
{
	Member tobepick;
	Day onhold;
	public ItemStatusOnhold(Member member,Day loanDate){
		this.tobepick=member;
		this.onhold=loanDate;
        this.onhold.addDay(3);
	}
	public String getNameAndId(Member member,Day Date)
	{
		return this.tobepick.getid()+" "+this.tobepick.getName()+" until "+this.onhold;
	}
	
	public String getStatus (){
		Member n_member=null;
		Day n_Loanday=null;
		return "On holdshelf for "+this.getNameAndId(n_member, n_Loanday);
	}
	public String b_person(){
		return this.tobepick.getid();
	}
	public Day b_day(){
		return this.onhold;
	}
	public String real_getStatus(){
		return "On hold";
	}
}