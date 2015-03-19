# AccessDB

Summary
The main objective of this Java program is to connect database with Java by JDBC and SQL statement. 

Getting stated:
- Install the Java SE SDK 
- Install the database management system (DBMS)
- Install a JDBC driver 
- Install Apache 
- Install Eclipse if needed 

The data file are presented by .data files with field names at the first row and real data in the following rows. In order to input all data files into database and do some queries, three steps are needed:
- Connect Java with database first
- Create data tables in database by SQL statements
- Read .data files into database by formatted SQL statements
- Query statements  

JavaAssessment class contains main method that is used to run the whole program and output the solution. DatabaseAcc class is for accessing the database using the JDBC and MySQL. Data files are read in this class, and all queries are included with separate methods. FileReader class is to get data from the .data files and then format in a way which can be used to for database.
