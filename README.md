# Cineclube back-end ⛁

A [spring-boot](https://spring.io/projects/spring-boot) web application to manage movies and series.

---

## Dependencies 📦
- Spring boot
- H2 database
- Select2.js
- JPA
- Maven
- Thymeleaf
- TMDB api

---

## Roadmap 🗺️

Cineclube project roadmap and main features implemented:

#### 🟨 Conventional MVC Web architecture
- [X] **Setup spring-boot project**
    - [X] configure spring-boot starters in [pom.xml](https://github.com/fscheidt/cineclube/blob/master/pom.xml)
    - [X] configure [app.properties](https://github.com/fscheidt/cineclube/blob/master/src/main/resources/application.properties) for H2, Thymeleaf and JPA settings.
    - [X] Create the model classes: Filme, Pessoa, Category(enum)
    - [X] Create a `@Service` class `DataLoaderHelper` so we don't need to re-create the database every time during development time (when we mess up a lot with the data). However, once the development phase ends, remove or disable this class.
- [X] **Movies CRUD**
    - [X] Create the Thymeleaf client interface html pages:
      - [X] on resources/templates/filme - `manterFilme.html` and `list.html`
    - [X] Create a `@Controller` (FilmeController) to manage http requests
      - [X] Define the actions: `newForm`, `delete`, `edit`, `list`, `save`
      - [X] Define the http methods for each action: `@PostMapping`, `@GetMapping`, `@RequestMapping`
    - [X] Create MovieRepository (aka `FilmeRepository`)
      - [X] Define method queries with spring data.
      - [X] findByCategory, findByNome, etc ...
    - [X] N-N Relationship with `Category`
    - [X] Define N-N Relationship (`@ManyToMany`) with `Person`
    - [X] Integrate [Select2.js](https://select2.org/) component to display the list of people associated with a movie.
- [X] **Person CRUD**:
    - [X] Create Thymeleaf html pages (/templates/pessoa).
    - [X] Create a `@Controller` (PessoaController) to manage http requests
    - [X] Create PersonRepository (aka `PessoaRepository`)
    - [X] Define N-N Relationship with `Movie`
- [X] **Category CRUD**
    - [X] Refactor category: from enum to `@Entity`
    - [X] Create `CategoryRepository`
    - [X] Create a `@Controller` (CategoryController) to manage http requests
    - [ ] Define N-N Relationship with `Movie`
- [X] **Create cineclube API**
    - [X] Create **movie** endpoints using `@RestController`
      - [X] Associate the http verbs for each endpoint:
        - [X] create `@PostMapping`
        - [X] read `@GetMapping`
        - [X] update `@PutMapping`
        - [X] delete `@DeleteMapping`
    - [X] Create **person** endpoints using `@RestController`
    - [X] Create a java class to customize the serialization process for:
      - [X] Pessoa: `PessoaListSerializer` (to expose the list of **filmes**)
      - [ ] Filme: `MovieListSerializer` (to expose the list of **pessoas** and **categories**)

#### 🟩 REST architecture
- [X] **The moviedb API integration (TMDB)**:
    - [X] Integrate Spring with [TMDB api](https://www.themoviedb.org/documentation/api) 👀
      - [X] Create an api-key + configure spring app.properties.
      - [X] Use `RestTemplate` to make requests and consume the api services.
      - [ ] Define a separeate package for TMDB classes: `br.com.cineclube.tmdb`
      - [X] Define a Service layer at `br.com.cineclube.tmdb.service` that groups all services that will use/call the TMDB api.
    - [ ] 🎬 **Movies** TMDB api services:
      - [X] Create a class `FilmeDB` to map the json return from TMDB. This is necessary to deserialize the object that represents a Movie.
      - [X] Create a java Wrapper class (`WrapperMovieSearch`) to map the results from a search in TMDB api, so we can use it in cineclube.
      - [X] Create a @service class `MoviedbService` to call the TMDB api endpoints.
      - [X] Search Movie by id. ⚙ `/movie/id`- [docs](https://developers.themoviedb.org/3/movies/get-movie-details)
      - [X] Search Movie by title. ⚙ `/search/movie?api_key={key}&query={title}` - [docs](https://developers.themoviedb.org/3/search/search-movies)
        - [X] Get the movie poster image - [image docs](https://developers.themoviedb.org/3/getting-started/images)
      - [ ] Search Movie by genres
    - [ ] 🧑 **Person** TMDB api services:
      - [X] Create class `PersonDB` for deserializing the json return by the api.
      - [ ] Create a @service class `PersondbService` to call the TMDB person api endpoints.
      - [ ] Search Person by name. ⚙ `/search/person?api_key={key}&query={name}` [api docs](https://developers.themoviedb.org/3/search/search-people)
      - [ ] Find all movies in which a person participated.
    - [ ] 📺 **TV/Shows**
      - [ ] Create a @service class `TvSeriesdbService` for TMDB endpoints.
      - [ ] Search a series/tv-shows by title - [docs](https://developers.themoviedb.org/3/search/search-tv-shows)
    - [ ] **Other services**
      - [ ] Find the list of all genres (categories) in TMDB.
- [ ] **API Authentication**
- [ ] **Cors support**
    - [ ] Enable cors in Spring
    - [ ] Test-case: Vue client to consume Cineclube API - CineApp

---

This project is *only for educational purposes* developed for learning the concepts of the spring-boot framework used in the class **Desenvolvimento Web IV** - 2021, ministered by Felippe Scheidt at [IFPR](https://foz.ifpr.edu.br/).
