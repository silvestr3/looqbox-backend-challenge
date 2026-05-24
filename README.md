# PokeAPI

Kotlin/Spring Boot API for searching Pokemon names from the public [PokeAPI](https://pokeapi.co/). The service fetches the Pokemon list, filters by a query string, supports sorting, and can return highlighted matches.

## Requirements

- Java 21
- Gradle wrapper included in this repository

## Tech Stack

- Kotlin 2.2
- Spring Boot 4
- Gradle Kotlin DSL
- Spring WebFlux `WebClient`
- Kotlin coroutines

## Running Locally

Start the application with:

```bash
./gradlew bootRun
```

By default, Spring Boot serves the API on:

```text
http://localhost:8080
```

## API

### Search Pokemon

```http
GET /pokemon
```

Query parameters:

| Name | Required | Default | Description |
| --- | --- | --- | --- |
| `query` | No | empty string | Case-insensitive substring used to filter Pokemon names. |
| `sort` | No | `ALPHABETICAL` | Sorting mode. Supported values: `ALPHABETICAL`, `LENGTH`. |

Example:

```bash
curl "http://localhost:8080/pokemon?query=char&sort=ALPHABETICAL"
```

Response:

```json
{
  "result": [
    "charizard",
    "charmander",
    "charmeleon"
  ]
}
```

### Search Pokemon With Highlight

```http
GET /pokemon/highlight
```

Returns the matching Pokemon names plus a highlighted representation of the matched query text.

Query parameters:

| Name | Required | Default | Description |
| --- | --- | --- | --- |
| `query` | No | empty string | Case-insensitive substring used to filter Pokemon names and produce highlights. |
| `sort` | No | `ALPHABETICAL` | Sorting mode. Supported values: `ALPHABETICAL`, `LENGTH`. |

Example:

```bash
curl "http://localhost:8080/pokemon/highlight?query=saur&sort=LENGTH"
```

Response shape:

```json
{
  "result": [
    {
      "name": "ivysaur",
      "highlight": "ivy<saur>"
    }
  ]
}
```

## Build

Create a local build with:

```bash
./gradlew build
```

The generated artifact is written under `build/libs/`.

## Project Structure

```text
src/main/kotlin/com/looqbox/pokeapi
├── configuration/   WebClient configuration
├── controller/      HTTP controllers
├── dto/             API response DTOs
├── handler/         Global exception handling
├── model/           Domain models and enums
└── service/         Pokemon lookup, sorting, and highlighting logic
```
