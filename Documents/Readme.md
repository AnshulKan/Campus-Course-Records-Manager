# **🎓 Campus Course & Records Manager (CCRM)**

**Say goodbye to scattered spreadsheets and hello to streamlined academic management.**

**CCRM** is a robust, Java-based desktop application designed to digitize the core operations of a university. From enrolling students in courses to calculating GPAs instantly, CCRM handles it all with a persistent, object-oriented backend.

## **🚀 Key Features**

| Feature | Description |
| :---- | :---- |
| **👥 Role-Based Access** | Secure login for **Students** (enroll/view grades) and **Professors** (manage courses/grade). |
| **📊 Interactive Dashboards** | Beautifully formatted console tables showing course capacities (e.g., 5/60 seats) and student rosters. |
| **🔒 Smart Enrollment** | Built-in logic prevents over-enrollment using custom exceptions (CourseFullException). |
| **💾 Auto-Save Persistence** | Never lose data\! The system uses **Java Serialization** to save your entire university state to disk automatically. |
| **📈 Instant Transcripts** | Auto-generates official-looking transcripts with weighted GPA calculations. |

## **🛠️ Technology Stack**

* **Core:** Java (JDK 8+)  
* **Architecture:** MVC (Model-View-Controller)  
* **Key Concepts:**  
  * **OOP:** Heavy use of Inheritance (User \-\> Student), Polymorphism, and Encapsulation.  
  * **Collections:** HashMap for O(1) lookups and ArrayList for dynamic storage.  
  * **File I/O:** ObjectOutputStream for data persistence.

## **⚡ Getting Started**

Follow these steps to get CCRM running on your local machine.

### **Prerequisites**

* Java Development Kit (JDK) installed.  
* A terminal or command prompt.

### **1\. Compile the Project**

Navigate to the **root** folder (where src is located) and run:

**For Windows (PowerShell) \- *Recommended***

mkdir bin  
Get-ChildItem \-Recurse \*.java | ForEach-Object { javac \-d bin $\_.FullName }

**For Mac / Linux / Git Bash**

mkdir bin  
javac \-d bin src/\*.java

### **2\. Run the Application**

Launch the app using the class path:

java \-cp bin src.Main

## **🧪 See it in Action (Testing Guide)**

Want to test the limits? Follow this script to verify all features work as expected.

### **🟢 Test Case 1: The Fresh Start**

1. Run the app. It detects no previous data and starts fresh.  
2. Select **Option 2 (Register)**.  
3. Create a **Professor** account (e.g., ID: P1, Name: Dr. Strange).

### **🔵 Test Case 2: The Professor's Workflow**

1. Login as P1.  
2. **Create a Course:** Option 1 \-\> ID: CS101, Name: Magic 101, Credits: 4, Capacity: 2\.  
3. **Verify:** Select Option 2\. You should see a table listing Magic 101 with 0/2 seats filled.  
4. Logout.

### **🟠 Test Case 3: The Student's Journey**

1. Register a **Student** (e.g., ID: S1, Name: Peter Parker).  
2. Login as S1.  
3. **Enroll:** Option 2 \-\> Enter CS101.  
4. **Verify:** Select Option 1\. The seat count for Magic 101 is now 1/2.

### **🔴 Test Case 4: Persistence Check**

1. Exit the application completely.  
2. Restart it.  
3. Login as S1. **Success\!** Your enrollment data is still there.

## **📂 Project Structure**

CCRM\_Project/  
├── src/  
│   ├── Main.java           \# The UI & Entry Point  
│   ├── RecordManager.java  \# The Brains (Controller)  
│   ├── FileHandler.java    \# The Vault (Data Storage)  
│   ├── User.java           \# Abstract Parent  
│   ├── Student.java        \# Concrete Child  
│   └── ...  
├── bin/                    \# Compiled magic (.class files)  
└── Documentation/          \# Reports & UML Diagrams

*Built with ❤️ and ☕ using Java.*