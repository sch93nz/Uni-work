import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author schmidmatt
 *
 */
public class NearestNeighbour {


	/**
	 * d = math.sqr(sum(a1 - b1)^2/R^2)
	 * @param args
	 */
	private static boolean verbose = false;
	private static boolean debug = false;


	
	

	public static void main(String[] args) {
		if (args.length <2 || args.length >2){
			if (args.length== 0){
				System.out.println("Please give two files for the paramenters -? for help..");
			}
			else {
				List<String> commands = new ArrayList<String>();
				for (String s: args){
					s=s.trim();
					if(s.equals("-?")){
						help();
					} else  if (s.equalsIgnoreCase("-v")){
						verbose = true;
					} else if (s.equalsIgnoreCase("-d")){
						debug = true;
					}else {
						commands.add(s);
					}
				}
				
			}
		
		}
		
		
	}


	/**
	 * this is prints out the help instructions of the file
	 */
	private static void help() {
		ArrayList<String> buf = new ArrayList<String>();
		buf.add("\t\t\t||HELP||");
		buf.add("---------------------------------------------------------------------------");
		buf.add("\tThis is the help function which will give you a list of");
		buf.add("\tCommands so that you can put the program in to a more ");
		buf.add("\tdetailed mode.");
		buf.add("___________________________________________________________________________\n");
		buf.add(" -?\t=\tThis is the help command you just called it but i felt\n" +
				"   \t \tthat you should at least see it here.\n");
		buf.add(" -v\t=\tThis is the verbose command it is used if you want lots\n" +
				"   \t \tof output or you just really don't trust the thing.\n");
		buf.add(" -d\t=\tThis is the debug command i will let you have one guess\n" +
				"   \t \twhat it does. Thats right it runs the program in dubg mode not\n" +
				"   \t \tto be confused with verbose as they are different!!\n");
		
		
		for (String s : buf){
			System.out.println(s);
		}

	}

}
