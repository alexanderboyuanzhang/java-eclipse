
# JDBC Example – MySQL, Oracle
Java Database Connectivity or JDBC API provides industry-standard and database-independent connectivity between the java applications and relational database servers. Just like java programs that we can “write once and run everywhere”, JDBC provides framework to connect to relational databases from java programs.

## JDBC Example
JDBC API is used to achieve following tasks:
- Establishing a connection to relational Database servers like Oracle, MySQL etc. JDBC API doesn’t provide framework to connect to NoSQL databases like MongoDB.
- Send SQL queries to the Connection to be executed at database server.
- Process the results returned by the execution of the query.
We will look into JDBC MySQL Example as well as JDBC Oracle Example. We will read database configuration from property file to make our code loosely coupled from database drivers.

## JDBC Driver
JDBC API consists of two parts – first part is the JDBC API to be used by the application programmers. Second part is the low-level API to connect to database server. First part of JDBC API is part of standard java packages in java.sql package.

For second part there are four different types of JDBC drivers:

1. **JDBC-ODBC Bridge plus ODBC Driver** (Type 1): This driver uses ODBC driver to connect to database servers. We should have ODBC drivers installed in the machines from where we want to connect to database, that’s why this driver is almost obsolete and should be used only when other options are not available.

2. **Native API partly Java technology-enabled driver** (Type 2): This type of driver converts JDBC class to the client API for the RDBMS servers. We should have database client API installed at the machine from which we want to make database connection. Because of extra dependency on database client API drivers, this is also not preferred driver. 

3. **Pure Java Driver for Database Middleware** (Type 3): This type of driver sends the JDBC calls to a middleware server that can connect to different type of databases. We should have a middleware server installed to work with this kind of driver. This adds to extra network calls and slow performance. Hence this is also not widely used JDBC driver.

4. **Direct-to-Database Pure Java Driver** (Type 4): This is the preferred driver because it converts the JDBC calls to the network protocol understood by the database server. This solution doesn’t require any extra APIs at the client side and suitable for database connectivity over the network. However for this solution, we should use database specific drivers, for example OJDBC jars provided by Oracle for Oracle DB and MySQL Connector/J for MySQL databases.

Before starting with the jdbc example, we need to do some prep work to have some data in the database servers to query.

We will write program to connect to database server and run a simple jdbc query and process the results. For showing how we can achieve loose-coupling in connecting to databases using JDBC API, I will use Oracle and MySQL database systems.

Run below SQL scripts to create the table and insert some dummy values in the table.

	--mysql create table
	create table Users(
	  id  int(3) primary key,
	  name varchar(20),
	  email varchar(20),
	  country varchar(20),
	  password varchar(20)
	  );
	  
	--oracle create table
	create table Users(
	  id  number(3) primary key,
	  name varchar2(20),
	  email varchar2(20),
	  country varchar2(20),
	  password varchar2(20)
	  );
	    
	--insert rows
	INSERT INTO Users (id, name, email, country, password) 
	VALUES (1, 'Pankaj', 'pankaj@apple.com', 'India', 'pankaj123');
	INSERT INTO Users (id, name, email, country, password) 
	VALUES (2, 'Boyuan', 'bzhang@gmail.com', 'India', 'pankaj123');	
	INSERT INTO Users (id, name, email, country, password) 
	VALUES (4, 'David', 'david@gmail.com', 'USA', 'david123');
	INSERT INTO Users (id, name, email, country, password) 
	VALUES	(5, 'Raman', 'raman@google.com', 'UK', 'raman123');
	commit;

Notice that datatypes in Oracle and MySQL databases are different, that’s why I have provided two different SQL DDL queries to create Users table. However both the databases confirms to SQL language, so insert queries are same for both the database tables.

## JDBC Example – Database Drivers
Make sure you are using the correct version of the java drivers according to your database server installation version. Usually these jars shipped with the installer, so you can find them in the installation package. If you have maven based application, you can use below dependencies too.

	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.0.5</version>
	</dependency>
	
	<!-- You need to install ojdbc6 jar manually to your maven repository -->
	<dependency>
	    <groupId>com.oracle</groupId>
	    <artifactId>ojdbc6</artifactId>
	    <version>11.2.0.1.0</version>
	</dependency>

## JDBC Database Configuration Property File
We will read the database configuration details from the property files. This will help us in switching from Oracle to MySQL database easily and vice versa. All we would need is to change the property details.

	#mysql DB properties
	#DB_DRIVER_CLASS=com.mysql.jdbc.Driver
	#DB_URL=jdbc:mysql://localhost:3306/UserDB
	#DB_USERNAME=pankaj
	#DB_PASSWORD=pankaj123
	
	#Oracle DB Properties
	DB_DRIVER_CLASS=oracle.jdbc.driver.OracleDriver
	DB_URL=jdbc:oracle:thin:@localhost:1571:MyDBSID
	DB_USERNAME=scott
	DB_PASSWORD=tiger

Database configurations are the most important details when using JDBC API. The first thing we should know is the Driver class to use. For Oracle database, driver class is *oracle.jdbc.driver.OracleDriver*. For MySQL database, driver class is *com.mysql.jdbc.Driver*. You will find these driver classes in their respective driver jar files. Both of these implement JDBC *java.sql.Driver* interface.

The second important part is the database connection URL string. Every database driver has it’s own way to configure the database URL but all of them have host, port and Schema details in the connection URL.

MySQL database connection String format is *jdbc:mysql://<HOST>:<PORT>/<SCHEMA>*.

Oracle database connection string format is *jdbc:oracle:thin:@<HOST>:<PORT>:<SID>*.

The other important details are database username and password details to be used for connecting to the database server.

### JDBC Example Program

package com.mysql.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;

	public class DBConnection {
	
		public static Connection getConnection() {
			Properties props = new Properties();
			FileInputStream fis = null;
			Connection con = null;
			try {
				fis = new FileInputStream("db.properties");
				props.load(fis);
	
				// load the Driver Class
				Class.forName(props.getProperty("DB_DRIVER_CLASS"));
	
				// create the connection now
				con = DriverManager.getConnection(props.getProperty("DB_URL"), props.getProperty("DB_USERNAME"),
						props.getProperty("DB_PASSWORD"));
			} catch (IOException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
	}

Above jdbc example program is really simple. First we are reading database configuration details from the property file and then loading the JDBC driver and using DriverManager to create the connection. Notice that this code use only Java JDBC API classes and there is no way to know that it’s connecting to which type of database. This is also a great example of writing code for interfaces methodology.

The important code to notice is the *Class.forName()* method call, this is the **Java Reflection** method to create the instance of the given class. You might wonder why we are using Reflection and not new operator to create the object and why we are just creating the object and not using it.

The first reason is that using reflection to create instance helps us in writing loosely-coupled code that we can’t achieve if we are using new operator. In that case, we could not switch to different database without making corresponding code changes.

### JDBC Statement and ResultSet
Here is a simple jdbc example program where we are using the JDBC Connection to execute SQL query against the database and then processing the result set.

	package com.mysql.jdbc;
	
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	
	public class DBConnectionTest {
		private static final String QUERY = "select id,name,email,country,password from Users";
	
		public static void main(String[] args) {
	
			// using try-with-resources to avoid closing resources (boiler plate code)
			try (Connection con = DBConnection.getConnection();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(QUERY)) {
	
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String country = rs.getString("country");
					String password = rs.getString("password");
					System.out.println(id + "," + name + "," + email + "," + country + "," + password);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


Notice that we are using **Java try-with-resources** feature to make sure that resources are closed as soon as we are out of try-catch block.

JDBC Connection, Statement and ResultSet are expensive resources and we should close them as soon as we are finished using them.

*Connection.createStatement()* is used to create the Statement object and then *executeQuery()* method is used to run the query and get the result set object.

First call to ResultSet *next()* method call moves the cursor to the first row and subsequent calls moves the cursor to next rows in the result set. If there are no more rows then it returns false and come out of the while loop. We are using result set *getXXX()* method to get the columns value and then writing them to the console.

When we run above jdbc example test program, we get following output.

	1,Pankaj,pankaj@apple.com,India,pankaj123
	2,Boyuan,bzhang@gmail.com,China,pass
	4,David,david@gmail.com,USA,david123
	5,Raman,raman@google.com,UK,raman123







#### Source
https://www.journaldev.com/2471/jdbc-example-mysql-oracle