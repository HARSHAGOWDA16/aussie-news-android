# AussieNews — Android News App

A modern Android news application built using **Jetpack Compose** and **MVVM architecture**, focusing on clean structure, scalability, and real-time news consumption.

This project is being developed incrementally as part of a structured training and evaluation exercise.

---

## 🚀 Features Implemented

- Single-Activity architecture with Jetpack Compose
- Bottom navigation (Home, Search, Profile)
- Real-time news feed using public News API (GNews)
- Card-based article layout with images
- Loading state handling
- Clean MVVM architecture

---

## 🏗️ Architecture Overview

The app follows a clean **MVVM pattern**:

UI (Compose)
└── ViewModel
└── Repository
└── Remote API


Key decisions:
- UI layer does not directly access network APIs
- Repository abstracts data source
- ViewModel manages UI state and business logic

---

## 📂 Project Structure

com.example.aussienews
├── data
│ ├── model
│ ├── remote
│ └── repository
│
├── ui
│ ├── components
│ └── screens


---

## 🛠️ Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- MVVM Architecture
- Retrofit + OkHttp
- Kotlin Coroutines & StateFlow
- Coil (image loading)

---

## 🔌 API Used

- **GNews API**
  - Used for fetching real-time news headlines
  - Suitable for Android client applications

> API key is currently hardcoded for development purposes and can be externalized later.

---

## 📈 Current Status

- Core app structure completed
- Home screen displays real-time news with images
- Navigation and data flow fully functional

---

## 🔮 Planned Enhancements

- Category-based filtering
- Article detail screen
- Search functionality
- Login and authentication flow
- Bookmark articles

---

## ▶️ How to Run

1. Clone the repository
2. Open in Android Studio
3. Add API key in `ApiConfig.kt`
4. Run on emulator or physical device

---

## 👨‍💻 Author

**Harsha Hanumaiah**  
Android Developer | Jetpack Compose | MVVM

