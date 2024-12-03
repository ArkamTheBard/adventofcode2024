import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static ArrayList<List<String>> parse_input(String file) {
        ArrayList<List<String>> list_of_lists = new ArrayList<>();
        try {
            FileReader input = new FileReader(file);
            BufferedReader reader = new BufferedReader(input);
            String line = null;

            while ((line = reader.readLine()) != null) {
                List<String> temp_list = new ArrayList<>();
                temp_list.add(line);
                list_of_lists.add(temp_list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_of_lists;
    }

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        if (args.length == 0) {
            System.err.println("No input provided");
            return;
        }
        ArrayList<List<String>> lists = new ArrayList<>();
        for (String arg : args) {
            lists = parse_input(arg);
        }
        String oneBigString = "";
        for (List<String> list : lists) {
            oneBigString = oneBigString+list.get(0);
        }
        int sum = 0;
        String cleanedString = oneBigString.replaceAll("don\\'t\\(\\)(?=don\\'t\\(\\))*.*?do\\(\\)\\n*", "");
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)", Pattern.CASE_INSENSITIVE);
        // For Part 1
//        for (List<String> list : lists) {
//            Matcher matcher = pattern.matcher(list.get(0));
//            while(matcher.find()) {
//                sum = sum + (Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
//            }
//        }
        Matcher matcher = pattern.matcher(cleanedString); // part 2
        while(matcher.find()) {
            sum = sum + (Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
        }
        System.out.println(sum);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Execution in milliseconds: " + duration/1000000);

    }

}