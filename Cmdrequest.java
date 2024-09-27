public class Cmdrequest extends RecordedCommand{
	Club club= Club.getInstance();
    Member b_member;
    Item b_item;
    private String m_id;
    private String i_id;
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
            if(b_item.notonhold_id(m_id)&&b_item.notavailable()&&b_member.requestquotacheck()&&b_item.notborrowed_id(m_id)&&b_member.notyetrequest(i_id)){
                b_member.request(b_item);
                b_item.getrequest(b_member);
                addUndoCommand(this);
		        clearRedoList();
                System.out.println("Done. This request is no. "+b_item.queuelist().size()+" in the queue.");
            }
        }
        catch (ExNotFound e) {
			System.out.println(e.getMessage());
		}
        catch(ExQuotaexceed e){
            System.out.println(e.getMessage());
        }
        catch(Exavailable e){
            System.out.println(e.getMessage());
        }
        catch(Exborrowedbyyou e){
            System.out.println(e.getMessage());
        }
        catch(Exalreadyrequest e){
            System.out.println(e.getMessage());
        }
        catch (ExInsufficientcommand e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
        b_member.antirequest(b_item);
        b_item.backrequest(b_member);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        b_member.request(b_item);
        b_item.getrequest(b_member);
		addUndoCommand(this);
	}
}