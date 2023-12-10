# JBus

JBus is a bus booking application that allows users to book bus tickets, make payments, and manage their bookings.

## Features

- User accounts
  - Users can sign up for an account
  - Accounts store personal details and booking history
- Bus rental companies 
  - Companies can list available buses
  - Buses have schedules, seats, pricing etc.  
- Bus search and booking
  - Users can search for and book bus tickets
  - Booked seats are updated in bus schedule
- Payments and invoices
  - Users make payments for bookings  
  - Invoices track transaction details
- Reviews and ratings
  - Users can leave reviews and ratings on trips

## Models

The main models of the system are:

- **Account** - User account with personal details 
- **Bus** - Represent a bus with details like seats, amenities etc.
- **Renter** - Bus rental company  
- **Schedule** - Availability and bookings for a Bus 
- **Booking** - A ticket booking for a user  
- **Invoice** - Bill for a booking 
- **Payment** - Payment transaction for a booking
- **Review** - Ratings and feedback left by a user  

Relationships exist between the models e.g. a Booking belongs to an Account and Invoice.

## Database

The data is persisted using a JSON file based database with automatic primary key generation. The `Serializable` class handles the ID generation and serialization.

Some of the key classes are:

- **Account** - User accounts
- **Bus** - Available buses  
- **Renter** - Bus companies
- **Schedule** - Bus schedules and availability
- **Payment** - Payments for bookings

## Business Logic

Additional business logic is handled via classes like:

- **Validate** - Input validation helpers 
- **Algorithm** - Generic algorithms for searching, filtering collections
- **BookingThread** - Background booking processor  

## Getting Started

The application is built using Spring Boot and the REST API endpoints are documented ...

To run locally:

1. Clone the repo
2. Run `mvn spring-boot:run`
3. The API will be available at `http://localhost:3000`

The frontend code can be found at...

## Next Steps

Future enhancements for this project:

- Improved search and filters
- Real-time seat availability  
- Webhook integration for notifications
- Analytics dashboard
