
public class B4358_생태학 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> kinds = new TreeMap<>();
		double sum = 0;
		
		while(sc.hasNextLine()) {
			String tree = sc.nextLine();
			if(tree.equals("")) break;
			
			if(kinds.containsKey(tree)) kinds.replace(tree, kinds.get(tree)+1);
			else kinds.put(tree, 1);
			sum++;
		}

		for(Entry<String, Integer> set : kinds.entrySet()) {
			System.out.printf("%s %.4f \n",set.getKey(),set.getValue()*100/sum);
		}
	}
}
