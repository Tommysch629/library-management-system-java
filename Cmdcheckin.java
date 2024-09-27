public class Cmdcheckin extends RecordedCommand {
    Club club= Club.getInstance();
    Member b_member;
    Item b_item;
    Member on_member;
    private String m_id;
    private String i_id;
    private Day borrowday;
    private int oldrequestno;
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
            if(b_item.borrowed_id(m_id)){
                borrowday=b_item.borrowed_day();
                b_member.antiborrow(b_item);
                if(b_item.queuelist().size()==0){
                    b_item.change_available();
                    oldrequestno=0;
                }
                else{
                    oldrequestno=b_item.queuelist().size();
                    Day due=SystemDate.getInstance().clone();
                    due.addDay(3);
                    on_member=b_item.first();
                    on_member.antirequest(b_item);
                    b_item.backrequest(on_member);
                    b_item.change_onhold(on_member, SystemDate.getInstance().clone());
                    System.out.println("Item ["+b_item.getid()+" "+b_item.getname()+"] is ready for pick up by ["+on_member.getid()+" "+on_member.getName()+"].  On hold due on "+due.toString()+".");
                }
                addUndoCommand(this);
		        clearRedoList();
                System.out.println("Done.");
            }
        }
        catch (ExNotFound e) {
			System.out.println(e.getMessage());
		}
        catch (ExNotborrowedbyyou e){
            System.out.println(e.getMessage());
        }
        catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
    @Override
	public void undoMe()
	{
        b_member.borrow(b_item);
        b_item.change_borrow(b_member,borrowday);
        if(oldrequestno>=1){
            b_item.getrequestposition(0, on_member);
            on_member.request(b_item);
            System.out.println("Sorry. "+on_member.getid()+" "+on_member.getName()+" please ignore the pick up notice for "+b_item.getid()+" "+b_item.getname()+".");
        }
        addRedoCommand(this); 
	}
	@Override
	public void redoMe()
	{
        b_member.antiborrow(b_item);
        if(oldrequestno==0){
            b_item.change_available();
        }
        else{
            Day due=SystemDate.getInstance().clone();
            due.addDay(3);
            on_member=b_item.first();
            on_member.antirequest(b_item);
            b_item.backrequest(on_member);
            b_item.change_onhold(on_member, SystemDate.getInstance().clone());
            System.out.println("Item ["+b_item.getid()+" "+b_item.getname()+"] is ready for pick up by ["+on_member.getid()+" "+on_member.getName()+"].  On hold due on "+due.toString()+".");
        }
        addUndoCommand(this);
	}
}