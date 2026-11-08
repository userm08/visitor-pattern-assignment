package filesystem;

import filesystem.visitor.Visitor;

/** Element: anything in the file system that a Visitor can visit. */
public interface Element {

    String getName();

    /** First step of double dispatch: the element chooses which visitX() is called. */
    void accept(Visitor visitor);
}
