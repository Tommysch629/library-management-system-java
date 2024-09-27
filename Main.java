import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String [] args) throws FileNotFoundException {	
		Scanner inFile=null;
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();

		inFile = new Scanner(new File(filepathname));
		
		String cmdLine1 = inFile.nextLine();
		String[] cmdLine1Parts = cmdLine1.split(" ");
		System.out.println("\n> "+cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]);
		
		while (inFile.hasNext()) {
			try{
				String cmdLine = inFile.nextLine().trim();
				if (cmdLine.equals("")) continue;  
	
				System.out.println("\n> "+cmdLine);
				
				String[] cmdParts = cmdLine.split(" "); 
			
				if (cmdParts[0].equals("register"))
					(new CmdRegister()).execute(cmdParts);
				else if (cmdParts[0].equals("cancelRequest"))
					(new CmdcancelRequest()).execute(cmdParts);
				else if (cmdParts[0].equals("request"))
					(new Cmdrequest()).execute(cmdParts);
				else if (cmdParts[0].equals("checkin"))
					(new Cmdcheckin()).execute(cmdParts);
				else if (cmdParts[0].equals("checkout"))
					(new Cmdcheckout()).execute(cmdParts);
				else if (cmdParts[0].equals("arrive"))
					(new Cmdarrive()).execute(cmdParts);
				else if (cmdParts[0].equals("listMembers"))
					(new CmdListMembers()).execute(cmdParts);
				else if (cmdParts[0].equals("listItems"))
					(new CmdListItem()).execute(cmdParts);
				else if (cmdParts[0].equals("startNewDay"))
					(new CmdStartNewDay()).execute(cmdParts);
				else if (cmdParts[0].equals("undo"))
					RecordedCommand.undoOneCommand();
				else if (cmdParts[0].equals("redo"))
					RecordedCommand.redoOneCommand();
				else{ 
					throw new ExWrongCommand();
				}
			}
			catch (ExWrongCommand e){
				System.out.println("Unknown command - ignored.");
			}
		}
		in.close();
		inFile.close();
	}
}