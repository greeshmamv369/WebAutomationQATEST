Framework:
selenium-framework
│
├── pom.xml
├── README.md
│
└── src
├── main
│   ├── java
│   │   └── com.automation
│   │       ├── base
│   │       │   ├── BaseClass.java
│   │       │   └── DriverManager.java
│   │       │
│   │       ├── pages              # Page Object Model
│   │       │   ├── LoginPage.java
│   │       │   └── HomePage.java
│   │       │
│   │       ├── utils
│   │       │   ├── SeleniumUtils.java     # Click, sendKeys, wait, JS actions
│   │       │   ├── WaitUtils.java         # Fluent wait, explicit wait
│   │       │   ├── FileUtils.java         # File moves, screenshots
│   │       │   ├── DBUtils.java           # JDBC database connections
│   │       │   ├── JsonUtils.java         # JSON reader parser
│   │       │   ├── PropertyReader.java    # config.properties reader
│   │       │   └── ExcelUtils.java        # Optional Excel reader
│   │       │
│   │       ├── reporting
│   │       │   ├── ExtentManager.java     # Extent report setup
│   │       │   ├── ExtentTestManager.java # Test instance manager
│   │       │   └── ExtentReporter.java    # Final report file generator
│   │       │
│   │       ├── listeners
│   │       │   ├── TestListener.java      # TestNG ITestListener for screenshots
│   │       │   ├── SuiteListener.java     # ISuiteListener
│   │       │   └── RetryAnalyzer.java     # Retry failed tests
│   │       │
│   │       └── logger
│   │           └── Log.java               # Wrapper around Log4j
│   │
│   └── resources
│       ├── config.properties              # browser, URL, timeouts, DB creds
│       ├── log4j2.xml                     # Log4j2 configuration
│       ├── testdata.json                  # Test data JSON
│       └── testdata.xlsx                  # Optional Excel data
│
└── test
├── java
│   └── com.automation.tests
│       ├── LoginTest.java
│       ├── RegressionSuite.java
│       └── SmokeSuite.java
│
└── resources
└── testng.xml




Step 1:Create a Maven Project .
Step 2:Add the folder structure
