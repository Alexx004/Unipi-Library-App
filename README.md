# Unipi Library Management System 

A comprehensive desktop application for managing a university library's daily operations. Built with **Java** and **JavaFX**, this project implements core Object-Oriented Programming (OOP) principles to handle books, student records, active loans, and fine calculations.

Developed as an academic project for the **University of Piraeus (UniPi)**.

##  Features

* **Book Management:** Add new books, edit existing details, search via ISBN, and remove entries. Includes data validation and availability tracking.
* **Student Registry:** Register students with unique IDs, contact info, and department details.
* **Loan System:** Issue books to students, process returns, and track active or delayed loans.
* **Fine Calculation:** Automated penalty tracking for overdue books, utilizing a flexible Interface-based design (`FeePolicy`).
* **Graphical User Interface:** Clean, interactive, and user-friendly GUI built with JavaFX, featuring dynamic forms, table views, and pop-up alerts.

##  Tech Stack & Architecture

* **Language:** Java
* **GUI Framework:** JavaFX
* **Build Tool:** Maven

### OOP Principles Highlighted:
* **Inheritance:** Base `User` class extended by `Student` and `Librarian` entities.
* **Interfaces / Strategy Pattern:** The `FeePolicy` interface (implemented by `StandardFeePolicy`) allows for dynamic and easily modifiable fine calculations without altering core logic.
* **Encapsulation:** Strict use of private fields with public getters/setters, along with robust input validation methods (e.g., regex for emails, ID formatting).
* **Separation of Concerns:** UI logic is separated into distinct `SceneCreator` classes (e.g., `AddStudentSceneCreator`, `EditBookSceneCreator`), keeping the main application loop clean and maintainable.

##  How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Alexx004/Unipi-Library-App.git](https://github.com/Alexx004/Unipi-Library-App.git)
    ```
2.  **Open in your IDE:** Import the project into Eclipse, IntelliJ IDEA, or VS Code as a Maven Project.
3.  **Run the application:** Execute the `App.java` file located in `src/main/java/unipi/library/ui/App.java`.

*PS: Ensure you have the JavaFX SDK configured in your environment or included in your Maven dependencies.*
