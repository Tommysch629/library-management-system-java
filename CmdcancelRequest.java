public class CmdcancelRequest extends RecordedCommand{
	Club club= Club.getInstance();
    Member b_member;
    Item b_item;
    private String m_id;
    private String i_id;
    private int position;
	@Override
	public void execute(String[] cmdParts)
	{
        try{
            if(cmdParts.length<3){
				throw new ExInsufficientcommand();
			}
            m_id=cmdParts[1];
            i_id=cmdParts[2];
            b_member=club.findMember(m_id);
            b_item=club.findItem(i_id);
            if(b_item.requestexist(m_id)){
                position=b_item.queuelist().indexOf(b_member);
                b_member.antirequest(b_item);
                b_item.backrequest(b_member);
                addUndoCommand(this);
		        clearRedoList();
                System.out.println("Done.");
            }
        }
        catch (ExNotFound e) {
			System.out.println(e.getMessage());
		}
        catch(Exnorequest e){
            System.out.println(e.getMessage());
        }
        catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
        b_member.request(b_item);
        b_item.getrequestposition(position,b_member);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        b_member.antirequest(b_item);
        b_item.backrequest(b_member);
		addUndoCommand(this);
	}
}