# 🏭 WarehouseFlow

> A modern, secure, and efficient inventory management REST API built with **Spring Boot 3**, **Spring Security 6**, and **Hibernate (JPA)**.  
> Designed for developers, students, and businesses who want a clean, extensible backend for managing products, warehouses, suppliers, categories, and stock movements — with full role-based access control.

---

## 🌐 Overview

**WarehouseFlow** simplifies how inventory data flows through a system.  
It’s fast, minimal, and production-ready — handling everything from tracking stock levels to generating warehouse reports.  
The goal: a foundation you can deploy as-is or extend into a complete ERP or e-commerce backend.

---

## 🚀 Key Features

| Category | Description |
|-----------|--------------|
| 🧑‍💼 **User Management** | Built-in admin creation, secure password hashing with BCrypt |
| 🔐 **Security** | Role-based access control using Spring Security 6 |
| ⚙️ **REST API** | Clean, stateless endpoints for all core entities |
| 💾 **Database Layer** | Uses JPA/Hibernate with H2 for testing (easily switchable to MySQL/PostgreSQL) |
| 📦 **Inventory Operations** | Full CRUD for products, categories, suppliers, warehouses, inventory items |
| 📊 **Reports** | Basic analytical endpoints for stock and movement summaries |
| 🧠 **Modern Stack** | Java 21, Spring Boot 3, Maven, REST-first architecture |
| 🧰 **Developer Friendly** | Clean modular structure and ready for deployment or Dockerization |

---

## 🧱 Tech Stack

| Layer | Technology |
|--------|-------------|
| Language | Java 21 |
| Framework | Spring Boot 3.3+ |
| ORM | Hibernate / JPA |
| Security | Spring Security 6 (HTTP Basic Auth + BCrypt) |
| Database | H2 (default), configurable to MySQL / PostgreSQL |
| Build Tool | Maven |
| Testing | JUnit, Spring Test |
| IDE | IntelliJ IDEA / Eclipse / VSCode |

---

## 🗂️ Project Structure

```
warehouseflow/
 ┣ src/main/java/mental/development/inventory_system/
 ┃ ┣ config/                # Spring Security configuration and beans
 ┃ ┣ controller/            # REST controllers for each entity
 ┃ ┣ model/                 # Entity models (Product, Supplier, Warehouse, etc.)
 ┃ ┣ repository/            # JPA repositories for database operations
 ┃ ┣ service/               # Business logic and data handling
 ┃ ┗ WarehouseFlowApp.java  # Main application class
 ┣ src/main/resources/
 ┃ ┣ application.properties # DB & security configuration
 ┃ ┗ data.sql               # Optional seed data
 ┗ pom.xml
```

---

## ⚙️ Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/MentalIllness/WarehouseFlow.git
cd warehouseflow
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

The API will start on:

```
http://localhost:8080
```

---

## 🔐 Authentication & Roles

WarehouseFlow uses **HTTP Basic Authentication** and **BCrypt** for password hashing.  
Users are stored in the database with role assignments (`ROLE_ADMIN`, `ROLE_USER`, etc.).

### Bootstrap Admin Account

The endpoint `/api/users/create-admin` is open (permitAll) and used to create the initial administrator:

```bash
POST /api/users/create-admin
```

After that, use Basic Auth in headers for protected endpoints:

```
Authorization: Basic base64(username:password)
```

---

## 📡 REST API Endpoints

### 🔓 Public Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/products` | Fetch all products |
| GET | `/api/categories` | List product categories |
| GET | `/api/suppliers` | Get supplier information |
| GET | `/api/warehouses` | List warehouses |
| GET | `/api/reports/**` | Public reports (aggregated stock info) |

### 🔒 Admin-Only Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/api/inventory/add` | Add new inventory item |
| PUT | `/api/inventory/update/{id}` | Update existing stock |
| DELETE | `/api/inventory/delete/{id}` | Remove inventory record |
| POST | `/api/products` | Create new product |
| POST | `/api/categories` | Create new category |
| POST | `/api/suppliers` | Create supplier entry |
| GET | `/api/users` | List all users (Admin only) |

---

## 🧾 Example JSON – Product

**POST** `/api/products`

```json
{
  "name": "ThinkPad T14 Gen 5",
  "sku": "TP-T14G5",
  "quantity": 0,
  "price": 1899.99,
  "category": {
    "id": 1,
    "name": "Electronics"
  },
  "supplier": {
    "id": 1,
    "name": "Lenovo Distribution",
    "contactEmail": "dist@lenovo.example",
    "phone": "+359888000111"
  }
}
```

---

## 🧩 Configuration

All app properties are stored in `src/main/resources/application.properties`.

Example:

```properties
spring.application.name=warehouseflow
spring.datasource.url=jdbc:h2:mem:warehouseflowdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

You can easily switch to MySQL/PostgreSQL by updating these properties.

---

## 🧰 Useful Commands

| Command | Description |
|----------|-------------|
| `mvn clean install` | Build project |
| `mvn spring-boot:run` | Run locally |
| `mvn test` | Run tests |
| `mvn package` | Package JAR file |
| `java -jar target/warehouseflow-1.0.jar` | Run built JAR |

---

## 🧪 Testing

The project includes unit and integration test templates for services and controllers.  
Run them via:

```bash
mvn test
```

You can extend them easily for repository-level tests using Spring’s `@DataJpaTest`.

---

## 📦 Deployment

WarehouseFlow is ready for containerization.  
Add a Dockerfile (planned feature) to deploy easily via:

```bash
docker build -t warehouseflow .
docker run -p 8080:8080 warehouseflow
```

Future roadmap includes full Docker Compose setup with MySQL and frontend integration.

---

## 🪶 Roadmap

- [ ] ✅ Role-based access (Admin/User)
- [ ] 🪪 JWT Authentication (planned)
- [ ] 📊 Advanced reporting module
- [ ] 🧮 Inventory analytics dashboards
- [ ] 🐳 Docker support (with MySQL)
- [ ] 🌐 React/Vue frontend
- [ ] 📦 Swagger/OpenAPI documentation

---

## 🤝 Contributing

Contributions are welcome!  
If you want to add new features, fix bugs, or improve documentation:

1. Fork this repo  
2. Create a new branch (`feature/amazing-feature`)  
3. Commit your changes  
4. Push and open a Pull Request  

---

## 👨‍💻 Author

**Mental Development**  
Backend Developer & Law Student @ Varna Free University  
📧 [mental.illness.business@gmail.com](mailto:mental.illness.business@gmail.com)

---

## 📄 License

This project is licensed under the **MIT License**.  
You’re free to use, modify, and distribute it with attribution.

---

## 🌟 Support

If you like **WarehouseFlow**, consider giving it a ⭐ on GitHub — it helps more developers discover the project!
