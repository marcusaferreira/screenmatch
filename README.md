# ScreenMatch

ScreenMatch is a Java console application built with Spring Boot. It looks up TV series and episode information through the [OMDb API](https://www.omdbapi.com/), translates series plots into Portuguese with the OpenAI API, and stores searched series in PostgreSQL.

## Requirements

- Java 25
- PostgreSQL, with a database named `screenmatch`
- An [OMDb API key](https://www.omdbapi.com/apikey.aspx)
- An OpenAI API key for plot translation

## Configure and run

Set the following environment variables before starting the application:

| Variable | Purpose |
| --- | --- |
| `OMDB_API_KEY` | Accesses series and episode data from OMDb |
| `OPENAI_API_KEY` | Translates series plots into Portuguese |
| `DB_HOST` | PostgreSQL host (include a port if it is not the default) |
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |

For example, on macOS or Linux:

```bash
export OMDB_API_KEY=your_omdb_api_key
export OPENAI_API_KEY=your_openai_api_key
export DB_HOST=localhost:5432
export DB_USERNAME=your_database_user
export DB_PASSWORD=your_database_password
./mvnw spring-boot:run
```

On Windows, set the same environment variables in your shell and run:

```bat
mvnw.cmd spring-boot:run
```

The application connects to the `screenmatch` database and updates its schema through Hibernate. Keep API keys and database credentials private; do not commit them to the repository.

## Console menu

At startup, search for a series by title, then select it to load its seasons and episodes. The menu lets you:

1. Search for a series
2. List series saved in the database
3. Select a series searched during the current run
4. List its episodes
5. Show the five highest-rated episodes
6. Show episodes released after a date (`YYYY-MM-DD`)
7. Find an episode by title
8. Show average episode ratings by season
9. Show series episode-rating statistics

Series metadata is saved in PostgreSQL. Season and episode data is fetched when a series is selected and used for the current application run.

## Build and test

```bash
./mvnw test
./mvnw package
```

On Windows, use `mvnw.cmd` in place of `./mvnw`.
