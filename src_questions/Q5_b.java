//Question 5
// b)
//        Assume an electric vehicle must go from source city s to destination city d. You can locate many
//        service centers along the journey that allow for the replacement of batteries; however, each service
//        center provides batteries with a specific capacity. You are given a 2D array in which
//        servicecenter[i]=[xi,yj] indicates that the ith service center is xi miles from the source city and
//        offers yj miles after the automobile can travel after replacing batteries at specific service centers.
//        Return the number of times the car's batteries need to be replaced before arriving at the destination.
//        Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
//        Output: 2
//        Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries
//        with a capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a
//        capacity of 40 miles; and ultimately, we may arrive at our destination.

import java.util.Arrays;
import java.util.Comparator;


import java.util.ArrayList;

public class Q5_b {
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        //count is an integer variable initialized to 0.
        int count = 0;
        int cMiles = startChargeCapacity;
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();

        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > cMiles) {
                cMiles = capacities.get(i - 1);
                count++;
            }
        }

        if (cMiles < targetMiles) {
            count++;
        }

        return count;
    }


    public static void main(String[] args) {
        int [][] serviceCenterList={{10,60},{20,30},{30,30},{60,40}};
        Q5_b question1=new Q5_b();
        int fAnswer=question1.numBatteryReplacements(serviceCenterList,100,10);
        System.out.println("The vehicle's batteries require replacement: "+ fAnswer +"times.");

    }
}