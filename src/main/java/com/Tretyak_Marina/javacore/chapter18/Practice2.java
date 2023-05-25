package main.java.com.Tretyak_Marina.javacore.chapter18;

import java.util.*;

public class Practice2 {
    public static boolean isContainsBrackets (String str, char[] b1, char[] b2) { //checking for the presence of brackets in a string
        boolean result = false;
        for (int i = 0; i < b1.length; i++) {
            boolean res1 = str.contains(String.valueOf(b1[i]));
            boolean res2 = str.contains(String.valueOf(b2[i]));
            result = result || res1 || res2;
        }
        return result;
    }

    public static boolean validateBrackets(String input) {
        boolean answer = true;
        char[] opening_brackets = {'(', '[', '{'};
        char[] closing_brackets = {')', ']', '}'};
        int ob = 0;             //var for position of opening bracket
        int cb = 1;             //var for position of closing bracket
        int dif = 0;            //difference between cb and ob
        boolean check = isContainsBrackets(input, opening_brackets, closing_brackets);

        while ((dif + cb - 1 < input.length()) && (check)) {
            String str = input.substring(0, ob) + input.substring(dif + cb - 1);

            int[] min = {str.length(), -1};                             //search for the first closing bracket: [0] - position of bracket, [1] - type of bracket
            for (int i = 0; i < closing_brackets.length; i++) {
                int pos = str.indexOf(closing_brackets[i]);
                if ((pos < min[0]) && (pos != -1)) {
                    min[0] = pos;
                    min[1] = i;
                }
            }

            String str_for_max = str.substring(0, min[0]);
            int[] max = {-1, -1};                                       //search for the last opening bracket before the first closing one: [0] - position of bracket, [1] - type of bracket
            for (int i = 0; i < opening_brackets.length; i++) {
                int pos = str_for_max.lastIndexOf(opening_brackets[i]);
                if (pos > max[0]) {
                    max[0] = pos;
                    max[1] = i;
                }
            }

            if (min[1] == max[1]) {         //checking if the found brackets are brackets of the same type
                ob = max[0];
                cb = min[0];
            } else {
                answer = false;
                dif += input.length();
            }

            dif += cb - ob + 1;

            check = false;
            if (cb < str.length()) {
                check = isContainsBrackets(str.substring(0, ob) + str.substring(cb + 1), opening_brackets, closing_brackets);
            }
        }

        if (check) {
            answer = false;
        }
        
        return answer;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Enter string: ");
        String input = console.nextLine();

        System.out.println("Are brackets is validate: " + validateBrackets(input));
    }
}