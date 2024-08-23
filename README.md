Instruction received
----------------------------------------------------------------------------------------
    • Read the requirements defined below.
    • Feel free to research solutions on the internet, but don't copy and paste codes.
    • Do track your progress in git and submit the project with git history.
    • Language – Java / C# [Feel free to choose the language which candidate is well versed] 
    • Create an API layer
    • Any UI framework like Angular \ React \ Vue
    • Database SQL / SQLite / PostgreSQL [Any database which candidate is well versed]
    • Use your OOPs skills as necessary
    Requirements: 
    - Create a single UI page with Username and Password
    - Perform CRUD operation
    - List the data
    - Exception handling
----------------------------------------------------------------------------------------

url to get to UI: 
----------------------------------------------------------------------------------------
    http://localhost:8080/menu
    or
    http://localhost:8080/menu?name=John
----------------------------------------------------------------------------------------

url to api:
----------------------------------------------------------------------------------------
    GET
    http://localhost:8080/fetchUsers

    GET
    http://localhost:8080/user/jui78
    
    
    POST
    http://localhost:8080/user
    body
       {
            "userId": "hat067",
            "password": "YFY$^%$^"
        }
    	
    PUT
    http://localhost:8080/user/jui78
    body
       {
            "userId": "hat067",
            "password": "YFY$^%$^"
        }
    
    
    DELETE
    http://localhost:8080/user/jik786
----------------------------------------------------------------------------------------

Database 
----------------------------------------------------------------------------------------
    Type H2
    Table Name: USERDB
    H2 database console  - http://localhost:8080/h2-console
    Datasource URL = jdbc:h2:mem:testdb
----------------------------------------------------------------------------------------
