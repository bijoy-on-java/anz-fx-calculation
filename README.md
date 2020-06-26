# ANZ Forex Calculator
This requirement is to convert the currencies.

Note: This application has been developed using MacOS.

# Author(s)
Name: Bijoy Baral
Email: bijoy.baral@cognizant.com

# Requirement Abstract View
The application allows a user to convert an amount in a specific currency to the equivalent amount in another currency.

# Task to Accomplish
Console based calculator should allow a user to enter an amount in any of the known currencies, and provide the equivalent amount in another currency.

# Prerequisites
1. Java 8
2. Gradle Build
3. Junit, Hamcrest
5. IntelliJ IDEA
6. MacOS

# Download, Installation and Configuration of Software's
Java 8:
1. It can be downloaded from following websites:
   https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html
2. Installation steps can be found under following link:
   https://www.java.com/en/download/help/mac_install.xml
3. Configuration steps can be found from following URL:
   https://www.oracle.com/webfolder/technetwork/tutorials/oraclecode/mac-hol-setup.pdf

Gradle:
1. Software can be downloaded from following location:
   https://gradle.org/releases/
2. And all installation and configuration steps can be found from following URL:
   https://gradle.org/releases/
3. Install gradle plugin from IntelliJ IDEA. Plugin installation through IntelliJ IDEA can be found from following URL:
   https://www.jetbrains.com/help/idea/managing-plugins.html

IntelliJ IDEA:
1. This software can be downloaded into following location:
   https://www.jetbrains.com/idea/download/#section=mac
2. This project will be imported into IntelliJ IDEA. Steps to import this project can be found from following URL:
   https://www.jetbrains.com/help/idea/gradle.html#link_gradle_project
   
# Classes
Following classes are developed for implementing this functionality:
1. FXCalculatorConsole.java(main class)
2. FXCalculatorConverter.java(Currency converter class)
3. FXCalculatorParser.java(Input parser class)
4. FXCalculatorRequest.java(POJO class to hold calculation attributes)

# Running Programme(s)
Following steps has to be followed to run and validate this requirement(once you import project into IntelliJ IDEA:
1. Please find the main class FXCalculatorConsole.java
2. Right click on the class and select Run FXCalculatorConsole..main()
3. In the terminal console window of IntelliJ IDEA, it will ask for "Please specify the currency amount to proceed/ convert:"
4. Please enter 'AUD 100.00 in USD' and press enter
5. You can see the result will be displayed as 'AUD 100.00 = USD 83.71'

# Running Test(s)
Following Test cases can be checked and validated for currency calculator implementation:
1. TestFXCalculatorConverter.java
2. TestFXCalculatorParser.java

Steps to execute Test cases:
1. Right click on Test cases(Java file) and click Run TestFXCalculatorConverter.java or TestFXCalculatorParser.java
2. Various Test and edge cases can be verified in the console window.

Steps to check code coverage:
1. Right click on Test cases(Java file) and click Run TestFXCalculatorConverter.java or TestFXCalculatorParser.java with coverage.
2. Code coverage can be verified in the console window.

# Build
1. Project can be build using gradle command, such as: gradle clean install, gradle clean build etc.

# Deployment
N/A

# Contributing

N/A

# Versioning

N/A

# Licence

N/A

# Acknowledgement(s)
N/A