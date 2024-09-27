public class Cmdcheckout extends RecordedCommand {
    Club club= Club.getInstance();
    Member b_member;
    Item b_item;
    private String m_id;
    private String i_id;
    private Day borrowday;
    private boolean onholdcheck=false;
    private Day dueday;
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
            if(b_item.isavailable(m_id)&&b_member.quotacheck()){
                if(b_item.onhold_check(m_id)){
                    onholdcheck=true;
                    dueday=b_item.borrowed_day();
                    dueday.backDay(3);;
                }
                borrowday=SystemDate.getInstance().clone();
                b_member.borrow(b_item);
                b_item.change_borrow(b_member,borrowday);
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.");
            }
        }
        catch (ExNotFound e) {
			System.out.println(e.getMessage());
		}
        catch (ExBorrowbyother e){
            System.out.println(e.getMessage());
        }
        catch(ExQuotaexceed e){
            System.out.println(e.getMessage());
        }
        catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
    @Override
	public void undoMe()
	{
        b_member.antiborrow(b_item);
        if(onholdcheck==false){
            b_item.change_available();
        }
        else if(onholdcheck){
            b_item.change_onhold(b_member, dueday);
        }
        addRedoCommand(this); 
	}
	@Override
	public void redoMe()
	{
        b_member.borrow(b_item);
        b_item.change_borrow(b_member,borrowday);
        addUndoCommand(this);
	}
}