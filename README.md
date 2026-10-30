# Stock Management System

A console-based Stock Management application developed using Java and Microsoft SQL Server.

The project manages product information and tracks stock movements through database operations using JDBC.

## Technologies

- Java
- Microsoft SQL Server
- JDBC
- IntelliJ IDEA
- Git & GitHub

## Features

- Database connectivity using JDBC
- Product management
- Product listing
- Stock movement tracking
- Recording stock IN and OUT transactions
- Relational database structure
- Data management using DAO architecture

## Project Structure

StockManagement/
│
├── src/
│   ├── Main.java
│   ├── Product.java
│   ├── ProductDAO.java
│   ├── DBConnection.java
│   └── ConnectionTest.java
│
├── database/
│   └── schema.sql
│
└── README.md


## Architecture

The project uses a DAO (Data Access Object) approach to separate database operations from the main application logic.

| Class | Responsibility |
|---|---|
| Main.java | Application entry point and console menu |
| Product.java | Represents product data |
| ProductDAO.java | Handles product-related database operations |
| DBConnection.java | Establishes SQL Server connection |
| ConnectionTest.java | Tests database connectivity |

## Database Design

The application uses Microsoft SQL Server with two related tables.

### Products

Stores product information.

| Column | Data Type | Description |
|---|---|---|
| ProductID | INT | Primary Key |
| NAME | NVARCHAR(100) | Product name |
| PRICE | DECIMAL(10,2) | Product price |
| QUANTITY | INT | Stock quantity |

### StockMovements

Stores stock transaction records.

| Column | Data Type | Description |
|---|---|---|
| Id | INT | Primary Key |
| ProductId | INT | Foreign Key |
| MovementType | VARCHAR(10) | IN / OUT |
| Quantity | INT | Movement quantity |
| MovementDate | DATETIME2 | Transaction date |

**Relationship:** StockMovements.ProductId references Products.ProductID.

Database creation script: `database/schema.sql`

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/StockManagement.git
```

### 2. Create the database

Open SQL Server Management Studio and execute the SQL script located at:

```text
database/schema.sql
```

The script creates the required tables inside the StockDB database.

### 3. Configure database connection

Update the database connection settings in `DBConnection.java` according to your local SQL Server configuration.

Do not commit database passwords or other sensitive credentials to GitHub.

### 4. Run the application

Open the project in IntelliJ IDEA and run:

```text
Main.java
```

## Concepts Practiced

- Object-Oriented Programming (OOP)
- Encapsulation
- DAO Design Pattern
- JDBC
- SQL Server
- PreparedStatement
- ResultSet
- Relational Database Design
- Primary Key and Foreign Key
- Data Integrity Constraints
- Git Version Control

## Project Purpose

This project was developed to gain practical experience in Java application development, relational database design, and database integration.

It demonstrates the implementation of a Java application that interacts with SQL Server through JDBC.

---

**Project Type:** Learning / Portfolio Project  
**Language:** Java  
**Database:** Microsoft SQL Server
