# XATransaction
1PC transaction management done by spring boot, hibernate jpa (with multiple EntityManager, PlatformTransactionManager &amp; ChainedTransactionManager). Also with 2 Databases on MySql.


1. <project-root>/src/main/resources has the schema.sql and data.sql
  Please execute those queries in your Local MySQL database.
  
2. Schema after you execute queries from schema.sql in your local mysql:
  MySqL
    \_ sbi_database
        \_ sbi_account (table)
    \_ hdfc_database
        \_ hdfc_account (table)
        
3. Execute the data.sql queries as well, and have some dummy data ready.

4. Now run as SpringBootApplication.
5. Finally go to 'XaTransactionApplicationTests.java' to execute credit and debit related operations.
   5.1. Run as JunitTest over 'XaTransactionApplicationTests.java' to test.
