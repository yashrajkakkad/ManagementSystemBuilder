<h1 align="center">
  <a href="#"><img src="https://raw.githubusercontent.com/yashrajkakkad/ManagementSystemBuilder/master/MSBtransparent.png?token=AKS265VVA34TE5XJWZQ3QUC6BYU7Y" alt="MSB" width="200"></a>
  <br>
  Management System Builder
  <br>
</h1>

<h3 align="center">Quickly build basic CRUD systems with GUI and database support</h3>
  
<p align="center">
  <img src="https://forthebadge.com/images/badges/made-with-java.svg">
  <img src="https://forthebadge.com/images/badges/built-with-love.svg">
</p>

<p align="center">
  <a href="https://github.com/yashrajkakkad/ManagementSystemBuilder/blob/master/LICENSE">
    <img src="https://img.shields.io/badge/license-MIT-green">
  </a>
  <a href="https://github.com/yashrajkakkad/ManagementSystemBuilder/pull/new/master">
    <img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg">
  </a>
</p>

<p align="center">
  <a href="#introduction">Introduction</a> •
  <a href="#installation">Installation</a> •
  <a href="#usage">Usage</a>
</p>

---

## Introduction

blah blah blah

## Requirements

- Java version 8 or higher. See [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html) for install instructions.
- Gradle. See [here](https://gradle.org/install/) for install instructions.

## Installation

### Get it up & running

- Download our latest release from the [Releases](https://github.com/yashrajkakkad/ManagementSystemBuilder/releases) section.
- Unzip the downloaded zip file.
- Execute the included jar file `ManagementSystemBuilder.jar`.

### Building the source code

- Clone the repository
```sh
git clone https://github.com/yashrajkakkad/ManagementSystemBuilder.git
cd ManagementSystemBuilder
```
- Build all the classes
```sh
gradle build
```
- Execute the JAR file
```sh
java -jar build/libs/ManagementSystemBuilder.jar
```

## Usage

### Setting up the database (MySQL database)

- You'll be prompted on the first run to setup the database details.
- Enter the host address, database name, username and password.
- Default values point to a MySQL database running on localhost. You can easily set that up using [WAMP](http://www.wampserver.com/en/) (Windows only) or [XAMPP](https://www.apachefriends.org/index.html).

### Defining the entities

- Enter each entity and define datafields for each of them.

### Defining the datafields

- Enter name of each datafield and define its datatype.
- The first datafield entered will act as the primary key of that entity's database table.

### Defining the GUI Panels

- The options "Add", "Update", "Delete" and "View" will allow you to create a GUI panel for the corresponding option for a particular entity.
- Select the appropriate options for each entity you've defined.

### Other Details

- Draft a message that will be shown in the "About Us" section of your generated app.
- Provide an email address where your users can send feedback.

### Generate the system

- Once all the steps are completed, you can run your generated application on a single click.
- That will execute a script to build your application and then execute the generated .jar file.
- The source code for the generated application can be found in `generated/your_system_name`.
