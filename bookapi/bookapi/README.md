# BookAPI

BookAPI è un'applicazione RESTful realizzata con **Spring Boot** che gestisce una raccolta di libri. L'accesso alle API è protetto da **JWT (JSON Web Token)** per distinguere tra utenti autenticati (admin) e richieste pubbliche.

## Funzionalità principali

- Login con JWT: gli admin ricevono un token da utilizzare per accedere alle funzionalità riservate.
- CRUD dei libri: crea, leggi, aggiorna ed elimina i libri (solo admin).
- Accesso pubblico: lettura dei dati disponibile anche senza autenticazione.
- Struttura RESTful: conforme agli standard HTTP con gestione corretta di status code (401, 403, 200, ecc.).

## Tecnologie usate

- Java 21
- Spring Boot 3
- Spring Security
- JWT (jjwt)
- Maven
- Postman per il test degli endpoint

## Esecuzione dell'applicazione

### Requisiti

- JDK 21
- Maven 3.8+
- (Opzionale) Postman o altro client HTTP

### Avvio dell'app

```bash
./mvnw spring-boot:run
```

L'app sarà avviata su: `http://localhost:8080`

## Autenticazione

### Login (admin)

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin"
}
```

Risposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Utilizza il token nelle richieste protette:
```
Authorization: Bearer <token>
```

## API principali

### Libri (accesso pubblico / admin)

- `GET /books` → Tutti i libri
- `GET /books/{id}` → Libro per ID

### Libri (solo admin)

- `POST /books` → Aggiungi libro
- `PUT /books/{id}` → Modifica libro
- `DELETE /books/{id}` → Elimina libro

## Status Code usati

| Codice | Descrizione                         |
|--------|-------------------------------------|
| 200    | OK                                  |
| 201    | Creato                              |
| 401    | Non autenticato (token mancante/scaduto) |
| 403    | Accesso vietato (ruolo mancante)    |
| 404    | Risorsa non trovata                 |

Il frontend può usare questi status per mostrare messaggi user-friendly (es. "Sessione scaduta, rifai il login").

## Struttura del progetto

```
src
 └── main
     ├── java
     │   └── com.example.bookapi
     │       ├── controller
     │       ├── model
     │       ├── repository
     │       ├── security
     │       └── service
     └── resources
         └── application.properties
```



## Autore

*Andrea Ranieri*