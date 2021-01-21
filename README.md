Coverage: 79.7%
# Inventory Management System

This is a basic inventory management system which can be used for a website such as amazon. Customer details, order details and item details are all stored on an SQL server. This software allows the user
to create, read details, update and delete any customers,orders and items from the database. It can also add items to orders, remove items from orders, read all items in a specific order and calculate the 
cost of an order.


## Getting Started

### Prerequisites

This is a java project so java is required. If you do not already have java installed you can download it here:
https://www.oracle.com/uk/java/technologies/javase-downloads.html
Run the .exe file and choose a location to save the JDK to. In your computers settings, go to edit enviroment variables and edit system variables from there, create a new system variable 

```
JAVA_HOME 
```

point it to the jdk file. Now, in the PATH variable under 'User variables for Admin' add

```
 %JAVA_HOME%/bin
```

Now java is installed.

Secondly you will need maven which can be downloaded here:
https://maven.apache.org/download.cgi
follow similar steps to the java install, in the enviroment variables, create a new variable in system variables called 

```
MAVEN_HOME 
```

and point it to the downloaded maven file. Add this variable to the PATH variable like before.


### Installing

To install this software firstly you must clone the repo. Navigate to the repository in your command line and enter,


```
mvn clean
```

followed by 

```
mvn package
```

The project is now build, run the following command to start the project.

```
java -jar chris-ims-0.0.1-jar-withdependencies.jar
```
The username is root and password is root. From here you can choose which domain to choose from. Depending on the domain you choose you will be given
all possible actions and a description of what they do.

## Running the tests

To run the tests enter

```
mvn test
```

in the project directory

### Unit Tests 

These tests test all methods in the three controller, services and Dao classes. They ensure that each method returns the expected
return value or is called if there is no return value. The tests have an 80% coverage of the code. The main absence is methods 
in the Ims class.

## Deployment

If you want to adapt this system to work with a different sql server, copy that server's IP address into the
init method on line 166, and in place of place of the current IP address in the jdbcConnectionUrl variable in the 
ItemDaoMysql, OrderDaoMysl and CustomerDaoMysql classes.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
