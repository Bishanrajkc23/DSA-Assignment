//Question 1
//b)
//        Assume you were hired to create an application for an ISP, and there is n number of network devices,
//        such as routers, that are linked together to provides internet access to home user users. You are given
//        a 2D array that represents network connections between these network devices such that a[i]=[xi,yi] where
//        xi is connected to yi device.  Suppose there is a power outage on a certain device provided as int n
//        represents id of the device on which power failure occurred)), Write an algorithm to return impacted
//        network devices due to breakage of the link between network devices. These impacted device list assists
//        you notify linked consumers that there is a power outage and it will take some time to rectify an issue.
//        Note that: node 0 will always represent a source of internet or gateway to international network..
//Input: edges= {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}}
//        Target Device (On which power Failure occurred): 4
//        Output (Impacted Device List) = {5,7}
//        Explanation: power failure on network device 4 will disconnect 5 and 7 from internet
//

import java.util.*;

public class Q1_b {
    private int n;
    private List<Integer>[] adjList;

    public Q1_b(int n, int[][] edges) {
        this.n = n;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }
    }

    public List<Integer> findImpactedDevices(int target) {
        boolean[] reachable = new boolean[n];
        dfs(0, reachable);
        reachable[target] = false;
        boolean[] impacted = new boolean[n];
        dfs(0, impacted, reachable);
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (!reachable[i] && impacted[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int u, boolean[] visited) {
        visited[u] = true;
        for (int v : adjList[u]) {
            if (!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    private void dfs(int u, boolean[] visited, boolean[] reachable) {
        visited[u] = true;
        for (int v : adjList[u]) {
            if (!visited[v] && reachable[v]) {
                dfs(v, visited, reachable);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        Q1_b network = new Q1_b(8, edges);
        List<Integer> impacted = network.findImpactedDevices(4);
        System.out.println(impacted);  // Output: [5, 7]
    }
}

