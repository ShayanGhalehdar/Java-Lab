# Java Programming Lab

Selected lab assignments from a Java programming course at Sharif University of Technology. Each lab is a self-contained mini-project that introduces a different aspect of the language and standard library.

## Labs

| Lab | Topic | What it covers |
|---|---|---|
| 1 | Java Basics | Primitive types, arithmetic, control flow, console I/O. |
| 2 | OOP — Courseware | `Course`, `Student` classes; encapsulation, composition, course enrollment. |
| 3 | Find a Path in a Graph | Graph representation and shortest/connected-path search. |
| 4 | Files & Exceptions | Reading/writing files, custom exceptions, plus a Maze and Tree sub-task. |
| 5 | — | Sorting / collections lab. |
| 6 | Parallel Programming & Multi-threading | `Thread`, `Runnable`, synchronization, concurrent task execution. |
| 7 | — | Final synthesis lab. |

## Repository Structure

```
Lab1. Java Basics/
Lab2. OOP(creating a courseware)/
Lab3. Find a Path in a Graph/
Lab4. Files & Exceptions/
Lab5/
Lab6. Parallel Porgramming & Multi-threading/
Lab7/
```

Several labs are IntelliJ projects (with `.iml` + `src/` + `out/`); a couple are Ant/NetBeans projects (`build.xml`, `manifest.mf`, `nbproject/`).

## How to Build and Run

For IntelliJ-style labs (Lab3, 5, 7):

```bash
cd "Lab3. Find a Path in a Graph/src"
javac *.java
java Main   # or whichever class has main()
```

For NetBeans/Ant labs (Lab4, 6):

```bash
cd "Lab4. Files & Exceptions"
ant run
```
