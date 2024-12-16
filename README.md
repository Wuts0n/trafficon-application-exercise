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

![use case diagram](./diagrams/use-case.drawio.svg)

The application shall feature two interfaces:

1) Data import
2) Data retrieval

In an ideal world, only users with admin privileges can import, update and delete data. Authorization and Authentication are **not** implemented here though.

All users can query parking lot data. Additionally, all users can query the sum of available parking spaces of all or of a specific set of parking lots.


### Database Relationship Diagram

![database relationship diagram](./diagrams/database-relationship.drawio.svg)

The database features a very simple structure:

- A general `ParkingLot` table, containing static content like its own `ID` or its `GEOMETRY`

All fields from the WFS json file are put into one single table. That's it.

> ℹ️ I considered splitting up the table into two tables of static content and dynamic content, so you could store and query historic data. But ultimately, this is just overengineering.

There is one difference though. The `FREIE_PLAETZE` field is split from one string field into two integer fields: `FREIE_PLAETZE_ABSOLUT` and `FREIE_PLAETZE_PROZENT`.

#### Storage Policy

Data properties stored are kept. Data properties are _not_ overridden. This allows users to query historic data.

#### Retention Policy

In order not to clump the database with unnecessary data, deleting old data once in a while may be beneficial to performance.

This is a simple demo app. It will not be implemented here.


### Class Diagram

![class diagram](./diagrams/class.drawio.svg)

The application will include default Jpa CRUD/REST functionality.

Additionally, two special methods will be added:

 - `findSumByIds()`: This method lets the user query the sum of all available parking spaces by a list of ids.
 - `createByWfsJson()`: This method lets the user import WFS Json data.



## Implementation

The application features a basic REST interface under `/parkingLots` and two special functions under `/parkingLots/wfs` and `/parkingLots/sum`.


### /parkingLots

This is a basic REST interface that allows CRUD operations (`GET`, `PUT`, `UPDATE`, `DELETE`) to store entites.


### /parkingLots/wfs

This is a custom interface that allows the user to import Salzburg's WFS Json format via `PUT`.

Use `PUT` as request method, `/parkingLots/wfs` as path and the WFS Json _content_ (**not** the file as a blob) as body.


### /parkingLots/sum

This is a custom interface that allows the user to retrieve the sum of all available parking spaces via `GET`.

Use `GET` as request method, `/parkingLots/sum` as path.

Optionally, use `ids` as paramter (`/parkingLots/sum?ids=`). It accepts a comma-separated list of ids. E.g. "1,2,3,4". This lets you filter the sum by specific parking lots.



## Run

Run the application by executing the command

```bash
docker compose up -d
```

in the `postgres-docker` folder.

Then, run the Spring application by executing the command

```bash
./mvnw spring-boot:run
```

in the `app` folder.



## Test

Run tests by executing the command

```bash
./mvnw test
```

in the `app` folder.



## Pros and Cons of the Dataset

[A concise list of pros and cons of the dataset can be found in this file.](./data_source.md)



## Further Thoughts


### FROST-Server

Storing sensor data seems like a prime example for the usage of a tool implementing [OGC SensorThings API](https://www.ogc.org/publications/standard/sensorthings/), like the open-source [FROST-Server](https://fraunhoferiosb.github.io/FROST-Server/). Sensors can be added quickly. Furthermore, it features an extensive query language. Furthestmore, FROST-Server in particular features an MQTT interface so that subscribed applications will receive updates as soon as they happen. This would be a nice-to-have feature for Salzburg's parking lot data.

Ultimately, this application is meant as an exercise to Spring. Thus, to my displeasure, I refrained from using a FROST-Server.


### Swagger

Would be a nice addition to have a nice-looking Swagger documentation for the REST interface for sure. Admittedly, I have not worked with it before so it is out of the scope for this small dummy application. I absolutely would not mind learning it.