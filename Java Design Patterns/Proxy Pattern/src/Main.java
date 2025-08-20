import java.util.HashMap;
import java.util.Map;

// FileDownloader Interface
interface FileDownloader {
    void downloadFile(String url);
}

// RealFileDownloader (Concrete Class)
class RealFileDownloader implements FileDownloader {
    @Override
    public void downloadFile(String url) {
        System.out.println("Downloading file from: " + url);
        // Simulate file download
        try {
            Thread.sleep(1000); // simulate network delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Download complete: " + url);
    }
}

// ProxyFileDownloader (Proxy Class)
class ProxyFileDownloader implements FileDownloader {
    private final RealFileDownloader realDownloader = new RealFileDownloader();
    private final Map<String, String> cache = new HashMap<>();

    @Override
    public void downloadFile(String url) {
        if (cache.containsKey(url)) {
            System.out.println("Fetching file from cache: " + url);
        } else {
            logDownload(url);
            realDownloader.downloadFile(url);
            cacheFile(url);
        }
    }

    private void cacheFile(String url) {
        cache.put(url, "File content of " + url); // Simulated caching
        System.out.println("Cached file: " + url);
    }

    private void logDownload(String url) {
        System.out.println("Logging download request for: " + url);
    }
}

