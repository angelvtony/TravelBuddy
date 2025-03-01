TravelBuddy - Android App

Overview

TravelBuddy is an Android application that helps users plan their trips by selecting pickup and drop-off locations along with their preferred travel dates. The app utilizes Google's Places API for autocomplete suggestions and provides a modern Material Design UI for an enhanced user experience.

Features

Location Autocomplete: Users can enter pickup and drop-off locations using an enhanced input field with autocomplete.

Date Selection: Users can select pickup and drop-off dates using a date picker.

Modern UI: Designed with Material Design components for a sleek and user-friendly experience.

State Management: Uses StateFlow or LiveData to manage UI state efficiently.

Tech Stack

Programming Language: Kotlin

Framework: Android Jetpack Components

UI Components: Material Design, TextInputLayout, MaterialButton

State Management: StateFlow / LiveData

API Integration: Google Places API (for location suggestions)

Setup Instructions

Clone the repository:

git clone https://github.com/your-repository/travelbuddy.git
cd travelbuddy

Open the project in Android Studio.

Add your Google Places API key in AndroidManifest.xml:

<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY_HERE"/>

Sync the Gradle files and build the project.

Run the app on an emulator or a physical device.

Project Structure

TravelBuddy/
│-- app/
│   │-- src/
│   │   │-- main/
│   │   │   │-- java/com/example/travelbuddy/
│   │   │   │   ├── ui/ (UI-related files)
│   │   │   │   ├── viewmodel/ (State management with StateFlow or LiveData)
│   │   │   │   ├── repository/ (API calls and data handling)
│   │   │-- res/
│   │   │   ├── layout/ (XML UI files)
│   │   │   ├── values/ (Strings, colors, themes)
│   │   │-- AndroidManifest.xml
│-- build.gradle
│-- README.md

Contributing

Fork the repository.

Create a new branch for your feature.

Make your changes and commit them with a meaningful message.

Push your branch and create a pull request.


https://github.com/user-attachments/assets/f9bcda21-e5fd-4502-bcea-200cf8624462


License

This project is licensed under the MIT License. See the LICENSE file for details.




