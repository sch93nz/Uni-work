import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author schmidmatt
 *
 */
public class NearestNeighbour {

	/**
	 * @param args
	 */
	private static boolean verbose = false;
	private static boolean debug = false;
	private double Range;
	private List<Nodes> fileInfo;
	private List<Nodes> trainingInfo;
	private List<Results> results;
	private long start;
	public NearestNeighbour(List<String> commands) {
		start(commands.get(0),commands.get(1));
	}

	public NearestNeighbour(String[] args) {
		start(args[0],args[1]);
	}

	private void start(String training, String file) {
		start = System.nanoTime();
		try {

			if(verbose)System.out.println("Loading the data in to memory");
			fileInfo = loadData(file);
			if(verbose)System.out.println("Finished loading the data in to memory");
			if(debug)System.out.println("loaded data size: "+fileInfo.size());


			if(verbose)System.out.println("Loading the training info in to memory");
			trainingInfo = loadData(training);
			if(verbose)System.out.println("Finished loading the training info in to memory");
			if(debug)System.out.println(fileInfo.size());

		} catch (IOException e) {
			e.printStackTrace();
		}

		for( int i=0; i<fileInfo.get(0).getNumValues();i++){
			Range = getRange(i);
			Compare(i);
		}
		printResult();
		printTime(System.nanoTime());

	}

	private void printTime(long E) {
		long time = E-start;
		long millis = time / 1000000l;
		
		String formTime = String.format("%02d:%02d:%02d.%02d", 
				TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) -  
				TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
				TimeUnit.MILLISECONDS.toSeconds(millis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)),
				TimeUnit.MILLISECONDS.toMillis(millis));   
		
		
		
		System.out.println("The operation took: "+formTime+" HH:MM:SS.fff");
	}

	private void printResult() {
		if (verbose) System.out.println("printing the results");
		for(Results r: results){
			System.out.println(r.toString());
		}

	}

	/**
	 * d = math.sqr(sum(a1 - b1)^2/R^2)
	 * @param i
	 */
	private void Compare(int i) {

		if (results== null)	results = new ArrayList<Results>();

		for(Nodes n: fileInfo){
			if (debug) System.out.println(n.toString());
			if (!contain(n.getName())){	results.add(new Results(n.getName(),n.getNumValues()));	}

			for (Nodes t: trainingInfo){

				if (n.getName().equalsIgnoreCase(t.getName())){

					double [] temp = getResult(n.getName()).getValues();

					temp[i] = temp[i] + (Math.pow(n.getValue(i)-t.getValue(i),2)/Math.pow(Range,2));

					getResult(n.getName()).setValues(temp);
				}
			}
		}

		if (verbose) {
			System.out.println("finished getting the sum");
			System.out.println("now square rooting the sum");
		}

		SquareResults(i);

		if (verbose) System.out.println("finished squaring the results");

	}

	private void SquareResults(int i) {
		for (Results r: results){
			r.setExactResult(i, Math.sqrt(r.getExactResult(i)));
		}

	}

	private Results getResult(String name) {
		for (Results n:results){
			if (n.getName().equalsIgnoreCase(name))return n;
		}
		return null;
	}

	private boolean contain(String name) {
		for(Results n: results){
			if (n.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}

	private double getRange(int index) {
		double max=Double.MIN_VALUE;
		double min=Double.MAX_VALUE;

		for (Nodes n: fileInfo){
			if(max <n.getValue(index))max = n.getValue(index);
		}
		if (debug) System.out.println(" Max is :" +max);

		for (Nodes n: trainingInfo){
			if(min >n.getValue(index))min = n.getValue(index);
		}
		if (debug) System.out.println(" Min is :" +min);

		return Math.abs(min - max);
	}

	/**
	 * opens the file reads it all in and saves the data as a list of Nodes were 
	 * Each item has its own node which contains the factors it needs..
	 * @param s
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private List<Nodes> loadData(String s) throws IOException {

		//need to add the debug statements
		//need to add the verbose statements
		if (debug||verbose) System.out.println("loading file "+ s);

		File file = new File(s);

		if (!file.exists()){	throw new IOException();	}

		BufferedReader buff = new BufferedReader(new FileReader(file)); // buffering it as i know this is going to be bad
		Scanner scan = new Scanner(buff);// made it a scanner so i can read in one item at a time

		List<Nodes> list = new ArrayList<Nodes>();

		while(scan.hasNextLine()){

			Nodes node;

			if(debug) System.out.println(list.size());

			double one = -1d;
			double two = -1d;
			double three = -1d;
			double four = -1d;

			if(scan.hasNextDouble()) one = scan.nextDouble();
			if(scan.hasNextDouble()) two = scan.nextDouble();
			if(scan.hasNextDouble()) three = scan.nextDouble();
			if(scan.hasNextDouble()) four = scan.nextDouble();
			String name =scan.nextLine();

			if (debug) System.out.println(" "+one+", "+two+", "+three+", "+four+", "+name);
			if (one >=0d && two >=0d && three >= 0d && four >= 0d & name.length()>0){
				node = new Nodes(one,two,three,four,name);

				if (debug) System.out.println(node.toString());

				list.add(node);
			}
		}

		if (verbose)System.out.println("A total of " + list.size()+ " lines of data was loaded");

		return list;
	}

	public static void main(String[] args) {
		if (args.length <2 || args.length >2){
			if (args.length== 0){
				System.out.println("Please give two files for the paramenters -? for help..");
				System.out.println("Were the first one is the file that contains the data and\n" +
						"and the second one contans the training set..");
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
				new NearestNeighbour(commands);
			}
		}else {
			new NearestNeighbour(args);
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

	private class Nodes{
		private List<Double> values;
		private String name;

		/**
		 * @param sepalLength
		 * @param sepalWidth
		 * @param petalLength
		 * @param petalWidth
		 * @param name
		 */
		public Nodes(double sepalLength, double sepalWidth, double petalLength,
				double petalWidth, String name) {
			super();
			values = new ArrayList<Double>();
			values.add(sepalLength);
			values.add(sepalWidth);
			values.add(petalLength);
			values.add(petalWidth);
			this.name = name;
		}

		public double getValue(int index){
			return values.get(index);
		}

		public int getNumValues(){
			return values.size();
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		public String toString(){
			StringBuffer buff = new StringBuffer();
			buff.append("|");
			for (double d : values){
				buff.append(d+" | ");
			}
			buff.append(name);
			return buff.toString();
		}

	}

	private class Results{
		private String name;
		private double[] values;

		public Results(String n, int numOfValues){
			name = n;
			values = new double[numOfValues];	
			for (int i = 0; i < numOfValues ; i++){
				values[i] = 0d;
			}
		}

		/**
		 * @return the values
		 */
		public double[] getValues() {
			return values;
		}

		/**
		 * @param values the values to set
		 */
		public void setValues(double[] values) {
			this.values = values;
		}

		public void setExactResult(int index, double value){
			values[index]= value;
		}

		public double getExactResult(int index){
			return values[index];
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		public String toString(){
			StringBuffer buff = new StringBuffer();
			buff.append("| ");
			for (double d : values){
				buff.append(d+"\t|\t");
			}
			buff.append(name);

			return buff.toString();
		}

	}
}
