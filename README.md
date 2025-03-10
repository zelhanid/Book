<img width="1338" alt="Screenshot 2025-03-09 at 03 30 19" src="https://github.com/user-attachments/assets/87fb5161-29ee-454c-963a-1400cbd52119" /># Zakaria EL HANID
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


<img width="1338" alt="Screenshot 2025-03-09 at 03 30 19" src="https://github.com/user-attachments/assets/ae342c4a-0a18-4a5e-82c7-209260c63731" />

<img width="1005" alt="Screenshot 2025-03-09 at 03 32 22" src="https://github.com/user-attachments/assets/ef85b071-37d8-476a-8f7d-1d31cd5f8fd8" />

<img width="1004" alt="Screenshot 2025-03-09 at 03 33 00" src="https://github.com/user-attachments/assets/c4d6d6a9-5c8e-430e-8b23-103661258d5f" />



Once checking that tables are well created and injected, you can test all developed endpoints : 
All developed endpoints are described on Swagger :

<img width="1501" alt="Screenshot 2025-03-09 at 14 28 38" src="https://github.com/user-attachments/assets/df82db93-c65c-401a-8110-9a60317a7d12" />

@GetMapping("/api/books"):


<img width="1085" alt="Screenshot 2025-03-09 at 03 33 55" src="https://github.com/user-attachments/assets/a38c0caa-3b28-473c-9ca2-3e3c26004b96" />

@GetMapping("/{id}"):

<img width="1074" alt="Screenshot 2025-03-09 at 03 37 23" src="https://github.com/user-attachments/assets/78b4676e-f9dd-4df2-9e65-bb41dec11ab3" />

@GetMapping("/title"):

<img width="1082" alt="Screenshot 2025-03-09 at 14 37 58" src="https://github.com/user-attachments/assets/8cd992a2-a85f-4ac9-ba5d-68bce0e2367b" />

@PostMapping("/addBook"):

<img width="1082" alt="Screenshot 2025-03-09 at 13 39 07" src="https://github.com/user-attachments/assets/4c09b951-8298-46c9-810f-7bc929a4e3d6" />

@PutMapping("/updateBook/{id}"):

<img width="1077" alt="Screenshot 2025-03-09 at 14 02 13" src="https://github.com/user-attachments/assets/241bae00-3484-4d64-bbf2-cdf8f5adacf9" />


@GetMapping("/authors"):

<img width="1077" alt="Screenshot 2025-03-09 at 14 05 14" src="https://github.com/user-attachments/assets/8273bf7b-c358-49ec-ae31-601bd0242d2c" />

@GetMapping("/getBookISBN"):

<img width="1078" alt="Screenshot 2025-03-09 at 14 19 41" src="https://github.com/user-attachments/assets/28605515-6000-4804-89eb-feebfa1d40ff" />

@GetMapping("/rate"):

<img width="1084" alt="Screenshot 2025-03-09 at 14 21 29" src="https://github.com/user-attachments/assets/64b2c6db-762c-4117-9bc8-ab24a32ac995" />

@GetMapping("/sendMessage"):

<img width="1378" alt="Screenshot 2025-03-09 at 14 26 07" src="https://github.com/user-attachments/assets/4c4f4020-22ea-489b-b924-2696d39363b3" />

