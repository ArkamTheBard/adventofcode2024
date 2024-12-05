package puzzle_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Main {

    public static List<String> parse_input(String file) {
        List<String> stringMatrix = new ArrayList<>();
        try {
            FileReader input = new FileReader(file);
            BufferedReader reader = new BufferedReader(input);
            String line = null;

            while ((line= reader.readLine()) != null) {
                stringMatrix.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringMatrix;
    }

    public static int word_search_part_2(char[][] matrix) {
        int occurrences = 0;
        String to_match = "mas";
        String to_match_reverse = "sam";
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                String temp_string = "";
                String temp_string2 = "";
                if (Character.toLowerCase(matrix[i][j]) != 'a') {
                    continue;
                }
                if (i == 0) {
                    continue;
                }
                if (j == 0) {
                    continue;
                }
                if (j == matrix[i].length-1 || i == matrix.length-1) {
                    continue;
                }
                temp_string += Character.toString(matrix[i+1][j-1]) + Character.toString(matrix[i][j]) + Character.toString(matrix[i-1][j+1]);
                temp_string2 += Character.toString(matrix[i+1][j+1]) + Character.toString(matrix[i][j]) + Character.toString(matrix[i-1][j-1]);
                if ((temp_string.equalsIgnoreCase(to_match) || temp_string.equalsIgnoreCase(to_match_reverse)) && (temp_string2.equalsIgnoreCase(to_match) || temp_string2.equalsIgnoreCase(to_match_reverse))) {
                    ++occurrences;
                }
            }
        }
        return occurrences;
    }

    public static int word_search(char[][] matrix) {
        int occurrences = 0;
        String to_match = "xmas";
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                String temp_string = "";
                if (Character.toLowerCase(matrix[i][j]) != 'x') {
                    continue;
                }
                if (i > 2) { // check up
                    // only check up if we would not go out of bounds.
                    // check up
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i-1][j]) + Character.toString(matrix[i-2][j]) + Character.toString(matrix[i-3][j]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (j > 2){  // check left
                    // only check left, if not out of bounds
                    // check left
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i][j-1]) + Character.toString(matrix[i][j-2]) + Character.toString(matrix[i][j-3]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (j+3 <= matrix[i].length-1){ // if near end of array, do not check right
                    // check right
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i][j+1]) + Character.toString(matrix[i][j+2]) + Character.toString(matrix[i][j+3]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (i+3 <= matrix.length-1) { // if near end of matrix, do not check down
                    // check down
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i+1][j]) + Character.toString(matrix[i+2][j]) + Character.toString(matrix[i+3][j]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (i+3 <= matrix.length-1 && j > 2) {
                    // check down and left
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i+1][j-1]) + Character.toString(matrix[i+2][j-2]) + Character.toString(matrix[i+3][j-3]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (i+3 <= matrix.length-1 && j+3 <= matrix[i].length-1) {
                    // check down and right
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i+1][j+1]) + Character.toString(matrix[i+2][j+2]) + Character.toString(matrix[i+3][j+3]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (i > 2 && j > 2) {
                    // check up and left
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i-1][j-1]) + Character.toString(matrix[i-2][j-2]) + Character.toString(matrix[i-3][j-3]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
                if (i > 2 && j+3 <= matrix[i].length-1) {
                    // check up and right
                    temp_string += Character.toString(matrix[i][j]) + Character.toString(matrix[i-1][j+1]) + Character.toString(matrix[i-2][j+2]) + Character.toString(matrix[i-3][j+3]);
                    if (temp_string.equalsIgnoreCase(to_match)) {
                        ++occurrences;
                    }
                    temp_string = "";
                }
            }
        }
        return occurrences;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if (args.length == 0) {
            System.err.println("No input provided");
            return;
        }
        List<String> stringMatrix = new ArrayList<>();
        for (String arg : args) {
            stringMatrix = parse_input(arg);
        }
        char[][] arrayMatrix = new char[stringMatrix.size()][];
        for (int i = 0; i < stringMatrix.size(); ++i) {
            char[] temp_arr = stringMatrix.get(i).toCharArray();
            arrayMatrix[i] = temp_arr;
        }
        int occurrences = 0;
        occurrences = word_search(arrayMatrix);
        int occurrences2 = 0;
        occurrences2 = word_search_part_2(arrayMatrix);
        System.out.println(occurrences);
        System.out.println(occurrences2);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Execution in miliseconds: " + duration/1000000);
    }
}