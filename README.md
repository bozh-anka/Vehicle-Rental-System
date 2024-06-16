# Vehicle-Rental-System
Prime Holding Task 1 - Vehicle Rental System

Analysed the business logic details and broke the invoice system into a two-level class hierarchy.

Abstract Invoice class with child classes for each vehicle type available. The abstract functions are the Rent and Insurance calculations.

The Invoice class held all data members shared between the vehicle types, while each child class held its unique members (e.g. Age for Motorcycle).

The Invoice class holds all the output processing with its toString override. The toString override calls the child class' 
respective rent and invoice calculation functions, thus applying the unique formulas dynamically. 

The InvoiceDemo file holds the main class and demonstrates the execution of the example.

Dates within the program use the Date datatype, formatted down to exclude the timezone information.
I found them to translate into numbers more simply than string formatting and parsing.
