package filesystem.visitor;

import filesystem.Directory;
import filesystem.File;

/** Visitor interface: one visitX() method per concrete element type. */
public interface Visitor {

    void visitFile(File file);

    void visitDirectory(Directory directory);
}
