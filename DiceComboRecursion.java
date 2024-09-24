//Mary Kait Heeren
//Purpose: test the recursive function and compare the outputs to the excel sheet
//Issue: The recursive way i use doesn't account for repeats. Therefore, it does not match the Excel sheet. 

/* 
Idea: First, I tried implementing basic recursion.
Problem: Too many recursive calls occuring.!!!! */

/*public class DiceComboRecursion {
    public static int recursive(int n, int k) {
        // Base case when k < n
        if (k < n) {
            return 0;
        }
        // Base case when k == n
        else if (k == n) {
            return 1;
        // Recursive case for k > n
        } else {
            return recursive(n, k - 1) + recursive(n - 1, k - 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 4;
        System.out.println(recursive(n, k));

    }
}
*/
/*Possible Solution: I decided to try dynamically coding recursion so 
the past recursive calls could be stored and reused for future n and k calls.
*/
public class DiceComboRecursion {

    public static int recursive(int n, int k) {
        int[][] dp = new int[n + 1][k + 1]; //stores n and k values/ results

        // recursion implementation
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j < i) {
                    dp[i][j] = 0; // when k < n: 0 combos
                } else if (j == i) {
                    dp[i][j] = 1; // when k = n: 1 combo
                } else {
                    /*For example dp[3,2]
                     * Option dp[i][j-1]: covers [2 sides][2 pips] = 1
                     * Option dp[i-1][j-1]: covers [1 side][2 pips] which is still n < k.
                     */
                    dp[i][j] = dp[i][j - 1]; //The number of ways to distribute j pips when you don’t add a pip to the current side.
                    if (i > 0 && j > 0) { // issue with negatives so check to make sure they aren't negative
                        dp[i][j] += dp[i - 1][j - 1]; //The number of ways to distribute j - 1 pips across i - 1 sides, then adding a pip to the current side.
                    }
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        for (int i = 3; i < 10; i++) {
            for (int j = i; j < 20; j++) {
                int n = i;
                int k = j;
                // Calculate and print the result using the iterative method
                System.out.println("C(" + n + "," + k + ") = " + recursive(n, k));
            }
        }
    }
}
