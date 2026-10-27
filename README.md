# Software Testing Portfolio: UI Automation & Backend Testing

This repository contains academic projects focused on software testing methodologies, including automated UI testing with Selenium and backend unit/integration testing with JUnit 5 and Mockito. These projects demonstrate my ability to design robust test architectures, implement the Page Object Model (POM), and verify complex business processes.

## Technologies Used

* **Language:** Java 17

* **Build Tool:** Maven

* **UI Automation:** Selenium WebDriver

* **Testing Frameworks:** JUnit 5 (Jupiter), Parameterized Tests

* **Mocking:** Mockito (5.11.0)

* **Architecture:** Page Object Model (POM) with Page Factory

## UI Test Automation with Selenium (Homework 7)

**Objective:** Automate the search, extraction, and verification of academic articles on [link.springer.com](https://link.springer.com/) using Selenium WebDriver.

### Implementation Details

* **Page Object Model (POM):** Implemented POM classes using `PageFactory` to ensure code reusability and maintainability for the following pages:

  * Home Page (Navigation to login)

  * Login Screen (`/signup-login`)

  * Advanced Search (`/advanced-search`)

  * Search Results (`/search`)

  * Article Detail (`/article/`)

* **Automated Extraction:** Developed a script to perform an advanced search for articles containing the exact phrase "Page Object Model" and at least one instance of "Sellenium Testing" published in the current year. The script reads the first four results and extracts their Title, DOI, and Publication Date.

* **Parameterized Testing:** Created parameterized tests that utilize the POM classes to automatically log in, search for the previously extracted articles by title, and assert that the live DOI and publication dates match the extracted data.

##  Backend Unit & Integration Testing (Homework 4)

**Objective:** Ensure the reliability of an E-shop backend system by writing comprehensive unit tests with Mockito and developing process/integration tests for the main controller.

### Unit Testing

Tested core domain entities and archive services focusing on edge cases and mocking dependencies:

* `StandardItem`: Verified constructors, copy methods, and wrote parameterized tests for `equals()`.

* `Order`: Tested constructors, specifically handling `null` value injections.

* `ItemStock`: Verified constructor logic and wrote parameterized tests for quantity adjustment methods.

* `PurchasesArchive`:

  * Tested statistical and archiving methods (`printItemPurchaseStatistics`, `getHowManyTimesHasBeenItemSold`, `putOrderToPurchasesArchive`).

  * Utilized **Mockito** to mock `orderArchive` and `ItemPurchaseArchiveEntry` dependencies.

  * Validated stream outputs (`println`) and verified correct constructor invocations during archive entry creation.

### Process / Integration Testing & Bug Fixing

* **Code Analysis & Debugging:** Analyzed the `EShopController` class to understand expected behavior, identifying and fixing underlying implementation bugs in the source code to ensure tests could pass.

* **Process Testing:** Designed integration tests covering the complete shopping cart lifecycle:

  * Adding and removing items from the cart.

  * The checkout and purchase process.

  * Sub-processes including stock inventory adjustments and purchase archiving.

  * Verified error handling states, such as attempting to purchase out-of-stock items.
