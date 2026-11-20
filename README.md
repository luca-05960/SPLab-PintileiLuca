# SPLab - Sabloane de Proiectare

Proiect dezvoltat in cadrul laboratorului de Sabloane de Proiectare (SPLab). Aplicatia este construita cu Java 25 si Spring Boot, demonstrand implementarea si integrarea sabloanelor de proiectare intr-un sistem de gestiune a cartilor si a structurii documentelor.

## Sabloane de Proiectare Implementate

- **Composite**: Structura ierarhica a cartilor (`Book`, `Section`, `Paragraph`, `Image`, `Table`, `TableOfContents`).
- **Proxy**: `ImageProxy` pentru incarcarea virtuala/intarziata (lazy loading) a imaginilor.
- **Strategy**: Alinierea paragrafelor folosind strategii interschimbabile (`AlignLeft`, `AlignCenter`, `AlignRight`).
- **Visitor**: Parcurgerea si procesarea elementelor cartii (`BookStatistics` pentru statistici, `GenerateToC` pentru generarea cuprinsului).
- **Command**: Incapsularea operatiilor CRUD pe carti ca obiecte de comanda (`CreateBookCommand`, `GetAllBooksCommand`, `GetBookCommand`, `DeleteBookCommand`, `UpdateBookCommand`).
- **Observer & Server-Sent Events (SSE)**: Notificarea in timp real a clientilor cand se adauga o carte noua (`AllBooksSubject`, `SseObserver`, endpoint-ul `/books-sse`).
- **Dependency Injection**: Utilizarea containerului Spring IoC (`SingletonComponent`, `TransientComponent`, `ClientComponent`).

## Tehnologii Utilizate

- Java 25
- Spring Boot
- Spring Data JPA & Hibernate
- H2 Database
- Server-Sent Events (SSE)
- HTML / JavaScript

## Rulare Aplicatie

1. Deschideti proiectul in IntelliJ IDEA (asigurati-va ca JDK-ul este setat pe Java 25).
2. Porniti aplicatia din clasa `SplabApplication` (sau rulati `./mvnw spring-boot:run` in directorul `splab`).
3. Accesati in browser `http://localhost:8080`.

## Functionalitati in Interfata Web

- **Observer & SSE**: Abonare la fluxul de evenimente prin "Fetch Books" si transmiterea imediata a noilor carti adaugate.
- **Gestiune Carti (JPA & Command)**: Afisarea cartilor salvate in baza de date si posibilitatea de stergere a acestora.
- **Structura Document**: Exemple concrete de organizare a elementelor (sectiuni, paragrafe aliniate, proxy-uri).
- **Operatii Document**: Rezultate calculate prin sablonul Visitor (statistici si cuprins).
