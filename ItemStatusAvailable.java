public class ItemStatusAvailable implements ItemStatus//It should be: public class RNormalMember implements Role 
{
	public String getNameAndId(Member member,Day loanDate)
	{
		return null;
	}
	
	public String getStatus (){
		return "Available";
	}
	public String b_person(){
		return " ";
	}
	public Day b_day(){
		return null;
	}
	public String real_getStatus (){
		return "available";
	}
}