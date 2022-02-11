### Run Project

Before project is run, please ensure to create a folder called 
`.env` all values needed for the env file is in the env.example file. i.e for value `DB_USERNAME=<yourdb_username>` is DB_USERNAME=root where root is the username for my database.

Please not that for now on each `mvn clean test -X` run, you will need to clear your database table as data gets deleted in the db table.

to run project, firstly run `mvn clean test -X`, if all test cases pass, you can proceed to runing `mvn exec:java`. Note that failed test cases will not a build a new `WAR` file.  