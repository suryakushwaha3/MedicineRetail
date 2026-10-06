<div align="center">

# 💊 MedicineRetail

### 🛒 Modern Medicine Shopping App for Android

<p>
  <img src="https://img.shields.io/badge/Kotlin-Android-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white"/>
  <img src="https://img.shields.io/badge/Material%203-Design-6750A4?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Hilt-DI-FF6F00?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Retrofit-REST%20API-48B983?style=for-the-badge"/>
</p>

<p>
  <b>MedicineRetail</b> is a modern Android application designed to provide
  users with a simple and convenient way to browse medicines,
  view product details, and place orders.
</p>

<br>

<a href="#-features">Features</a> • <a href="#-architecture">Architecture</a> • <a href="#-tech-stack">Tech Stack</a> • <a href="#-project-structure">Structure</a>

</div>

---

# 📱 About

**MedicineRetail** is the user-side Android application of a medicine retail management ecosystem.

The application allows users to browse available medicines, explore categories, view detailed product information, and place orders through a clean and modern interface.

The project is developed using modern Android technologies with a focus on **scalability, maintainability, performance, and clean architecture**.

---

# ✨ Features

<table>
<tr>
<td align="center">🔐<br><b>Authentication</b></td>
<td align="center">🏠<br><b>Home</b></td>
<td align="center">💊<br><b>Medicines</b></td>
<td align="center">🏷️<br><b>Categories</b></td>
</tr>

<tr>
<td align="center">🔎<br><b>Product Details</b></td>
<td align="center">🛒<br><b>Order Medicines</b></td>
<td align="center">📦<br><b>My Orders</b></td>
<td align="center">👤<br><b>User Profile</b></td>
</tr>
</table>

---

# 🚀 Core Functionality

### 🔐 User Authentication

* User registration
* User login
* Account validation
* Secure API-based authentication
* Authentication state handling

### 🏠 Home

* Modern home interface
* Medicine categories
* Available products
* Promotional content
* Quick access to medicines

### 💊 Medicine Browsing

* Browse medicines
* Explore categories
* View available stock
* Product information
* Easy navigation

### 🔎 Product Details

Users can open a medicine and view its complete information before placing an order.

```text
Product
   ↓
Product Details
   ↓
Quantity
   ↓
Order
```

### 📦 Orders

* Place medicine orders
* View order details
* Track order status
* View previous orders

### 👤 Account

* View user information
* Manage profile
* Account-related operations

---

# 🏗️ Architecture

MedicineRetail follows a modern **MVVM + Repository architecture**.

```text
┌───────────────────────────┐
│     Jetpack Compose UI    │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│         ViewModel         │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│        Repository         │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│        ApiService         │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│    Retrofit + OkHttp      │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│       Backend API         │
└───────────────────────────┘
```

---

# 🔄 Data Flow

```text
User Interaction
       ↓
Compose Screen
       ↓
ViewModel
       ↓
Repository
       ↓
ApiService
       ↓
Retrofit / OkHttp
       ↓
Backend
       ↓
API Response
       ↓
Repository
       ↓
ViewModel
       ↓
StateFlow
       ↓
Compose UI
```

---

# 💉 Dependency Injection

**Hilt** is used to manage application dependencies.

```text
                 Hilt
                  │
        ┌─────────┼─────────┐
        ▼         ▼         ▼
    Retrofit   Repository  ViewModel
        │
        ▼
    ApiService
```

This keeps dependency creation centralized and makes the application easier to maintain and test.

---

# 🛠️ Tech Stack

| Technology             | Purpose                      |
| :--------------------- | :--------------------------- |
| 🟣 **Kotlin**          | Primary programming language |
| 🎨 **Jetpack Compose** | Modern Android UI            |
| 🧩 **Material 3**      | Modern design system         |
| 🏗️ **MVVM**           | Application architecture     |
| 💉 **Hilt**            | Dependency Injection         |
| 🌐 **Retrofit**        | REST API communication       |
| 🔌 **OkHttp**          | Network communication        |
| ⚡ **Coroutines**       | Asynchronous operations      |
| 🔄 **StateFlow**       | Reactive UI state            |
| 🗄️ **Room**           | Local data storage           |
| 🔗 **REST API**        | Backend integration          |

---

# 📂 Project Structure

```text
com.example.medicineretail
│
├── data
│
├── di
│   └── AppModule
│
├── models
│   ├── requestModel
│   └── responseModel
│
├── repository
│
├── Utils
│   └── ResultState
│
├── view
│   ├── auth
│   ├── home
│   ├── product
│   ├── order
│   └── profile
│
├── viewModel
│
├── MainActivity
│
└── MedicineApplication
```

---

# 🌐 Backend Communication

MedicineRetail communicates with the backend using REST APIs.

```text
┌─────────────────────┐
│   MedicineRetail    │
│    Android App      │
└──────────┬──────────┘
           │
           │ REST API
           ▼
┌─────────────────────┐
│     Backend API     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Database       │
└─────────────────────┘
```

The API-based architecture allows the Android application and backend system to remain independent and scalable.

---

# 🎨 UI & Design

The application uses **Jetpack Compose + Material 3** to provide a modern Android user interface.

### Design Principles

* Clean layouts
* Simple navigation
* Responsive UI
* Material 3 components
* Modern cards and buttons
* Consistent spacing
* User-friendly interactions
* Dark theme support

---

# 🔐 Authentication Flow

```text
        Login
          │
          ▼
   Validate Input
          │
          ▼
      REST API
          │
     ┌────┴────┐
     │         │
  Success    Error
     │         │
     ▼         ▼
 Waiting     Message
 Screen
     │
     ▼
    Home
```

---

# 🛒 User Flow

```text
        App
         │
         ▼
      Login
         │
         ▼
       Home
         │
    ┌────┼─────┐
    ▼    ▼     ▼
Category Product Profile
    │     │
    └──┬──┘
       ▼
 Product Details
       │
       ▼
     Order
       │
       ▼
   My Orders
```

---

# 🎯 Project Goals

* 🛒 Provide a simple medicine shopping experience
* 💊 Make medicine discovery easier
* 📦 Simplify medicine ordering
* 🔐 Provide reliable user authentication
* ⚡ Maintain responsive UI and smooth navigation
* 🏗️ Follow scalable Android architecture
* 🔄 Keep API integration clean and maintainable

---

# 🚧 Project Status

<div align="center">

### 🟡 Active Development

MedicineRetail is currently under active development.

New screens, features, API integrations, and improvements are being added progressively.

</div>

---

# 🔮 Future Improvements

* 🔎 Advanced medicine search
* 🛒 Shopping cart
* 💳 Online payment integration
* 📍 Delivery address management
* 🔔 Order notifications
* 📦 Real-time order tracking
* ⭐ Product reviews & ratings
* ❤️ Wishlist
* 🧾 Digital invoices
* 🌙 Complete dark theme
* 📍 Delivery tracking

---

<div align="center">

# 💊 MedicineRetail

### **Browse • Order • Manage**

Built with ❤️ using modern Android development practices.

<br>

⭐ **Star this repository if you like the project!**

</div>
