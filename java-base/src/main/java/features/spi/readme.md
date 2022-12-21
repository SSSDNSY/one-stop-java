## Introduction
    An extensible application is one that you can extend without modifying its original code base. You can enhance its functionality with new plug-ins or modules. Developers, software vendors, and customers can add new functionality or application programming interfaces (APIs) by adding a new Java Archive (JAR) file onto the application class path or into an application-specific extension directory.
    
    This section describes how to create applications with extensible services, which enable you or others to provide service implementations that require no modifications to the original application. By designing an extensible application, you provide a way to upgrade or enhance specific parts of a product without changing the core application.
    
    One example of an extensible application is a word processor that allows the end user to add a new dictionary or spelling checker. In this example, the word processor provides a dictionary or spelling feature that other developers, or even customers, can extend by providing their own implementation of the feature.
    
    The following are terms and definitions important to understand extensible applications:
 
 ##
    Service
        A set of programming interfaces and classes that provide access to some specific application functionality or feature. The service can define the interfaces for the functionality and a way to retrieve an implementation. In the word-processor example, a dictionary service can define a way to retrieve a dictionary and the definition of a word, but it does not implement the underlying feature set. Instead, it relies on a service provider to implement that functionality.
    Service provider interface (SPI)
        The set of public interfaces and abstract classes that a service defines. The SPI defines the classes and methods available to your application.
    Service Provider
        Implements the SPI. An application with extensible services enable you, vendors, and customers to add service providers without modifying the original application.
    



*** from  https://docs.oracle.com/javase/tutorial/ext/basics/spi.html ***