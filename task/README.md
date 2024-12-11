# Trafficon | Task for Backend Applicants

Welcome! This task is designed to assess your skills in developing a Spring Boot application, following best practices, and creating a clean, maintainable codebase.
The instructions provided are intentionally minimal to encourage you to think independently and make informed decisions during implementation.

We're more interested in the quality of your implementation and repository than in the size or complexity of the application.
We're interested in seeing how you approach the development of a Spring Boot application. Specifically, we want to understand some of the following things:

- What constitutes a clean, well-organized development project and repository for you?
- What essential elements do you include in every application you create?
- How do you ensure code quality and maintainability?

## Task Description

Create a simple Spring Boot application (REST API) with the following functionalities:

- **Data Import**:
Enable users to import data that is relevant for analysis into a database. Use the open dataset, [Parkplätze in der Stadt Salzburg](https://www.data.gv.at/katalog/de/dataset/stadt-wien_parkpltzeinderstadtsalzburg), as your data source.
- **Data Retrieval**:
Allow users to retrieve data from the database for analysis. (e.g., provide an endpoint that calculates the sum of parking spaces for a specified set of IDs.)


In addition, please provide a Markdown file named `data_source.md` with a concise list of pros and cons of the dataset.

You can find more information about the provided database in the section [PostgreSQL / PostGIS Database](#postgresql--postgis-database).

## TLDR / MVP / Checklist

I have...

- [ ] created a public repository on GitHub or a different git hosting service.
- [ ] created a Spring-Boot application which implements the given task.
- [ ] tested the application.
- [ ] followed the known best-practices and clean code rules :-)

## PostgreSQL / PostGIS Database

Once you have installed all necessary dependencies (docker, docker compose plugin, ...) just copy the directory `postgres-docker` to your project's directory.

Within the directory you'll find two files:

- `docker-compose.yml`: the docker compose file
- `init.sql`: optional initialization sql script. This script will be run on first startup and can be used to create tables, etc. You can also use migration tooling, if you prefer.

The provided compose-file will start a database instance at `localhost:5432`.
Take a look at the compose-file contents to see how it is configured.

## Final Notes

This task is intentionally open-ended to give you the freedom to showcase your skills and approach. There's no single "correct" solution, so feel free to be creative and thoughtful in your implementation.

Good luck, and we look forward to reviewing your submission!
