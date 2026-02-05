# Grievous Bot

<p align="center">
  <img src="assets/logo.png" alt="Grievous Banner" width="150" height="150">
</p>


## Project Overview

**Grievous Bot** is a robust, event-driven Discord automation tool developed in **Java**. Utilizing the **JDA (Java Discord API)** library, this application is engineered to provide modular and efficient server management.

The project structure focuses on scalability, clean architecture, and secure credential management, serving as a comprehensive template for advanced Java bot development.

## Technical Architecture

* **Language:** Java 21 (LTS)
* **Framework:** JDA 6.3.0 (Java Discord API)
* **Build Automation:** Gradle
* **Configuration Management:** Dotenv (Environment Variables)
* **Pattern:** Event Listener Adapter

## Prerequisites

Before deploying the application, ensure the following requirements are met:

* **Java Development Kit (JDK):** Version 21 or higher.
* **Gradle:** Distributed wrapper included in the repository.
* **Discord Bot Token:** A valid application token from the [Discord Developer Portal](https://discord.com/developers/applications).

## Installation and Configuration

### 1. Repository Cloning

Clone the repository to your local machine using Git:

```bash
git clone [https://github.com/Xhall1/Grievous-bot.git](https://github.com/Xhall1/Grievous-bot.git)
cd GrievousBot
```

### 2. Security Configuration

For security reasons, the bot token is not included in the source code. You must configure it manually using environment variables.

1. Create a file named `.env` in the root directory of the project.
2. Add your unique Discord Token as follows:

```properties
TOKEN=YOUR_DISCORD_TOKENN
SERVER_ID=YOUR_PRIVATE_SERVER_ID
```
