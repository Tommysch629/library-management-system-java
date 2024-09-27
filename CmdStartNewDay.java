public class CmdStartNewDay extends RecordedCommand{
	Club club= Club.getInstance();
	private String sDay1;
	private String sDay2;
	@Override
	public void execute(String[] cmdParts)
	{
		try{
			if(cmdParts.length<2){
				throw new ExInsufficientcommand();
			}
			SystemDate sd= SystemDate.getInstance();
			sDay1=sd.toString();
			sDay2=cmdParts[1];
			sd.set(cmdParts[1]);
			Day new_Day= SystemDate.getInstance().clone();
			for(Item i:club.getItem()){
				if(i.onhold_due(new_Day.returnday())){
					if(i.queuelist().size()==0){
						i.change_available();
						System.out.println("On hold period is over for "+i.getid()+" "+i.getname()+".");
					}
					else{
						Day due=SystemDate.getInstance().clone();
						due.addDay(3);
						Member on_member;
						on_member=i.first();
						on_member.antirequest(i);
						i.backrequest(on_member);
						i.change_onhold(on_member, SystemDate.getInstance().clone());
						System.out.println("On hold period is over for "+i.getid()+" "+i.getname()+".");
						System.out.println("Item ["+i.getid()+" "+i.getname()+"] is ready for pick up by ["+on_member.getid()+" "+on_member.getName()+"].  On hold due on "+due.toString()+".");
					}
				}
			}
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		}
		catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
		SystemDate sd=SystemDate.getInstance();
		sd.set(sDay1);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		SystemDate sd=SystemDate.getInstance();
		sd.set(sDay2);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
