# 🧵 Sewing Division Android
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple.svg)](https://kotlinlang.org/)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

The Android mobile application for Sewing Division ("Швейна рота") - a volunteer platform connecting creators of adaptive clothing for wounded military personnel. Our app provides seamless access to tutorials, patterns, and community features for volunteers worldwide.

## 📱 Features
* **Video Learning**: Stream HD masterclass tutorials for adaptive clothing creation
* **Pattern Access**: Download and view detailed sewing patterns
* **Multilingual**: Full support for Ukrainian and English
* **Offline Mode**: Download patterns and tutorials for offline access
* **Community Integration**: Connect with other volunteers
* **Project Tracking**: Monitor your contribution and impact
* **Resource Library**: Organized categories for cyber-wear, armor-wear, and accessories

## 🛠️ Technology Stack
* **Language**: Kotlin
* **Architecture**: MVI with Clean Architecture
* **UI Framework**: Jetpack Compose
* **Database**: Room
* **Dependency Injection**: Hilt
* **Networking**: Retrofit with Coroutines
* **Image Loading**: Coil

## 🚀 Getting Started
### Prerequisites
* Android 8.0+ (API level 26+)
* Android Studio Hedgehog or later
* JDK 17

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/sewing-division-android.git
   cd sewing-division-android
   ```

2. Open in Android Studio

3. Build the project:
   ```bash
   ./gradlew build
   ```

4. Run on your device or emulator

## 📂 Project Structure
```plaintext
app/
├── data/              # Data layer
│   ├── api/          # Remote data sources
│   ├── db/           # Local database
│   └── repository/   # Repository implementations
├── domain/           # Business logic
│   ├── model/       # Domain models
│   ├── repository/  # Repository interfaces
│   └── usecase/     # Use cases
├── presentation/     # UI layer
│   ├── tutorials/   # Tutorial screens
│   ├── patterns/    # Pattern screens
│   ├── profile/     # Profile screens
│   └── common/      # Shared UI components
└── di/              # Dependency injection
```

## 🌍 Impact Statistics
* 100,000+ adaptive clothing items created
* 700+ active volunteers
* 1,800+ individual requests fulfilled
* 100+ medical facilities supported

## 🤝 Contributing
We welcome contributions! Please follow these steps:
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Submit a pull request

## 📱 Connect With Us
* [Instagram](https://www.instagram.com/shveina_rota)
* [Facebook](https://www.facebook.com/profile.php?id=100083407995435)
* [Telegram](https://t.me/shveina_rota)
* [Twitter](https://twitter.com/ShveinaRota)

## 💝 Support Our Mission
* MonoBank: 5375 4112 0381 7304
* [PayPal](mailto:marishka.polo@gmail.com)
* [Support Link](https://send.monobank.ua/jar/5VV7zhDJGY)

## 📄 License
This project is licensed under the MIT License.
