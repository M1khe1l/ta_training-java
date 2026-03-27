# Saucedemo E2E Test Automation

End-to-end test automation project for [https://www.saucedemo.com/](https://www.saucedemo.com/) built with Selenium WebDriver, TestNG, and Allure reporting.

---

## Tech Stack

| Tool               | Version      |
|--------------------|--------------|
| Java               | 17           |
| Selenium WebDriver | 4.41.0       |
| TestNG             | 7.12.0       |
| Allure TestNG      | 2.27.0       |
| Maven              | 3.x          |
| Browsers           | Chrome, Edge |

---

## Project Structure

```
src
├── main
│   └── java
│       ├── com.epam.training.mikheil_akobidze.pages.base
│       │   └── BasePage.java               # Base page with WebDriverWait helpers
│       ├── com.epam.training.mikheil_akobidze.pages.saucedemo_pages
│       │   ├── LoginPage.java
│       │   ├── InventoryPage.java
│       │   ├── CartPage.java
│       │   ├── CheckoutPage.java
│       │   ├── CheckoutFinishPage.java
│       │   └── CompletePage.java
│       └── utilities
│           └── Utility.java                # Price parsing helpers
└── test
    ├── java
    │   ├── com.epam.training.mikheil_akobidze.base
    │   │   └── BaseTest.java               # Driver setup, teardown, browser provider
    │   ├── com.epam.training.mikheil_akobidze.saucedemo_task_1
    │   │   └── CheckoutOneItemTest.java     # UC-1: Checkout one item
    │   └── com.epam.training.mikheil_akobidze.saucedemo_task_2
    │       └── CheckoutSeveralItemTest.java # UC-2: Checkout several items
    └── resources
        └── allure.properties               # Allure results directory config
```

---

## Test Cases

### UC-1: Checkout Flow (One Item)
- Login with `standard_user`
- Add a specific product to the cart
- Validate item is present in cart
- Proceed to checkout and fill in information form
- Validate success message: **"Thank you for your order!"**

### UC-2: Checkout Flow (Several Items)
- Login with `standard_user`
- Add two specific products to the cart
- Validate both items are present in cart
- Proceed to checkout and fill in information form
- Validate final price equals the sum of both product prices
- Validate success message: **"Thank you for your order!"**

---

## Prerequisites

Make sure the following are installed on your machine:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.x](https://maven.apache.org/download.cgi)
- [Google Chrome](https://www.google.com/chrome/) (latest)
- [Microsoft Edge](https://www.microsoft.com/en-us/edge) (latest)

Verify installations by running:
```bash
java -version
mvn -version
```

---

## How to Run Tests

**1. Open terminal in the project root** (where `pom.xml` is located)

**2. Run all tests in parallel on Chrome and Edge:**
```bash
mvn clean test
```

Tests run in parallel — Chrome and Edge execute simultaneously via TestNG `@DataProvider(parallel = true)`.

---

## How to Generate Allure Report

**Option 1 — Serve report in browser (recommended):**
```bash
mvn allure:serve
```
This builds the report and automatically opens it in your default browser.

**Option 2 — Generate report as static files:**
```bash
mvn allure:report
```
Then open the report manually at:
```
target/site/allure-maven-plugin/index.html
```

> **Note:** Always run `mvn clean test` before generating the report to ensure results are up to date.

---

## Allure Report Features

- **Epics / Stories** — tests organized by feature and use case
- **Step-by-step execution** — each page action logged as a named step
- **Screenshots on failure** — automatically captured and attached to failed tests
- **Parallel execution timeline** — visible in the Timeline tab

---

## Logging

The project uses SLF4J for console logging during test execution.

Logs are printed automatically to the console when running tests and include:
- Browser setup and teardown events
- Navigation to URL
- Test failure notifications

To view logs simply run:
```bash
mvn clean test
```
Logs appear in the terminal output in real time alongside test execution.

---

## Parallel Execution

Tests are executed in parallel across two browsers using TestNG's `@DataProvider(parallel = true)` and `ThreadLocal<WebDriver>` to ensure thread safety.

| Browser | Mode      |
|---------|-----------|
| Chrome  | Incognito |
| Edge    | Default   |
