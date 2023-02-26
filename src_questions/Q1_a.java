//Question 1
//        a)
//        There are n nations linked by train routes. You are given a 2D array indicating routes between
//        countries and the time required to reach the target country, such that E[i]=[xi,yi,ki], where xi
//        represents the source country, yi represents the destination country, and ki represents the time
//        required to go from xi to yi. If you are also given information on the charges, you must pay while
//        entering any country. Create an algorithm that returns the cheapest route from county A to county B
//        with a time constraint.
//
//        Input: edge= {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}}
//        Charges = {10,2,3,25,25,4}
//        Source: 0
//        Destination: 5
//        Output: 64
//        Time Constraint=14 min
//        Note: the path 0, 3, 4, 5 will take minimum time i.e., 13 minutes and which costs around $64.
//        We cannot take path 0,1,2,5 as it takes 15 min and violates time constraint which in 14 min.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1_a {
    static class Edge {
        int x;
        int y;
        int time;

        Edge(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int findCheapestRoute(List<Edge> edges, int[] charges, int source, int destination, int timeConstraint) {
        int n = charges.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (Edge edge : edges) {
            graph[edge.x].add(edge.y);
        }
        for (int i = 0; i < n; i++) {
            System.out.println("graph is "+graph[i]);
        }
        //initializes three arrays: cost, time, and visited, which are used to keep track of information during the execution
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[source] = 10;
        int[] time = new int[n];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[source] = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int minNode = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (minNode == -1 || time[j] < time[minNode])) {
                    minNode = j;
                }
            }
            if (minNode == -1) {
                break;
            }
            visited[minNode] = true;
            // implementing an algorithm to find the minimum cost path in a graph, subject to a constraint on the total time required to traverse the path
            for (int nextNode : graph[minNode]) {
                int finalMinNode = minNode;
                int edgeTime = edges.stream().filter(e -> e.x == finalMinNode && e.y == nextNode).findFirst().get().time;
                int newTime = time[minNode] + edgeTime;
                if (newTime <= timeConstraint && cost[minNode] + charges[nextNode] < cost[nextNode]) {
                    cost[nextNode] = cost[minNode] + charges[nextNode];
                    time[nextNode] = newTime;
                }
            }
        }
        return cost[destination] == Integer.MAX_VALUE ? -1 :cost[destination];

    }
    // Adding edges
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(0, 3, 2));
        edges.add(new Edge(1, 2, 5));
        edges.add(new Edge(3, 4, 5));
        edges.add(new Edge(4, 5, 6));
        edges.add(new Edge(2, 5, 5));
        int[] charges = {10, 2, 3, 25, 25, 4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;
        int result = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("The cheapest cost to reach destination is: " + result);
    }
}
