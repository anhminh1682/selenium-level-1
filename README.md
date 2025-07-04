
# 🚂 SeleniumLevel1 - Railway Test Automation

> **Goal**: Basic test automation project for the Railway website using Java, Selenium, and TestNG.

---

## Note: Create file .env in root project and add API_MAIL_SLURP_KEY

## 🔧 Technologies Used

| Component            | Version                  |
|---------------------|--------------------------|
| Java                | JDK 11                   |
| IDE                 | IntelliJ IDEA            |
| Selenium            | 4.33.0                   |
| TestNG              | 7.11.0                   |
| Allure Report       | 2.29.1                   |
| MailSlurp           | 16.2.1                   |
| Log4j               | 2.25.0                   |
| ExtentReports       | 5.1.2                    |
| Test Browser        | Chrome 138.0.7204.9 (64-bit) |

---

## ✅ Applied Techniques

- ✅ **Page Object Model (POM)** for reusability and maintainability.
- ✅ Handling **dynamic locators** using flexible XPath.
- ✅ Following **Java coding conventions**.
- ✅ **Parallel execution** support using TestNG.
- ✅ **Screenshot on failure**, embedded in reports.
- ✅ **Log4j** for structured logging (INFO, WARN, ERROR).
- ✅ **Extent Reports** with step-by-step and sub-step logging + screenshots.
- ✅ Using **data models** instead of plain primitive types.
- ✅ Using **enums** like `SeatTypeEnum`, `DepartStationEnum`, etc.
- ✅ **Allure Report** integration for step-annotated reports.
- ✅ Using **MailSlurp API** to test the password reset feature (`TC12`).

---

## 🧪 Allure Report Setup Guide

### 1. Download Allure CLI

👉 [Download Allure CLI](https://github.com/allure-framework/allure2/releases)

> Recommended version: **Allure 2.34.1**

---

### 2. Set Up Environment Variables

- Unzip the downloaded `.zip` file.
- Add the `bin` directory of Allure to your system's `PATH`.

---

### 3. Verify Installation

```bash
allure --version
```

---

### 4. Run the Tests

```bash
mvn clean test
```
or:
```bash
testng testng.xml
```

---

### 5. View the Allure Report

```bash
cd SeleniumLevel1
allure serve
```

📌 *Make sure Java is properly installed and configured.*

---

## 🧩 Notes

- If you encounter `ExtentTest is null`, ensure `LogUtils.setExtentTest(...)` is called before logging.
- For `CDP version mismatch` warnings, update Selenium or ChromeDriver to match your current Chrome version.

---

## 👨‍💻 Author

> This project is built for learning and applying basic to intermediate test automation practices using Selenium and professional reporting tools.
