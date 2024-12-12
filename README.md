# Trafficon Application Exercise

An exercise for my job application at Traffic Consultants GmbH.



## Task

Create a simple Spring Boot application (REST API) with the following functionalities:

- **Data Import**:
Enable users to import data that is relevant for analysis into a database. Use the open dataset, [Parkplätze in der Stadt Salzburg](https://www.data.gv.at/katalog/de/dataset/stadt-wien_parkpltzeinderstadtsalzburg), as your data source.
- **Data Retrieval**:
Allow users to retrieve data from the database for analysis. (e.g., provide an endpoint that calculates the sum of parking spaces for a specified set of IDs.)



## Solution

The following diagrams depict my solution to the task.


### Use-Case Diagram

![use-case diagram](./diagrams/use-case.drawio.svg)

The application shall feature two interfaces:

1) Data import
2) Data retrieval

Users with admin privileges can import and update data.

All users can query data. Currently, data can only be querried by a list of parking lots. The total amount of unoccupied parking spaces is then returned.