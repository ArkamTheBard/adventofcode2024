import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Main{
	public static ArrayList<List<Integer>> parse_input(String file) {
		ArrayList<List<Integer>> list_of_lists = new ArrayList<>();
		try {
			FileReader input = new FileReader(file);
			BufferedReader reader = new BufferedReader(input);
			String line = null;
			List<Integer> list1 = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();


			while((line = reader.readLine()) != null) {
				String[] temp_array = line.split("   ");
				list1.add(Integer.parseInt(temp_array[0]));
				list2.add(Integer.parseInt(temp_array[1]));
			}
			list_of_lists.add(list1);
			list_of_lists.add(list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_of_lists;

	}

	public static void main(String args[]) {
		long startTime = System.nanoTime();
		ArrayList<List<Integer>> lists = new ArrayList<>();
		for (String arg : args) {
			lists = parse_input(arg);
		}
		List<Integer> list1 = lists.get(0);
		List<Integer> list2 = lists.get(1);

		Collections.sort(list1);
		Collections.sort(list2);

		int sum = 0;
		
		if(list1.size() == list2.size()) {
			for(int i = 0; i < list1.size(); ++i) {
				int distance = 0;
				if(list1.get(i) > list2.get(i)) {
					distance = list1.get(i) - list2.get(i);
				} else {
					distance = list2.get(i) - list1.get(i);
				}
				sum = sum + distance;
			}
		}
		int sum2 = 0;
		if (list1.size() == list2.size()) {
			for (int i = 0; i < list1.size(); ++i) {
				int currentNumber = list1.get(i);
				int appearances = 0;
				for (int j = 0; j < list2.size(); ++j) {
					if (currentNumber == list2.get(j)) {
						++appearances;
					}
				}
				sum2 = sum2 + (appearances * currentNumber);
			}
		}
		System.out.println(sum);
		System.out.println(sum2);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("Execution in milliseconds: " + duration /1000000);
	}
}
