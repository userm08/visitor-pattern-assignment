# Visitor Pattern — File System (Assignment #6)

Course: ShP-2216 Software Design Patterns, Astana IT University. Language: Java 17.

A small file system (`File`, `Directory`) with operations added through the **Visitor** pattern,
without modifying the element classes.

## Structure
```
src/main/java/filesystem/
├── Element.java                 # Element interface: accept(Visitor)
├── File.java                    # Concrete Element (leaf)
├── Directory.java               # Concrete Element (composite)
├── Main.java                    # Client: builds the structure, runs the visitors
└── visitor/
    ├── Visitor.java             # Visitor interface: visitFile / visitDirectory
    ├── SizeCalculatorVisitor.java   # Operation 1: total size
    ├── SearchVisitor.java           # Operation 2: search by criterion
    └── TreePrinterVisitor.java      # Operation 3: print tree (extensibility demo)
docs/  (UML diagram, sample output)
```

## Build and run
```
mkdir out
javac -d out $(find src -name "*.java")      # Windows PowerShell: javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out filesystem.Main
```
Or open the folder in IntelliJ IDEA and run `Main`.

## How double dispatch works
1. `element.accept(visitor)` — Java picks `File.accept` or `Directory.accept` by the **runtime type of the element**.
2. Inside, `visitor.visitFile(this)` / `visitor.visitDirectory(this)` — the compiler knows `this` is a `File`/`Directory`
   and picks the matching overload; the concrete visitor class is chosen at runtime.

Two dynamic choices (element type + visitor type), no `instanceof`.

See `docs/sample-output.txt` for the program output.
