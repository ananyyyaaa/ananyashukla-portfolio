# 📚 LearnSphere – Real-Time Study Collaboration Backend

LearnSphere is a real-time backend system for a study collaboration platform built using Java, Spring Boot, WebSocket, and JWT authentication. It allows multiple users to interact in virtual study rooms with real-time messaging and secure API access.

---

## 🛠 Tech Stack

- **Language**: Java  
- **Frameworks**: Spring Boot, Spring Security, WebSocket  
- **Database**: MySQL  
- **Authentication**: JWT (JSON Web Token)  
- **Communication**: WebSocket (STOMP)  
- **Build Tool**: Maven  
- **Testing & Debugging**: Postman  

---

## 🚀 Features

- 🔐 **JWT Authentication**
  - User registration and login
  - Secure API access using token-based auth

- 💬 **Real-Time Messaging**
  - Enable users to join study rooms
  - Send and receive messages using WebSocket
  - Support for private or group conversations

- 🧾 **API Security**
  - All messaging and room access secured via Spring Security and JWT
  - Role-based access if extended

- 🧠 **Backend-Focused Architecture**
  - Fully decoupled backend that can integrate with any frontend (React, Angular, etc.)
