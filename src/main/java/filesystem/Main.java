package filesystem;

import filesystem.visitor.SearchVisitor;
import filesystem.visitor.SizeCalculatorVisitor;
import filesystem.visitor.TreePrinterVisitor;

public class Main {

    public static void main(String[] args) {
        Element root = buildSampleFileSystem();

        TreePrinterVisitor printer = new TreePrinterVisitor();
        root.accept(printer);
        System.out.println("=== File system structure ===");
        System.out.print(printer.getOutput());

        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        System.out.println("\n=== SizeCalculatorVisitor ===");
        System.out.println("Total size: " + sizeCalculator.getTotalSizeInBytes() + " bytes");

        SearchVisitor javaFiles = new SearchVisitor(f -> f.getName().endsWith(".java"));
        root.accept(javaFiles);
        System.out.println("\n=== SearchVisitor: *.java ===");
        javaFiles.getMatchingPaths().forEach(System.out::println);

        SearchVisitor bigFiles = new SearchVisitor(f -> f.getSizeInBytes() > 5000);
        root.accept(bigFiles);
        System.out.println("\n=== SearchVisitor: size > 5000 B ===");
        bigFiles.getMatchingPaths().forEach(System.out::println);
    }

    private static Element buildSampleFileSystem() {
        return new Directory("project")
                .add(new File("README.md", 1200))
                .add(new Directory("src")
                        .add(new File("Main.java", 2500))
                        .add(new File("Utils.java", 1800))
                        .add(new Directory("model")
                                .add(new File("User.java", 900))
                                .add(new File("Order.java", 1400))))
                .add(new Directory("assets")
                        .add(new File("logo.png", 15400))
                        .add(new File("banner.jpg", 48200)))
                .add(new Directory("empty"));
    }
}
