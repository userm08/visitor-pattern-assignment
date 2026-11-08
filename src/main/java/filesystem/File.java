package filesystem;

import filesystem.visitor.Visitor;

/** Concrete Element: a leaf of the tree. */
public class File implements Element {

    private final String name;
    private final long sizeInBytes;

    public File(String name, long sizeInBytes) {
        if (sizeInBytes < 0) {
            throw new IllegalArgumentException("Size must not be negative");
        }
        this.name = name;
        this.sizeInBytes = sizeInBytes;
    }

    @Override
    public String getName() {
        return name;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitFile(this); // second step: the compiler picks visitFile(File) because 'this' is a File
    }
}
