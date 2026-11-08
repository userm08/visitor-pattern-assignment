package filesystem;

import filesystem.visitor.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Concrete Element and Composite: contains Files and other Directories. */
public class Directory implements Element {

    private final String name;
    private final List<Element> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Directory add(Element child) {
        children.add(child);
        return this;
    }

    /** Read-only view: visitors can read the structure but cannot corrupt it. */
    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDirectory(this);
    }
}
