# 🛒 MegaMart – E-Commerce Microservices Backend

A modular e-commerce backend system built using Java and Spring Boot with microservices architecture. The application includes independent services for users (with cart and order functionality) and products. Designed with JWT authentication, service discovery, and secure inter-service communication.

---

## 🛠 Tech Stack

- **Languages**: Java  
- **Frameworks**: Spring Boot, Spring Security  
- **Database**: MySQL  
- **Architecture**: Microservices  
- **Authentication**: JWT (JSON Web Token)  
- **Service Discovery**: Eureka  
- **Communication**: Feign Client  
- **Containerization**: Docker  
- **Deployment**: Render  
- **Build Tool**: Maven  

---

## 🚀 Features

### 🧑‍💼 User Service (`user-service`)
- User registration and login with JWT
- Profile update, delete, and fetch
- **Cart functionality**:
  - Add products to cart
  - Remove items from cart
  - View current cart
- **Order functionality**:
  - Place orders
  - View past order history

### 📦 Product Service (`product-service`)
- Add, update, delete, and retrieve products
- Filter products by category, price, or name

### 🔗 Inter-Service Communication
- Feign clients used for interaction between user and product services
- Eureka for service registration and dynamic discovery


