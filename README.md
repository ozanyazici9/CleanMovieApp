# 🎬 CleanMovieApp

A modern **Android application** for film enthusiasts, built with **Kotlin** and **Jetpack Compose**. CleanMovieApp allows users to explore a wide range of movies — from the latest releases to timeless classics — with rich detail pages powered by IMDB data.

---

## 📸 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/cf8a4432-df6b-4f73-b2a8-4d12979e10ab" width="280"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/42aff12d-1445-49ab-a958-ee3a0fad40a5" width="280"/>
</p>

---

## 🌟 Features

- 🎥 **Movie Listing** — Browse a wide range of films from latest to classics
- 🔍 **Search** — Quickly find any movie by title
- 📖 **Movie Detail** — View rich information including ratings, cast, and synopsis powered by IMDB data
- 🏗️ **Clean Architecture** — Strict separation of concerns with MVVM pattern
- ♻️ **Reusable Compose Components** — Modular and maintainable UI structure
- ⚡ **Async Operations** — Smooth experience with Kotlin Coroutines & Flow

---

## 🧰 Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | Clean Architecture + MVVM |
| DI | Hilt |
| Networking | Retrofit |
| Async | Kotlin Coroutines & Flow |
| Image Loading | Coil |

---

## 🏛️ Architecture

CleanMovieApp strictly follows **Clean Architecture** principles:

```
Presentation Layer  →  Jetpack Compose (UI) + ViewModel
        ↓
Domain Layer        →  Use Cases, Repository Interfaces, Entities
        ↓
Data Layer          →  Retrofit (Remote API), Repository Implementations
```

---

## 🗂️ Project Structure

```
CleanMovieApp/
├── app/                      # App module (entry point)
├── core/
│   ├── common/               # Shared utilities & extensions
│   └── ui/                   # Shared UI components & theme
├── feature/
│   ├── movie_list/           # Movie listing screen
│   │   ├── data/             # API calls, DTOs, mappers
│   │   ├── domain/           # Use cases, repository interface
│   │   └── presentation/     # Compose UI, ViewModel
│   └── movie_detail/         # Movie detail screen
│       ├── data/
│       ├── domain/
│       └── presentation/
└── build-logic/              # Gradle convention plugins
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or newer
- Android SDK 24+
- Kotlin 1.9+

### Installation

```bash
# Clone the repository
git clone https://github.com/ozanyazici9/CleanMovieApp.git

# Open in Android Studio and sync Gradle
# Run on emulator or physical device (API 24+)
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 👤 Author

**Ozan Yazıcı**  
[![GitHub](https://img.shields.io/badge/GitHub-ozanyazici9-181717?style=flat&logo=github)](https://github.com/ozanyazici9)  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Ozan%20Yazıcı-0077B5?style=flat&logo=linkedin)](https://www.linkedin.com/in/ozan-yaz%C4%B1c%C4%B1-a5025a236/)

