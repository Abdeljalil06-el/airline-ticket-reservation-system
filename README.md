<div align="center">

# ✈️ Airline Ticket Reservation System

### A full-featured Java desktop application with AI voice assistant
### and Python ML-based flight price prediction

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Flask](https://img.shields.io/badge/Flask-000000?style=for-the-badge&logo=flask&logoColor=white)
![Scikit-learn](https://img.shields.io/badge/Scikit--learn-F7931E?style=for-the-badge&logo=scikit-learn&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![XAMPP](https://img.shields.io/badge/XAMPP-FB7A24?style=for-the-badge&logo=xampp&logoColor=white)

</div>

---

## ✨ Overview

**Airline Ticket Reservation System** is a full-stack desktop application
built with **Java Swing** and **MySQL**, integrated with a **Python Flask
REST API** for machine learning based flight price prediction. The system
features an AI-powered voice assistant using **Google Dialogflow**, offline
speech recognition using **Vosk**, and is trained on real Bangladesh
domestic flight data with **~85% prediction accuracy**.

---

## 🚀 Features

| Feature | Description |
|---|---|
| 🔐 Secure Login | AES-encrypted saved credentials with remember me |
| 🔢 OTP Verification | One-time password dialog for extra security |
| ✈️ Flight Search | Search by source, destination, date and class |
| 🤖 ML Price Prediction | Python Random Forest model predicts ticket prices |
| 🎙️ AI Voice Assistant | Google Dialogflow chatbot with voice input and output |
| 🗣️ Speech Recognition | Offline voice input using Vosk speech model |
| 🔊 Text to Speech | Google TTS and FreeTTS for voice responses |
| 📋 Booking System | Book tickets and view full booking history |
| 👨‍💼 Admin Panel | Separate admin login and management dashboard |
| 📅 Holiday Awareness | Pricing adjusts based on Bangladesh holidays |
| 🌙 Dark / Light Mode | Toggle between dark and light UI themes |
| 👤 Traveller Details | Save and manage passenger information |

---

## 🛠️ Tech Stack

```
Java Side
──────────────────────────────────────────
Language          →  Java (JDK 8+)
GUI Framework     →  Java Swing + FlatLaf
Database          →  MySQL (XAMPP)
Calendar Widget   →  JCalendar
HTTP Client       →  HttpURLConnection
Encryption        →  AES (javax.crypto)
AI Assistant      →  Google Dialogflow
Speech Input      →  Vosk (offline model)
Voice Output      →  Google TTS + FreeTTS

Python Side
──────────────────────────────────────────
Language          →  Python 3.x
Web Framework     →  Flask
ML Library        →  Scikit-learn
Data Processing   →  Pandas
Model             →  Random Forest Regressor
Model Accuracy    →  ~85%
Dataset           →  5000 Bangladesh domestic flights
```

---

## 📁 Project Structure

```
Airline Ticket Reservation System/
│
├── src/
│   └── airline/ticket/reservation/system/
│       ├── LoadingScreen.java          ← App entry point
│       ├── LoginPage.java              ← Login with AES encryption
│       ├── LoginOption.java            ← Login type selection
│       ├── CreateAccount.java          ← New user registration
│       ├── OTPDialog.java              ← OTP verification
│       ├── SessionManager.java         ← User session handling
│       ├── MainPage.java               ← Main dashboard
│       ├── FlightBooking.java          ← Flight search and booking
│       ├── SearchTickets.java          ← Search available tickets
│       ├── Ticket.java                 ← Ticket details
│       ├── Traveller.java              ← Passenger information
│       ├── MyBookings.java             ← Booking history
│       ├── MyDetails.java              ← User profile
│       ├── AdminLoginDialog.java       ← Admin authentication
│       ├── AdminPage.java              ← Admin management
│       ├── AssistantDialog.java        ← AI chatbot UI
│       ├── DialogflowClient.java       ← Google Dialogflow API
│       ├── MicrophoneRecorder.java     ← Voice input recorder
│       ├── SpeechToText.java           ← Vosk speech recognition
│       ├── TextToSpeech.java           ← Voice output
│       ├── FlightFormDialog.java       ← Flight entry form
│       ├── FlightPriceClient.java      ← Calls Python ML API
│       └── HolidaySeasonManager.java   ← Holiday pricing logic
│
├── FlightML/
│   ├── flight_price_prediction.py      ← ML model training script
│   ├── flight_price_api.py             ← Flask REST API server
│   ├── flight_price_model.pkl          ← Trained Random Forest model
│   ├── encoders.pkl                    ← Label encoders
│   └── Bangladesh_Domestic_Flights_5000.csv  ← Real flight dataset
│
├── sql/
│   └── horizon.sql                     ← Database setup script
│
└── README.md
```

---

## ⚙️ Requirements

### Java side
- ✅ Java JDK 8 or higher — [Download here](https://www.oracle.com/java/technologies/downloads/)
- ✅ Apache NetBeans IDE — [Download here](https://netbeans.apache.org/)
- ✅ XAMPP installed and running — [Download here](https://www.apachefriends.org/)
- ✅ MySQL Connector JAR — [Download here](https://dev.mysql.com/downloads/connector/j/)

### Python side
- ✅ Python 3.8 or higher — [Download here](https://www.python.org/downloads/)
- ✅ Flask, Scikit-learn, Pandas, Joblib

Install all Python requirements with one command:
```bash
pip install flask scikit-learn pandas joblib
```

---

## 🗄️ Database Setup

**Step 1** — Open XAMPP Control Panel and start **Apache** and **MySQL**

**Step 2** — Open your browser and go to:
```
http://localhost/phpmyadmin
```

**Step 3** — Click **New** on the left side and create a database called:
```
horizon
```

**Step 4** — Click the **SQL** tab and run the script from `sql/horizon.sql`

---

## 🤖 ML Model Details

The price prediction model was trained on real Bangladesh domestic flight data:

```
Dataset       →  5000 real Bangladesh domestic flight records
Algorithm     →  Random Forest Regressor
Features      →  Source, Destination, Duration, Stopovers,
                 Class, Seasonality, Days Before Departure
Target        →  Total Fare (BDT)
Accuracy      →  ~85%
API           →  Flask REST API running on localhost:5000
```

---

## ▶️ How to Run

### Step 1 — Start the Python ML API
```bash
cd FlightML
python flight_price_api.py
```
The API will start at `http://localhost:5000` — keep this running

### Step 2 — Start XAMPP
- Open XAMPP Control Panel
- Start **Apache** and **MySQL** — both must be green

### Step 3 — Set up the database
- Go to `http://localhost/phpmyadmin`
- Create database called `horizon`
- Run `sql/horizon.sql`

### Step 4 — Open the Java project
- Open NetBeans → File → Open Project
- Select the project folder
- Add all JAR files from `dist/lib/` to Libraries
- Add MySQL Connector JAR to Libraries
- Press **F6** to run

---

## 🔑 Default Login

```
Username  →  admin
Password  →  (set during database setup)
```

New users can register directly from the login screen.

---

## 🔗 Related Projects

This project is part of a series of desktop applications:

👉 [E-Book Reader](https://github.com/samsul-arifin-17/ebook-reader) — Java Swing PDF library manager

👉 [Student Grade Calculator](https://github.com/samsul-arifin-17/student-grade-calculator) — Java Swing GPA calculator

---

## 👨‍💻 Author

<div align="center">

**Samsul Arifin**


[![GitHub](https://img.shields.io/badge/GitHub-samsul--arifin--17-181717?style=for-the-badge&logo=github)](https://github.com/samsul-arifin-17)
[![Email](https://img.shields.io/badge/Email-smarifin17@gmail.com-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:smarifin17@gmail.com)

</div>

---

<div align="center">

⭐ If you found this project helpful, please give it a star!

</div>
