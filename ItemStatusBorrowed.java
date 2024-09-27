public class ItemStatusBorrowed implements ItemStatus
{
	Member borrowing;
	Day Loanday;
	public ItemStatusBorrowed(Member member,Day loanDate){
		this.borrowing=member;
		this.Loanday=loanDate;
	}
	public String getNameAndId(Member member,Day loanDate)
	{
		return this.borrowing.getid()+" "+this.borrowing.getName()+" on "+this.Loanday;
	}
	
	public String getStatus (){
		Member n_member=null;
		Day n_Loanday=null;
		return "Borrowed by "+this.getNameAndId(n_member, n_Loanday);
	}
	public String b_person(){
		return this.borrowing.getid();
	}
	public Day b_day(){
		return this.Loanday;
	}
	public String real_getStatus (){
		return "Borrowed";
	}
}