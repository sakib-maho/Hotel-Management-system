# Hotel Management System (Java)

Refactored console-based hotel management application built with clean package structure, service layer, persistence, and automated tests.

## Highlights

- Maven project structure (`src/main`, `src/test`)
- Domain-driven model classes (`Room`, `Guest`, `FoodOrder`, `RoomType`)
- Service layer for booking, food ordering, checkout, and availability
- Custom exceptions for room availability and lookup failures
- Persistence layer using Java serialization (`backup.dat`)
- JUnit 5 tests for critical booking and billing flows

## Features

- Manage four room categories:
  - Luxury Double
  - Deluxe Double
  - Luxury Single
  - Deluxe Single
- Book rooms with guest details
- Order food per booked room
- Calculate final bill with room + food charges
- Check room availability by category
- Persist data between runs

## Tech Stack

- Java 17
- Maven
- JUnit 5

## Project Structure

```text
.
├── pom.xml
├── src/main/java/com/sakib/hotel/
│   ├── Main.java
│   ├── exception/
│   ├── model/
│   ├── persistence/
│   ├── service/
│   └── util/
└── src/test/java/com/sakib/hotel/service/
```

## Run Locally

```bash
git clone https://github.com/sakib-maho/Hotel-Management-system.git
cd Hotel-Management-system
mvn clean package
java -cp target/classes com.sakib.hotel.Main
```

## Run Tests

```bash
mvn test
```

## Data Persistence

- Runtime data is saved to `backup.dat` on exit.
- On next startup, previous state is automatically loaded if backup exists.

## License

MIT License - see [LICENSE](LICENSE).
