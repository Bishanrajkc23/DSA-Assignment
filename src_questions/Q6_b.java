//Question 6
//
//        b)	You are given an array of different words and target words. Each character of a word represents
//        a different digit ranging from 0 to 9, and no two character are linked to same digit.
//        If the sum of the numbers represented by each word on the array equals the sum the number represented
//        by the targeted word, return true; otherwise, return false. Note: Provided list of words and targeted
//        word is in the form of equation
//        Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
//        Output: true
//        Explanation:
//        s=6
//        I=5
//        X=0,
//        E=8,
//        V=7,
//        N=2,
//        T=1,
//        W=3,
//        Y=4
//        SIX +SEVEN + SEVEN = TWENTY
//        650 + 68782 + 68782 = 138214

import java.util.HashSet;
import java.util.Set;

public class Q6_b {
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};
    public boolean isSolvable(String[] words, String result) {
        Set<Character> charSet = new HashSet<>();
        int[] charCount = new int[91];
        boolean[] nonLeadingZero = new boolean[91];
        // The range of the "A..Z" chars in ASCII is "65..90."
        for (String word : words) {
            char[] cs = word.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                charSet.add(cs[i]);
                charCount[cs[i]] += POW_10[cs.length - i - 1];
                // units are used to determine charCount.
            }
        }
        char[] cs = result.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
            charSet.add(cs[i]);
            charCount[cs[i]] -= POW_10[cs.length - i - 1]; // units are used to determine charCount.
        }
        boolean[] used = new boolean[10];
        char[] charList = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) charList[i++] = c;
        return backtracking(used, charList, nonLeadingZero, 0, 0, charCount);
    }

    private boolean backtracking(boolean[] used, char[] charList, boolean[] nonLeadingZero, int step, int diff, int[] charCount) {
        if (step == charList.length) return diff == 0;
        // Difference between the word count and the outcome is equal to 0.
        for (int d = 0; d <= 9; d++) {
            // Every character is translated into one digit (0 - 9).
            char c = charList[step];
            if (!used[d]
                    // Each character must correspond to a separate set of numbers.
                    && (d > 0 || !nonLeadingZero[c])) {
                // one integer without the beginning zeros is deciphered.
                used[d] = true;
                if (backtracking(used, charList, nonLeadingZero, step + 1, diff + charCount[c] * d, charCount)) return true;
                used[d] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Q6_b solver = new Q6_b();
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        boolean isSolvable = solver.isSolvable(words, result);
        System.out.println("Is solvable? " + isSolvable);
    }


}