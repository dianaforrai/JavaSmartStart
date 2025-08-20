import java.util.ArrayList;
import java.util.List;

// Component interface
interface FileSystemComponent {
    int getSize();
    void printDetails();
}

// Leaf class: File
class File implements FileSystemComponent {
    private String name;
    private int size; // size in KB

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printDetails() {
        System.out.println("File: " + name + " | Size: " + size + "KB");
    }
}

// Leaf class: Directory (can also be a composite but kept simple for individual directories)
class Directory implements FileSystemComponent {
    private String name;
    private int size; // optional size for simple directory

    public Directory(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printDetails() {
        System.out.println("Directory: " + name + " | Size: " + size + "KB");
    }
}

// Composite class: DirectoryComposite
class DirectoryComposite implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public DirectoryComposite(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    @Override
    public void printDetails() {
        System.out.println("DirectoryComposite: " + name);
        for (FileSystemComponent component : components) {
            component.printDetails();
        }
    }
}


