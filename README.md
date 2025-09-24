Campus Course & Records Manager (CCRM)

Overview CCRM is a console-based Java SE application to manage students, courses, enrollments, grades, transcripts, and file operations (import/export/backup). It demonstrates core Java concepts including OOP pillars, interfaces/abstract classes, enums, lambdas, Streams, Date/Time API, NIO.2, recursion, exceptions, and design patterns (Singleton & Builder).

How to Run

Prerequisites: JDK 17+ on Windows.
Compile:
From project root: javac -d out $(Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
Run:
java -cp out edu.ccrm.Main
Project Structure

Packages:
edu.ccrm.cli – Menu-driven CLI
edu.ccrm.domain – Core domain entities and enums
edu.ccrm.service – Services for students/courses/enrollment/transcripts
edu.ccrm.io – Import/Export and Backup (NIO.2)
edu.ccrm.util – Validators, comparators, recursion utilities
edu.ccrm.config – AppConfig (Singleton), DataStore (in-memory)
edu.ccrm.exceptions – Custom exceptions
Minimum Demo Flow

AppConfig loads config (data folder) on start.
CLI menu to manage Students, Courses, Enrollment, Grades.
Enroll students, record grades, print transcript (polymorphism & toString()).
Export data then run Backup to timestamped folder.
Program prints a short platform note (Java SE vs ME vs EE) at exit.
Java Platform Notes

Evolution (brief):

1995: Java 1.0 (Applets)
2004: Java 5 (Generics, Enums)
2011: Java 7 (NIO.2)
2014: Java 8 (Lambdas, Streams, Date/Time API)
2017+: 9–17 (Modules, var, records, switch updates)
Java ME vs SE vs EE (summary):

ME: Mobile/embedded profile, constrained devices.
SE: Core Java platform for desktop/server apps (this project).
EE: Enterprise stack (Servlets, JPA, CDI) built on top of SE for large-scale apps.
JDK, JRE, JVM

JVM: Runtime engine executing bytecode.
JRE: JVM + core libraries to run apps.
JDK: JRE + development tools (javac, javadoc) to build apps.
Windows Install & Eclipse Setup

Install JDK 17+, set JAVA_HOME, verify with java -version and javac -version.
In Eclipse: File → New → Java Project → Add src folder → Create edu.ccrm.Main → Run.
Add your screenshots to screenshots/.
Assertions

Enable with java -ea -cp out edu.ccrm.Main.
Assertions used for invariants (non-null IDs, credit bounds).
