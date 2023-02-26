//Question 7
//
//        b)	Write multithreaded web crawler


import java.util.*;
import java.util.concurrent.*;

public class Q7_b {

    private final ExecutorService executorService;
    private final Set<String> visitedUrls;
    private final Queue<String> unvisitedUrls;

    public Q7_b(int numThreads) {
        this.executorService = Executors.newFixedThreadPool(numThreads);
        this.visitedUrls = ConcurrentHashMap.newKeySet();
        this.unvisitedUrls = new ConcurrentLinkedQueue<>();
    }

    public void crawl(String startUrl) {
        unvisitedUrls.offer(startUrl);
        while (!unvisitedUrls.isEmpty() && visitedUrls.size() < 1000) {
            String url = unvisitedUrls.poll();
            if (!visitedUrls.contains(url)) {
                visitedUrls.add(url);
                executorService.execute(() -> {
                    List<String> links = getLinks(url);
                    for (String link : links) {
                        if (!visitedUrls.contains(link)) {
                            unvisitedUrls.offer(link);
                        }
                    }
                });
            }
        }
        executorService.shutdown();
    }

    private List<String> getLinks(String url) {
        // code for pulling links from a url
        return null;
    }

    public static void main(String[] args) {
        Q7_b crawler = new Q7_b(10);
        crawler.crawl("https://boxnovel.com");
    }
}
