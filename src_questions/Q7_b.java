//Question 7
//
//        b)	Write multithreaded web crawler


import java.util.*;

public class Q7_b {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    int workingThreads = 0;

    public void crawl() {
        OUTER_LOOP: while(true) {
            String nextUrl;
            synchronized(this) {
                while(queue.isEmpty()) {
                    if(workingThreads == 0) {
                        break OUTER_LOOP;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                nextUrl = queue.poll();
                workingThreads++;
            }
            List<String> URLs = getLinks(nextUrl);

            synchronized(this) {
                for(String newUrl: URLs) {  // 'URLs' instead of 'urls'
                    if(!visited.contains(newUrl)) {
                        queue.offer(newUrl);
                        visited.add(newUrl);
                    }
                }
                workingThreads--;
                notifyAll();
            }
        }
    }

    // An example of how to obtain a list of links on a website
    public List<String> getLinks(String url) {
        List<String> links = new ArrayList<>();
        // Code to fetch links goes here
        return links;
    }

    public static void main(String[] args) {
        // Create a new instance
        Q7_b webCrawler = new Q7_b();

        // Add a starting URL to the queue
        String startingUrl = "https://https://vapemandu.com/";
        webCrawler.queue.offer(startingUrl);
        webCrawler.visited.add(startingUrl);

        // Create an array of worker threads
        Thread[] workers = new Thread[5];
        for (int i = 0; i < workers.length; i++) {
            // Each worker executes the webCrawler instance's crawl() function.
            workers[i] = new Thread(webCrawler::crawl);
            workers[i].start();
        }

        // Wait threads to be reduced
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Visited URLs will be printed.
        System.out.println("Visited URLs:");
        for (String url : webCrawler.visited) {
            System.out.println(url);
        }
    }

}