# 🗺️ Android Route Mapping App

An Android app that displays driving routes between two locations using the **Google Directions API**. Built with modern Android technologies like **Jetpack Compose**, **Koin**, and follows **Clean Architecture** and **SOLID** principles.

---

## 🚀 Features

- 🧭 Draws a polyline route between two coordinates
- 📍 Uses Google Maps and Directions API
- 💡 Built with Jetpack Compose UI
- 🧱 MVVM + Clean Architecture
- 🧪 Simple & testable domain logic
- 🧰 Dependency Injection using Koin

---

## 🧪 Tech Stack

| Layer        | Tools & Libraries                         |
|--------------|--------------------------------------------|
| UI           | Jetpack Compose, Google Maps Compose       |
| Domain       | Use Cases, Repository Interface            |
| Data         | Retrofit, Google Directions API            |
| Dependency Injection | Koin                              |
| Architecture | MVVM, Clean Architecture, SOLID            |

---

## 🔧 Setup

### 1. Enable Google Directions API
Make sure you have an API key with the **Directions API** and **Maps SDK for Android** enabled from [Google Cloud Console](https://console.cloud.google.com/).

### 2. Add your API Key
Replace your API key in `local.properties`:

```properties
MAPS_API_KEY=YOUR_API_KEY_HERE
