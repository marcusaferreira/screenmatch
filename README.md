# SCREENMATCH

ScreenMatch is a Java console application built with Spring Boot. It uses the OMDb API to look up a TV series, load its seasons and episodes, and explore episode ratings and release dates.

The application was built during Alura's course "Java: trabalhando com lambdas, streams e Spring Framework".

## Requirements

- Java 25
- An [OMDb API key](https://www.omdbapi.com/apikey.aspx)

## Configure and run

Set the `OMDB_API_KEY` environment variable to your key, then start the application with the Maven Wrapper:

```bash
export OMDB_API_KEY=your_api_key
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run` and set `OMDB_API_KEY` in your environment before launching.

Enter a series name when prompted. After the series and episode data load, use the console menu to:

- List all episodes or the five highest-rated episodes
- List episodes released after a date (`YYYY-MM-DD`)
- Find an episode by title
- View average ratings by season and series rating statistics

The application makes requests to the [OMDb API](https://www.omdbapi.com/). Keep your API key private and do not commit it to the repository.
