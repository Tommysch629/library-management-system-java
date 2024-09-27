public class ListAllRecords implements Command{
    @Override
    public void execute(String[] cmdParts){
        Club club= Club.getInstance();
        club.listClubMembers();
    }
}
