package filesystem.visitor;

import filesystem.Directory;
import filesystem.Element;
import filesystem.File;

/**
 * Operation 3 (extensibility demo): prints the tree.
 * Added WITHOUT touching File, Directory or Element.
 */
public class TreePrinterVisitor implements Visitor {

    private final StringBuilder output = new StringBuilder();
    private int depth;

    @Override
    public void visitFile(File file) {
        output.append("  ".repeat(depth))
              .append(file.getName())
              .append(" (").append(file.getSizeInBytes()).append(" B)\n");
    }

    @Override
    public void visitDirectory(Directory directory) {
        output.append("  ".repeat(depth)).append(directory.getName()).append("/\n");
        depth++;
        for (Element child : directory.getChildren()) {
            child.accept(this);
        }
        depth--;
    }

    public String getOutput() {
        return output.toString();
    }
}
