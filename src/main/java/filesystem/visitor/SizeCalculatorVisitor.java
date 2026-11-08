package filesystem.visitor;

import filesystem.Directory;
import filesystem.Element;
import filesystem.File;

/** Operation 1: computes the total size of everything it visits. */
public class SizeCalculatorVisitor implements Visitor {

    private long totalSizeInBytes;

    @Override
    public void visitFile(File file) {
        totalSizeInBytes += file.getSizeInBytes();
    }

    @Override
    public void visitDirectory(Directory directory) {
        for (Element child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public long getTotalSizeInBytes() {
        return totalSizeInBytes;
    }
}
