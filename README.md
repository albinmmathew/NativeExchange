# Digital Platform for Micro-Scale Local Producers and Consumers

A modular Java Swing desktop application designed to connect local producers (Vendors) with Consumers (Customers), featuring a robust Role-Based Access Control (RBAC) system and Oracle Database integration.

## 🚀 Features

### Core & Security
*   **Secure Authentication**: Role-based login and registration system.
*   **RBAC (Role-Based Access Control)**: Dynamic dashboard that adapts its interface based on whether the user is an **Admin**, **Vendor**, or **Customer**.
*   **Profile Management**: View profile details and update passwords securely.

### Role-Specific Tools
*   **Customers**: 
    *   Browse available listings with real-time stock filtering.
    *   Submit and track requests for items.
*   **Vendors**: 
    *   Manage inventory: Add, update, or mark items as sold.
    *   Process customer requests (Approve/Reject).
*   **Admins**: 
    *   User governance: Activate/Deactivate user accounts.
    *   Global oversight: Monitor and control all platform listings.

## 🛠️ Technology Stack
*   **Language**: Java (JDK 8+)
*   **UI Framework**: Java Swing (Modular `JPanel` architecture)
*   **Database**: Oracle Database (JDBC Connectivity)
*   **IDE**: NetBeans

## 📂 Project Structure
*   `src/`: Main Java source files.
*   `nbproject/`: NetBeans project configuration.
*   `build/`: Compiled artifacts (ignored by git).
*   `.gitignore`: Configured to exclude IDE-specific and temporary files.

## ⚙️ Setup & Installation
1.  **Clone the Repository**:
    ```bash
    git clone <repository-url>
    ```
2.  **Database Configuration**:
    *   Ensure Oracle Database is running (Service: `FREEPDB1`).
    *   Update `DBConnect.java` with your database credentials if necessary.
3.  **Open in NetBeans**:
    *   Launch NetBeans IDE.
    *   Go to `File > Open Project` and select the project folder.
4.  **Build and Run**:
    *   Right-click the project in NetBeans and select **Clean and Build**.
    *   Click the **Run** button or press `F6`.

## 📄 License
This project is for educational/lab purposes.

---
*Developed as part of the S2 RCSS Java Lab.*
