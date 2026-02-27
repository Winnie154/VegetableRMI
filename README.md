# Vegetable Service Engine
**Course:** MIT8102: Advanced Distributed Systems  
**Admission Numbers:** 225518 & 121290

## Project Overview
This is a mobile distributed application designed to manage a vegetable-price table.  
The system uses a distributed architecture where a mobile client communicates via Java Servlets to a remote Vegetable Compute Engine using RMI (Remote Method Invocation).

## Features
The application supports five core client tasks:
1. **Add:** Insert new vegetable-price entities.
2. **Update:** Modify existing vegetable prices.
3. **Delete:** Remove vegetable entities.
4. **Calculate:** Query prices and compute total cost based on quantity.
5. **Receipt:** Generate a transaction receipt including change and cashier details.

## Setup & Implementation
* **RMI Registry:** Runs on the default port 1099.
* **Connection:** Configured for `localhost` for local testing.
* **Architecture:** Implements a Compute Engine that executes generic `Task` objects.

## How to Run
1. Start the RMI Registry: `rmiregistry 1099`.
2. Launch the `VegetableComputeEngine`.
3. Deploy the Java Servlets to a web server (e.g., Apache Tomcat).
4. Run the Client application to interact with the Servlets.

## References
* Coulouris, G., Dollimore, J. & Kindburg, T. (2011). *Distributed Systems, Concepts and Design*. Addison Wesley.
* Tanenbaum, A. S. & van Steen, M. (2007). *Distributed Systems: Principles and Paradigms*. Pearson Education.
