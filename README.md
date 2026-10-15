# JDBC MySQL INSERT Demo (PreparedStatement)

## Aim
Demonstrate how to **insert data** into a MySQL database using JDBC's **PreparedStatement**, including creating a table if it does not exist.

## Problem Statement
A Java program that:
1. Connects to a MySQL database named `mydatabase`
2. Creates a `users` table if it does not exist (columns: `id`, `name`, `email`)
3. Inserts **two user records** using `PreparedStatement`
4. Displays a success message after insertion
5. Handles any `SQLException`
6. Uses **try-with-resources** for automatic resource management

## Project Structure
```
jdbc-insert-demo/
├── output/                  # Sample output / screenshots
│   └── sample_output.txt
├── src/
│   └── JDBCInsertDemo.java
├── README.md
└── .gitignore
```

## Prerequisites
1. **MySQL Server** running on `localhost:3306`
2. Database `mydatabase` created (`CREATE DATABASE mydatabase;`)
3. **MySQL Connector/J** (JDBC driver) in the classpath

## How to Compile & Run

```bash
# Download MySQL Connector/J and place the jar in a convenient location

cd src
javac JDBCInsertDemo.java

# Run (adjust the path to the connector jar)
java -cp ".:/path/to/mysql-connector-j-8.x.x.jar" JDBCInsertDemo
```

**Important:** Update the constants inside `JDBCInsertDemo.java`:
```java
private static final String JDBC_URL  = "jdbc:mysql://localhost:3306/mydatabase";
private static final String USERNAME  = "root";
private static final String PASSWORD  = "your_password";
```

## Expected Output
```
Connected to MySQL database successfully!
Table 'users' is ready (created if it did not exist).
Inserted 1 row(s): John Doe
Inserted 1 row(s): Jane Doe
Data inserted successfully.
```

## Key Concepts Used
- `DriverManager.getConnection()`
- `PreparedStatement` for both CREATE TABLE and INSERT
- Parameter binding with `setString(index, value)`
- `executeUpdate()` for DDL/DML statements
- `try-with-resources` (auto-closes Connection & PreparedStatements)
- Proper `SQLException` handling

## Constraints Followed
- MySQL database must be running and accessible
- JDBC driver must be in the classpath
- Table is created with `CREATE TABLE IF NOT EXISTS` before insertion
- Uses PreparedStatement (not Statement) for the INSERT operations
