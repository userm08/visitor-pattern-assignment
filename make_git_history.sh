#!/usr/bin/env bash
# Creates an incremental commit history. Run once from the project root:
#   git config user.name "Your Name"; git config user.email "you@example.com"
#   bash make_git_history.sh
set -e
git init -b main 2>/dev/null || git init
c() { git add -A && git commit -m "$1"; }
S=src/main/java/filesystem
git add .gitignore README.md                                && git commit -m "Add README and .gitignore"
git add $S/Element.java $S/visitor/Visitor.java             && git commit -m "Add Element and Visitor interfaces"
git add $S/File.java $S/Directory.java                      && git commit -m "Add File and Directory elements with accept()"
git add $S/visitor/SizeCalculatorVisitor.java               && git commit -m "Add SizeCalculatorVisitor"
git add $S/visitor/SearchVisitor.java                       && git commit -m "Add SearchVisitor with Predicate criterion"
git add $S/Main.java                                        && git commit -m "Add Main client with sample file system"
git add $S/visitor/TreePrinterVisitor.java                  && git commit -m "Add TreePrinterVisitor to demonstrate extensibility"
git add docs                                                && git commit -m "Add UML diagram, sample output and report draft"
git log --oneline
echo "Now: git remote add origin <your-repo-url> && git push -u origin main"
