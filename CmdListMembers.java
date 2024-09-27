public class CmdListMembers extends RecordedCommand{
	Club club= Club.getInstance();
	@Override
	public void execute(String[] cmdParts)
	{
		try{
			if(cmdParts.length>1){
				throw new ExInsufficientcommand();
			}
			club.listClubMembers();
		}
		catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
    @Override
	public void undoMe()
	{
	}
	@Override
	public void redoMe()
	{
	}
}
