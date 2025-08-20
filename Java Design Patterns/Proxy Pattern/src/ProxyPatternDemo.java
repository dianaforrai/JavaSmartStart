public class ProxyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Using RealFileDownloader ===");
        FileDownloader realDownloader = new RealFileDownloader();
        realDownloader.downloadFile("http://example.com/file1.txt");

        System.out.println("\n=== Using ProxyFileDownloader ===");
        FileDownloader proxyDownloader = new ProxyFileDownloader();
        proxyDownloader.downloadFile("http://example.com/file1.txt");
        proxyDownloader.downloadFile("http://example.com/file2.txt");
        proxyDownloader.downloadFile("http://example.com/file1.txt"); // Cached
    }
}
