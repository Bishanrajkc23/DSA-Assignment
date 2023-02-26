//b)
//        Given an array of even numbers sorted in ascending order and an integer k,
//        Find the k^th missing even number from provided array
//        Input a[] ={0, 2, 6, 18, 22} k=6
//        Output: 16 examples:
//        Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and kth missing
//        number is on 6th place of the list i.e. 16


//Wrong


public class Q8_b {
    public static int findKthMissingEven(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        int missingCount = arr[high] / 2 - arr[low] / 2 - (arr[0] % 2 == 0 ? 1 : 0);
        if (missingCount < k)
            return arr[high] + (k - missingCount) * 2;
        while (low < high) {
            int mid = (low + high) / 2;
            int leftMissingCount = arr[mid] / 2 - arr[low] / 2 - (arr[low] % 2 == 0 ? 1 : 0);
            if (leftMissingCount >= k)
                high = mid;
            else {
                k -= leftMissingCount;
                low = mid + 1;
            }
        }
        return arr[low - 1] + (k - (arr[low - 1] / 2 - arr[0] / 2 - (arr[0] % 2 == 0 ? 1 : 0))) * 2;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 6, 18, 22};
        int k = 6;
        System.out.println("The k-th missing even number is: " + findKthMissingEven(arr, k));
    }
}
