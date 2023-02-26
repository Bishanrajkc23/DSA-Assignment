//Question 4
//b)	Given a linked list containing an integer value, return the number of steps required
// to sort an array in ascending order by eliminating elements at each step
// Note: at each step remove element a[i] where a[i-1]> a[i]


public class Q4_b {

    public static int stepsToSort(ListNode head) {
        int steps = 0;
        boolean sorted = false;
        // while loop
        while (!sorted) {
            sorted = true;
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null && curr.next != null) {
                if (curr.val > curr.next.val) {
                    if (prev == null) {
                        head = curr.next;
                    } else {
                        prev.next = curr.next;
                    }
                    curr = curr.next;
                    sorted = false;
                } else {
                    prev = curr;
                    curr = curr.next;
                }
            }
            steps++;
        }

        return steps;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        int steps = stepsToSort(head); // Print output
        System.out.println("No. of steps required is : " + steps);
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}