public class CmdRegister extends RecordedCommand{
	Club club= Club.getInstance();
    private Member m;
	
	@Override
	public void execute(String[] cmdParts)
	{
		try{
			if(cmdParts.length<3){
				throw new ExInsufficientcommand();
			}
			m=new Member(cmdParts[1],cmdParts[2]);
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		}
		catch (Exarleadyname e) {
			System.out.println(e.getMessage());
		}
		catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
		club.removeMember(m);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
		club.addMember(m);
		addUndoCommand(this);
	}
}