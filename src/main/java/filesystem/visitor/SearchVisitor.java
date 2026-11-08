package filesystem.visitor;

import filesystem.Directory;
import filesystem.Element;
import filesystem.File;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/** Operation 2: finds files matching a criterion and remembers their full paths. */
public class SearchVisitor implements Visitor {

    private final Predicate<File> criterion;
    private final Deque<String> currentPath = new ArrayDeque<>();
    private final List<String> matchingPaths = new ArrayList<>();

    public SearchVisitor(Predicate<File> criterion) {
        this.criterion = criterion;
    }

    @Override
    public void visitFile(File file) {
        if (criterion.test(file)) {
            matchingPaths.add(buildPath(file.getName()));
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        currentPath.addLast(directory.getName());
        for (Element child : directory.getChildren()) {
            child.accept(this);
        }
        currentPath.removeLast();
    }

    public List<String> getMatchingPaths() {
        return Collections.unmodifiableList(matchingPaths);
    }

    private String buildPath(String fileName) {
        StringBuilder path = new StringBuilder();
        for (Iterator<String> it = currentPath.iterator(); it.hasNext(); ) {
            path.append(it.next()).append('/');
        }
        return path.append(fileName).toString();
    }
}
