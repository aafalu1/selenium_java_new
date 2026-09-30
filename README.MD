# Selenium Java Automation Framework

A Java-based browser automation project using Selenium WebDriver and TestNG. The framework exercises login, product selection, checkout, payment, and order confirmation flows on the Rahul Shetty Academy demo storefront (`https://rahulshettyacademy.com/client`). It uses page objects to group page interactions, WebDriverManager to provision browser drivers, and an ExtentReports listener to generate an HTML test report with failure screenshots.

> The tests interact with a live third-party demo application. Its availability, UI, test account, and test data can change; this project does not control that service.

## What is included

- Page objects for the landing, home, checkout, payment, and order confirmation pages.
- TestNG tests for login and order confirmation in the default suite.
- Browser selection for Chrome, Edge, and Firefox; Chrome runs headless by default.
- TestNG listener with an ExtentReports report and screenshots attached on failure.
- Test account credentials read from environment variables or Java system properties, never from tracked source.

## Requirements

- Java 11 or newer (the project compiles against the Java 11 API).
- Apache Maven 3.9 or newer (recommended).
- A supported browser installed locally: Chrome (default), Edge, or Firefox.
- A valid account for the demo storefront, if the live site's login and checkout flows are to be exercised.
- Network access to Maven Central, the selected browser's driver distribution, and the demo storefront.

Selenium 4.49.0 and WebDriverManager automatically manage the browser-driver setup. Browser and driver compatibility still depends on the browser installation and network access.

## Setup

1. Clone this repository and switch to its root directory (the directory containing `pom.xml`):

   ```bash
   git clone https://github.com/aafalu1/selenium_java_new.git
   cd selenium_java_new
   ```

2. Confirm that Maven is using Java 11 or newer:

   ```bash
   java -version
   mvn -version
   ```

3. Supply credentials for the demo storefront outside the repository. On macOS/Linux:

   ```bash
   export TEST_EMAIL='your-demo-account-email'
   export TEST_PASSWORD='your-demo-account-password'
   ```

   On PowerShell:

   ```powershell
   $env:TEST_EMAIL = 'your-demo-account-email'
   $env:TEST_PASSWORD = 'your-demo-account-password'
   ```

   The equivalent Maven system properties are `-Dtest.email=...` and `-Dtest.password=...`. Avoid committing real credentials or putting them in shared shell history.

4. Build and run the configured suite:

   ```bash
   mvn clean test
   ```

Maven downloads the declared dependencies on the first build. The default TestNG suite is `src/main/resources/testNG.xml`; it runs `LoginPageTest` and `OrderConfirmationPageTest`.

## Running tests

By default the suite uses Chrome in headless mode, as configured in `src/main/resources/configuration/config.properties`. Choose another supported browser with the `browser` system property:

```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

Run without headless mode by setting `headless:false` in `src/main/resources/configuration/config.properties`. The browser property can also be changed in that file; a `-Dbrowser=...` argument takes precedence.

The TestNG XML suite selects which test classes run. To run other classes under `src/test/java/org/aafalu/testcases`, add them to `src/main/resources/testNG.xml`. The `AddScreenshotsDemo` class is a standalone demonstration with a `main` method; it is not part of the default suite.

## Reports and outputs

- The ExtentReports HTML report is written to `target/ExtentReport/extentReport.html`.
- Failed tests include a captured browser screenshot in the ExtentReports entry.
- Maven build output and compiled classes are placed under `target/`.

## Project layout

```text
src/
├── main/
│   ├── java/org/aafalu/
│   │   ├── base/                 # Shared WebDriver and page interaction helpers
│   │   ├── pages/                # Page-object implementations
│   │   └── utilities/            # Configuration and ExtentReports setup
│   └── resources/
│       ├── configuration/        # Browser and headless defaults
│       └── testNG.xml            # Default TestNG suite and listener
└── test/
    ├── java/org/aafalu/
    │   ├── base/                 # Test lifecycle and WebDriver setup
    │   ├── test/utilities/       # Externalized test credentials
    │   ├── test/util/listners/   # TestNG report listener
    │   └── testcases/            # Browser automation tests
    └── resources/                # ExtentReports properties
```

## Main dependencies

| Component | Version | Role |
|---|---:|---|
| Selenium Java | 4.49.0 | Browser automation |
| TestNG | 7.12.0 | Test framework and suite execution |
| WebDriverManager | 6.4.0 | Browser driver setup |
| ExtentReports | 5.1.2 | HTML reporting |
| SLF4J / Logback | 2.0.20 / 1.6.4 | Logging API and implementation |

Dependency versions were checked against Maven Central release metadata on September 30, 2026; preview versions were excluded. Plugin versions for the Maven compiler and Surefire are pinned in `pom.xml`.

## Configuration notes

- Browser and headless defaults are in `src/main/resources/configuration/config.properties`.
- The tests target a live application URL configured in `AafaluBaseTest`.
- `TEST_EMAIL` and `TEST_PASSWORD` can also be passed as Java system properties (`test.email` and `test.password`).
- The site credentials are not provisioned by this repository. Use an account authorized for the demo storefront.

## Troubleshooting

- **Missing credential error:** Set `TEST_EMAIL` and `TEST_PASSWORD` (or the matching system properties) before running tests.
- **Driver or browser startup failure:** Confirm the chosen browser is installed and can be reached by WebDriverManager; check network access and browser/driver compatibility.
- **Element or assertion failures:** The target is a live site. Confirm it is available and that its account, products, and page layout have not changed.
- **No report found:** Check the Maven output for suite completion or errors, then inspect `target/ExtentReport/extentReport.html`.
