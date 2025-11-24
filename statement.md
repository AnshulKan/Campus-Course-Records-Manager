# **📄 Project Statement & Scope**

"Efficiency is doing things right; effectiveness is doing the right things."  
This project aims to bring both to university academic management.

## **1\. 🚩 The Problem**

In many educational institutions today, the critical task of course registration and grade management is still handled through disjointed, archaic systems. Whether it's physical paperwork, static Excel sheets shared via email, or legacy software that crashes during registration week, the results are the same:

* **⚠️ Data Inconsistency:** Manual entry leads to duplicate records, lost grades, and "ghost" students.  
* **⏳ Operational Bottlenecks:** Faculty members waste hours manually calculating weighted averages instead of focusing on teaching.  
* **🌑 Lack of Transparency:** Students remain in the dark about real-time seat availability, leading to confusion and frustration.

**The Solution:** A centralized, object-oriented digital system that ensures data integrity and automates the boring stuff.

## **2\. 🎯 Scope of the Project**

The **CCRM (Campus Course & Records Manager)** is designed as a focused, high-impact desktop application.

### **✅ In Scope**

* **User Identity Management:** Secure registration and login for different roles.  
* **Course Lifecycle:** Complete flow from Course Creation → Enrollment → Grading.  
* **Academic Reporting:** Instant generation of transcripts and GPA calculations.  
* **Data Persistence:** Robust local file storage using Java Serialization.

### **❌ Out of Scope (for this version)**

* **Financial Module:** Tuition fee processing or billing.  
* **External Integrations:** Email notifications or SMS alerts.  
* **Mobile Interface:** This is currently a desktop-console exclusive.

## **3\. 👥 Target Users**

| Persona | Role | Needs |
| :---- | :---- | :---- |
| **👩‍🎓 The Student** | **End User** | Needs a self-service portal to browse courses, enroll instantly, and view their GPA without queuing at the registrar's office. |
| **👨‍🏫 The Professor** | **Manager** | Needs tools to create course offerings, view real-time class rosters, and assign grades efficiently. |
| **🏛️ The Registrar** | **Admin** | *(Future Scope)* Needs oversight of the entire system to ensure data integrity. |

## **4\. ⭐ High-Level Features**

### **🔐 Secure Authentication**

Role-based logic ensures strict boundaries: Students cannot modify grades, and Professors cannot alter courses they do not teach.

### **📚 Dynamic Course Catalog**

Real-time visibility on course capacity (e.g., **"Seats: 5/60"**). If a course is full, the system intelligently blocks enrollment.

### **🛡️ Conflict Prevention**

Custom business logic detects and prevents invalid states, such as over-enrollment or duplicate registrations.

### **📊 Automated Intelligence**

No more calculators. The system instantly computes weighted GPAs based on credit hours the moment a grade is entered.