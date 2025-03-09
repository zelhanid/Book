# Zakaria EL HANID
# Phone: 06 00 61 79 08
# Email: zakaria.elhanid@gmail.com 

# Introduction 
The main goal of this project is to build an API that allows you to manage a collection of books and their authors.

# Getting Started
To use this project, just clone it in your workspace.
You should have JDK 17, ZooKeeper and Kafka servers started in your machine.


# Build and Test
To build the project in order to run on your local machine.

When starting the BookApplication, an in-memory database server H2 will start on localhost:8080/h2
A 'books' database with two tables (book and author) will be created, and these two tables will be automatically populated each time the application starts.

![](src/main/resources/img/Screenshot 2025-03-09 at 03.30.19.png)

![](src/main/resources/img/Screenshot 2025-03-09 at 03.32.22.png)

![](src/main/resources/img/Screenshot 2025-03-09 at 03.33.00.png)

Once checking that tables are well created and injected, you can test all developed endpoints : 
All developed endpoints are described on Swagger :

![](src/main/resources/img/Screenshot 2025-03-09 at 14.28.38.png)

@GetMapping("/api/books"):

![](src/main/resources/img/Screenshot 2025-03-09 at 03.33.55.png)

@GetMapping("/{id}"):

![](src/main/resources/img/Screenshot 2025-03-09 at 03.37.23.png)

@GetMapping("/title"):

![](src/main/resources/img/Screenshot 2025-03-09 at 14.37.58.png)

@PostMapping("/addBook"):

![](src/main/resources/img/Screenshot 2025-03-09 at 13.39.07.png)

@PutMapping("/updateBook/{id}"):

![](src/main/resources/img/Screenshot 2025-03-09 at 14.02.13.png)

@GetMapping("/authors"):

![](src/main/resources/img/Screenshot 2025-03-09 at 14.05.14.png)

@GetMapping("/getBookISBN"):

![](src/main/resources/img/Screenshot 2025-03-09 at 14.19.41.png)

@GetMapping("/rate"):

![](src/main/resources/img/Screenshot 2025-03-09 at 14.21.29.png)

@GetMapping("/sendMessage"):

![](src/main/resources/img/Screenshot 2025-03-09 at 14.26.07.png)

