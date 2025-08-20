public class CompositePatternDemo {
    public static void main(String[] args) {
        File file1 = new File("Doc1.txt", 10);
        File file2 = new File("Doc2.txt", 20);

        Directory dir1 = new Directory("Images", 50);

        DirectoryComposite rootDir = new DirectoryComposite("Root");
        DirectoryComposite subDir = new DirectoryComposite("SubFolder");

        subDir.addComponent(file2);
        subDir.addComponent(dir1);

        rootDir.addComponent(file1);
        rootDir.addComponent(subDir);

        System.out.println("Printing file system structure:");
        rootDir.printDetails();

        System.out.println("\nTotal size of the file system: " + rootDir.getSize() + "KB");
    }
}
