# API Endpoint Examples with Valid JSON Requests

## Authentication Endpoints

### POST /auth/register - Register Driver
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "StrongPass123!",
  "phone": "+359888123456",
  "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
  "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "DRIVER",
  "availableSeats": 3,
  "costPer100KmEUR": 12.5
}
```
**Response Example:**
```json
"eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSaWRlVG9nZXRoZXJBcHAiLCJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlkIjoiMSIsInJvbGUiOiJEUklWRVIiLCJpYXQiOjE2ODg1MjEyMDAsImV4cCI6MTY4OTEyNjAwMH0..."
```

### POST /auth/register - Register Passenger
```json
{
  "name": "Jane Smith",
  "email": "jane.smith@example.com",
  "password": "SecurePass456!",
  "phone": "+359888765432",
  "homeAddress": "15 Stamboliyski Blvd, Sofia, Bulgaria",
  "officeAddress": "1 Bulgaria Square, Sofia, Bulgaria",
  "preferredArrivalStart": "08:15:00",
  "preferredArrivalEnd": "09:15:00",
  "flexibilityMinutes": 20,
  "flexibilityKm": 3.5,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```
**Response Example:**
```json
"eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSaWRlVG9nZXRoZXJBcHAiLCJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlkIjoiMSIsInJvbGUiOiJEUklWRVIiLCJpYXQiOjE2ODg1MjEyMDAsImV4cCI6MTY4OTEyNjAwMH0..."
```


### POST /auth/login
```json
{
  "email": "john.doe@example.com",
  "password": "StrongPass123!"
}
```

**Response Example:**
```json
"eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSaWRlVG9nZXRoZXJBcHAiLCJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlkIjoiMSIsInJvbGUiOiJEUklWRVIiLCJpYXQiOjE2ODg1MjEyMDAsImV4cCI6MTY4OTEyNjAwMH0..."
```

### GET /auth/test
**Response:**
```
"hey acces to auth/ endpoints works"
```

## User Endpoints

### GET /users
**Response Example:**
```json
[
  {
    "id": 1,
    "name": "Alice Smith",
    "email": "alice.smith@example.com",
    "password": "$2a$10$...",
    "phone": "+359888123456",
    "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
    "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
    "preferredArrivalStart": "08:00:00",
    "preferredArrivalEnd": "09:00:00",
    "flexibilityMinutes": 15,
    "flexibilityKm": 5.0,
    "role": "PASSENGER",
    "availableSeats": 0,
    "costPer100KmEUR": 0.0
  }
]
```

### GET /users/{id}
**Response Example:**
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "password": "$2a$10$...",
  "phone": "+359888123456",
  "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
  "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```

### POST /users
```json
{
  "name": "New User",
  "email": "new.user@example.com",
  "password": "$2a$10$encodedPassword",
  "phone": "+359888999888",
  "homeAddress": "42 Graf Ignatiev St, Sofia, Bulgaria",
  "officeAddress": "33 Sitnyakovo Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:30:00",
  "preferredArrivalEnd": "09:30:00",
  "flexibilityMinutes": 10,
  "flexibilityKm": 4.0,
  "role": "DRIVER",
  "availableSeats": 2,
  "costPer100KmEUR": 11.0
}
```

### PUT /users/{id}
```json
{
  "id": 1,
  "name": "Updated User Name",
  "email": "updated.email@example.com",
  "password": "$2a$10$encodedPassword",
  "phone": "+359888111222",
  "homeAddress": "New Home Address, Sofia, Bulgaria",
  "officeAddress": "New Office Address, Sofia, Bulgaria",
  "preferredArrivalStart": "07:30:00",
  "preferredArrivalEnd": "08:30:00",
  "flexibilityMinutes": 25,
  "flexibilityKm": 6.0,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```

### DELETE /users/{id}
```json
{
  "id": 1,
  "name": "User To Delete",
  "email": "delete.me@example.com",
  "password": "$2a$10$encodedPassword",
  "phone": "+359888333444",
  "homeAddress": "Some Address, Sofia, Bulgaria",
  "officeAddress": "Some Office, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```

## Driver Endpoints

### GET /users/drivers
**Response Example:**
```json
[
  {
    "id": 2,
    "name": "Bob Johnson",
    "email": "bob.johnson@example.com",
    "phone": "+359887123456",
    "homeAddress": "10 Knyaz Boris I St, Sofia, Bulgaria",
    "officeAddress": "90 Tsarigradsko Shose Blvd, Sofia, Bulgaria",
    "preferredArrivalStart": "08:15:00",
    "preferredArrivalEnd": "09:15:00",
    "flexibilityMinutes": 10,
    "flexibilityKm": 3.5,
    "role": "DRIVER",
    "availableSeats": 3,
    "costPer100KmEUR": 12.5
  }
]
```

### GET /users/drivers/{id}
**Response Example:**
```json
{
  "id": 2,
  "name": "Bob Johnson",
  "email": "bob.johnson@example.com",
  "phone": "+359887123456",
  "homeAddress": "10 Knyaz Boris I St, Sofia, Bulgaria",
  "officeAddress": "90 Tsarigradsko Shose Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:15:00",
  "preferredArrivalEnd": "09:15:00",
  "flexibilityMinutes": 10,
  "flexibilityKm": 3.5,
  "role": "DRIVER",
  "availableSeats": 3,
  "costPer100KmEUR": 12.5
}
```

### GET /users/drivers/{id}/matching-drivers
**Response Example:**
```json
[
  {
    "id": 1,
    "name": "Alice Smith",
    "email": "alice.smith@example.com",
    "phone": "+359888123456",
    "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
    "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
    "preferredArrivalStart": "08:00:00",
    "preferredArrivalEnd": "09:00:00",
    "flexibilityMinutes": 15,
    "flexibilityKm": 5.0,
    "role": "PASSENGER"
  }
]
```

## Passenger Endpoints

### GET /users/passengers
**Response Example:**
```json
[
  {
    "id": 1,
    "name": "Alice Smith",
    "email": "alice.smith@example.com",
    "phone": "+359888123456",
    "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
    "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
    "preferredArrivalStart": "08:00:00",
    "preferredArrivalEnd": "09:00:00",
    "flexibilityMinutes": 15,
    "flexibilityKm": 5.0,
    "role": "PASSENGER"
  }
]
```

### GET /users/passengers/{id}
**Response Example:**
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "phone": "+359888123456",
  "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
  "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "PASSENGER"
}
```

### GET /users/passengers/{id}/matching-drivers
**Response Example:**
```json
[
  {
    "id": 2,
    "name": "Bob Johnson",
    "email": "bob.johnson@example.com",
    "phone": "+359887123456",
    "homeAddress": "10 Knyaz Boris I St, Sofia, Bulgaria",
    "officeAddress": "90 Tsarigradsko Shose Blvd, Sofia, Bulgaria",
    "preferredArrivalStart": "08:15:00",
    "preferredArrivalEnd": "09:15:00",
    "flexibilityMinutes": 10,
    "flexibilityKm": 3.5,
    "role": "DRIVER",
    "availableSeats": 3,
    "costPer100KmEUR": 12.5
  }
]
```

## Error Response Examples

### 400 Bad Request (Validation Error)
```json
[
  "name: must not be blank",
  "email: Invalid email address",
  "password: Password must have at least 8 chars, one uppercase, one lowercase, one digit, and one special char",
  "phone: Invalid phone number"
]
```

### 401 Unauthorized
```json
"Invalid credentials"
```

### 404 Not Found
```json
"There is no driver with id: 99"
```

### 500 Internal Server Error
```json
"An unexpected error occurred: Database connection failed"
```

## Validation Rules Summary

### RegisterRequest Validation:
- **name**: Must not be blank
- **email**: Must be valid email format and not blank
- **password**: Must match pattern `^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}# API Endpoint Examples with Valid JSON Requests

## Authentication Endpoints

### POST /auth/register - Register Driver
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "StrongPass123!",
  "phone": "+359888123456",
  "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
  "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "DRIVER",
  "availableSeats": 3,
  "costPer100KmEUR": 12.5
}
```

### POST /auth/register - Register Passenger
```json
{
  "name": "Jane Smith",
  "email": "jane.smith@example.com",
  "password": "SecurePass456!",
  "phone": "+359888765432",
  "homeAddress": "15 Stamboliyski Blvd, Sofia, Bulgaria",
  "officeAddress": "1 Bulgaria Square, Sofia, Bulgaria",
  "preferredArrivalStart": "08:15:00",
  "preferredArrivalEnd": "09:15:00",
  "flexibilityMinutes": 20,
  "flexibilityKm": 3.5,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```

### POST /auth/login
```json
{
  "email": "john.doe@example.com",
  "password": "StrongPass123!"
}
```

**Response Example:**
```json
"eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSaWRlVG9nZXRoZXJBcHAiLCJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlkIjoiMSIsInJvbGUiOiJEUklWRVIiLCJpYXQiOjE2ODg1MjEyMDAsImV4cCI6MTY4OTEyNjAwMH0..."
```

### GET /auth/test
**Response:**
```
"hey acces to auth/ endpoints works"
```

## User Endpoints

### GET /users
**Response Example:**
```json
[
  {
    "id": 1,
    "name": "Alice Smith",
    "email": "alice.smith@example.com",
    "password": "$2a$10$...",
    "phone": "+359888123456",
    "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
    "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
    "preferredArrivalStart": "08:00:00",
    "preferredArrivalEnd": "09:00:00",
    "flexibilityMinutes": 15,
    "flexibilityKm": 5.0,
    "role": "PASSENGER",
    "availableSeats": 0,
    "costPer100KmEUR": 0.0
  }
]
```

### GET /users/{id}
**Response Example:**
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice.smith@example.com",
  "password": "$2a$10$...",
  "phone": "+359888123456",
  "homeAddress": "1 Vitosha Blvd, Sofia, Bulgaria",
  "officeAddress": "85 Bulgaria Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```

### POST /users
```json
{
  "name": "New User",
  "email": "new.user@example.com",
  "password": "$2a$10$encodedPassword",
  "phone": "+359888999888",
  "homeAddress": "42 Graf Ignatiev St, Sofia, Bulgaria",
  "officeAddress": "33 Sitnyakovo Blvd, Sofia, Bulgaria",
  "preferredArrivalStart": "08:30:00",
  "preferredArrivalEnd": "09:30:00",
  "flexibilityMinutes": 10,
  "flexibilityKm": 4.0,
  "role": "DRIVER",
  "availableSeats": 2,
  "costPer100KmEUR": 11.0
}
```

### PUT /users/{id}
```json
{
  "id": 1,
  "name": "Updated User Name",
  "email": "updated.email@example.com",
  "password": "$2a$10$encodedPassword",
  "phone": "+359888111222",
  "homeAddress": "New Home Address, Sofia, Bulgaria",
  "officeAddress": "New Office Address, Sofia, Bulgaria",
  "preferredArrivalStart": "07:30:00",
  "preferredArrivalEnd": "08:30:00",
  "flexibilityMinutes": 25,
  "flexibilityKm": 6.0,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```

### DELETE /users/{id}
```json
{
  "id": 1,
  "name": "User To Delete",
  "email": "delete.me@example.com",
  "password": "$2a$10$encodedPassword",
  "phone": "+359888333444",
  "homeAddress": "Some Address, Sofia, Bulgaria",
  "officeAddress": "Some Office, Sofia, Bulgaria",
  "preferredArrivalStart": "08:00:00",
  "preferredArrivalEnd": "09:00:00",
  "flexibilityMinutes": 15,
  "flexibilityKm": 5.0,
  "role": "PASSENGER",
  "availableSeats": 0,
  "costPer100KmEUR": 0.0
}
```


- **phone**: Must match pattern `\\+?[0-9]{10,15}`
- **flexibilityMinutes**: Must be >= 0
- **flexibilityKm**: Must be >= 0.0
- **role**: Must not be null (DRIVER or PASSENGER)
- **availableSeats**: Required for DRIVER role
- **costPer100KmEUR**: Required for DRIVER role

### Phone Number Format Examples:
- Valid: `+359888123456`, `359888123456`, `12345678901`
- Invalid: `123-456-7890`, `+1 (555) 123-4567`, `123`

### Time Format:
- Use 24-hour format: `"08:30:00"` for 8:30 AM
- Use `"HH:mm:ss"` pattern