## BnBit – Hotel Booking Application

BnBit is an application connecting travelers with hosts offering unique stays (homes, apartments, castles) and experiences (cooking, hiking) in private properties, acting as a broker for short-term rentals.
It handles authentication, hotel & room management, availability and dynamic pricing, bookings, and Stripe‑based payments.

### Tech Stack
- **Language**: Java 21
- **Framework**: Spring Boot 3 (Web, Data JPA, Security, Scheduling)
- **Database**: PostgreSQL
- **Auth**: JWT (access + refresh tokens)
- **Build**: Maven
- **Other**: ModelMapper, Stripe Java SDK, springdoc‑openapi

### Core Features
- **Authentication & users**
  - Sign‑up, login, refresh token (access + HttpOnly refresh cookie).
  - Role‑based access (e.g. `GUEST`, `HOTEL_MANAGER`) via Spring Security.
- **Hotels & rooms**
  - Hotel creation and management by hotel owners.
  - Rooms with base price, capacity, total count, amenities, photos.
- **Inventory & dynamic pricing**
  - Per‑day `Inventory` records for each room (availability, surge factor, price, open/closed).
  - One‑year inventory initialization for new rooms.
  - Dynamic pricing pipeline using multiple strategies (surge, occupancy, urgency, holiday).
- **Search & browsing**
  - Search hotels by city, date range, and rooms count.
  - Returns hotels with continuous availability and aggregated minimum price.
- **Booking & payments**
  - Multi‑step booking: reserve inventory → add guests → initiate Stripe checkout → confirm via webhook.
  - Cancellation and automatic Stripe refund for confirmed bookings.
  - Hotel‑level reports (total bookings, revenue, average revenue).

### Project Structure (src/main/java/com/projects/BnBit)
- `BnBitAppApplication` – Application entrypoint (`@SpringBootApplication`, `@EnableScheduling`).
- `controller` – REST controllers:
  - `AuthController` – `/auth/signup`, `/auth/login`, `/auth/refresh`.
  - `HotelBrowseController` – public hotel search & info endpoints.
  - `HotelController`, `RoomAdminController`, `InventoryController` – hotel/room/inventory admin APIs.
  - `HotelBookingController`, `UserController`, `WebhookController` – bookings, user operations, Stripe webhook.
- `service` – Business logic:
  - `*Service` + `*ServiceImpl` for hotels, rooms, inventory, bookings, guests, users, checkout, pricing updates.
- `entity` & `repository` – JPA entities and Spring Data repositories (Hotel, Room, Inventory, Booking, User, Guest, etc.).
- `security` – JWT security:
  - `WebSecurityConfig`, `JWTAuthFilter`, `JWTService`, `AuthService`.
- `strategy` – Dynamic pricing strategies and `PricingService`.
- `advice` – Global exception & response handling.
- `config` – CORS, ModelMapper, Stripe configuration.
- `util` – `AppUtils` for helpers (e.g. getting the current user).

### Configuration & Profiles
Configuration lives under `src/main/resources`:

- `application.properties`
  - Sets app name and active profile:
    - `spring.application.name=BnBit`
    - `spring.profiles.active=dev` (default; override with env or CLI).
- `application-dev.properties`
  - Local/dev settings (PostgreSQL on localhost, dev JWT secret, local frontend URL, Stripe test keys).
- `application-prod.properties`
  - Production template (placeholders for DB, JWT, frontend URL, Stripe live keys).

> **Note**: `application-dev.properties` and `application-prod.properties` are **ignored by git** (`.gitignore`) so you can safely put secrets there locally.  
> Do **not** commit real secrets.

### Running the Project

#### Prerequisites
- Java 21 installed.
- PostgreSQL running and a database created (e.g. `BnBit`).
- Stripe account & API keys (for payment functionality).

#### 1. Configure dev profile
Edit `src/main/resources/application-dev.properties` with your local values:
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `jwt.secretKey`
- `frontend.url`
- `stripe.secret.key`
- `stripe.webhook.secret`

#### 2. Start the app (dev)
From the project root:

```bash
./mvnw spring-boot:run
```

On Windows CMD/PowerShell:

```bash
mvnw spring-boot:run
```

Spring will start with the **dev** profile by default.  
Change profile with:

```bash
mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

or environment variable:

```bash
SPRING_PROFILES_ACTIVE=prod mvnw spring-boot:run
```

### Security & Authorization
- Stateless JWT authentication (access token in header, refresh token in HttpOnly cookie).
- Security rules (see `WebSecurityConfig`):
  - `/admin/**` – requires role `HOTEL_MANAGER`.
  - `/bookings/**`, `/users/**` – require authentication.
  - Other endpoints (like `/auth/**`, search/browse) are public.

### Typical Flow
1. **User registration & login**
   - Client calls `/auth/signup` → `/auth/login`.
   - Receives access token (JSON) and refresh token (cookie).
2. **Search & select hotel/room**
   - Client uses `/hotels/search` and `/hotels/{id}/info` to find suitable options.
3. **Book a stay**
   - Client creates a booking (reserve inventory), adds guests, and initiates payment.
4. **Pay with Stripe**
   - Backend creates a Stripe Checkout Session and returns its URL.
   - Stripe calls the webhook on success; backend marks booking as `CONFIRMED` and confirms inventory.
5. **Manage bookings**
   - User can view their bookings; hotel owners can see all bookings and reports for their hotels.

### Development Notes
- Use your IDE’s “Rebuild project” or `mvnw clean test` to verify changes.
- Sensitive config should stay in `application-*.properties` and **not** in `application.properties` or `pom.xml`.


