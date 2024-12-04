package puzzle_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

class Main {
	public static ArrayList<List<Integer>> parse_input(String file) {
		ArrayList<List<Integer>> list_of_lists = new ArrayList<>();
		try {
			FileReader input = new FileReader(file);
			BufferedReader reader = new BufferedReader(input);
			String line = null;

			while ((line = reader.readLine()) != null) {
				String[] temp_array = line.split(" ");
				List<Integer> temp_list = new ArrayList<>();
				for (String num : temp_array) {
					temp_list.add(Integer.parseInt(num));
				}
				list_of_lists.add(temp_list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_of_lists;
	}


	public static int safe_reports(List<Integer> list) {
		boolean increasing = false;
		boolean decreasing = false;
		for (int i = 0; i < list.size(); ++i) {
			if (i == list.size() - 1) {
				break;
			}
			if ((Math.abs(list.get(i) - list.get(i+1)) >= 1) && (Math.abs(list.get(i) - list.get(i+1)) <= 3)) {
				if (i == 0) {
					if(list.get(i) < list.get(i+1)) {
						increasing = true;
					}
					else {
						decreasing = true;
					}
				}
				if (increasing == true && list.get(i) > list.get(i+1)) {
					return 0;
				}
				if (decreasing == true && list.get(i) < list.get(i+1)) {
					return 0;
				}
			}
			else {
				return 0;
			}
		}
		return 1;
	}


	public static void main(String args[]) {
		long startTime = System.nanoTime();
		if (args.length == 0) {
			System.err.println("No input provided");
			return;
		}
		ArrayList<List<Integer>> lists = new ArrayList<>();
		for (String arg : args) {
			lists = parse_input(arg);
		}
		int report_count = 0;
		for (List<Integer> list : lists) {
			report_count = report_count + safe_reports(list);
		}
		System.out.println(report_count);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.println("Execution in milliseconds: " + duration/1000000);
	}
}
