INSERT INTO users (
    name, email, password, phone, home_address, office_address,
    preferred_arrival_start, preferred_arrival_end, flexibility_minutes,
    flexibility_km, role, available_seats, cost_per100kmeur
) VALUES
-- Passengers
('Alice Smith', 'alice.smith@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888123456', '1 Vitosha Blvd, Sofia, Bulgaria', '85 Bulgaria Blvd, Sofia, Bulgaria', '08:00:00', '09:00:00', 15, 5.0, 'PASSENGER', 0, 0),
('Carol Williams', 'carol.williams@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888234567', '15 Stamboliyski Blvd, Sofia, Bulgaria', '1 Bulgaria Square, Sofia, Bulgaria', '07:45:00', '08:45:00', 20, 6.0, 'PASSENGER', 0, 0),
('Eve Davis', 'eve.davis@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888345678', '42 Graf Ignatiev St, Sofia, Bulgaria', '33 Sitnyakovo Blvd, Sofia, Bulgaria', '08:00:00', '09:00:00', 10, 5.0, 'PASSENGER', 0, 0),
('Grace Wilson', 'grace.wilson@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888456789', '12 Rakovski St, Sofia, Bulgaria', '15 Cherni Vrah Blvd, Sofia, Bulgaria', '07:45:00', '08:45:00', 15, 4.5, 'PASSENGER', 0, 0),
('Ivy Taylor', 'ivy.taylor@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888567890', '88 Vasil Levski Blvd, Sofia, Bulgaria', '22 Pencho Slaveikov Blvd, Sofia, Bulgaria', '08:00:00', '09:00:00', 15, 5.5, 'PASSENGER', 0, 0),
('Karen Thomas', 'karen.thomas@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888678901', '5 Alexander Battenberg Square, Sofia, Bulgaria', '44 Georgi S. Rakovski St, Sofia, Bulgaria', '07:45:00', '08:45:00', 10, 4.0, 'PASSENGER', 0, 0),
('Mia White', 'mia.white@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888789012', '25 Tsar Osvoboditel Blvd, Sofia, Bulgaria', '18 James Bourchier Blvd, Sofia, Bulgaria', '08:00:00', '09:00:00', 10, 5.0, 'PASSENGER', 0, 0),
('Olivia Martin', 'olivia.martin@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888890123', '7 William Gladstone St, Sofia, Bulgaria', '55 Dragan Tsankov Blvd, Sofia, Bulgaria', '07:45:00', '08:45:00', 15, 3.5, 'PASSENGER', 0, 0),
('Quinn Walker', 'quinn.walker@example.com', '$2a$10$DowJonesLorem123HashExample', '+359888901234', '3 Ivan Vazov St, Sofia, Bulgaria', '77 Simeonovsko Shose Blvd, Sofia, Bulgaria', '08:00:00', '09:00:00', 15, 4.0, 'PASSENGER', 0, 0),
('Sam Young', 'sam.young@example.com', '$2a$10$DowJonesLorem123HashExample', '+359889012345', '21 Patriarch Evtimiy Blvd, Sofia, Bulgaria', '8 Friedrich Schiller St, Sofia, Bulgaria', '07:45:00', '08:45:00', 10, 5.0, 'PASSENGER', 0, 0),

-- Drivers
('Bob Johnson', 'bob.johnson@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887123456', '10 Knyaz Boris I St, Sofia, Bulgaria', '90 Tsarigradsko Shose Blvd, Sofia, Bulgaria', '08:15:00', '09:15:00', 10, 3.5, 'DRIVER', 3, 12.5),
('David Brown', 'david.brown@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887234567', '20 Angel Kanchev St, Sofia, Bulgaria', '111 Aleksandar Malinov Blvd, Sofia, Bulgaria', '08:30:00', '09:30:00', 15, 4.0, 'DRIVER', 2, 15.0),
('Frank Miller', 'frank.miller@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887345678', '35 Hristo Botev Blvd, Sofia, Bulgaria', '45 Todor Aleksandrov Blvd, Sofia, Bulgaria', '08:15:00', '09:15:00', 20, 3.0, 'DRIVER', 3, 13.0),
('Henry Moore', 'henry.moore@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887456789', '14 Dondukov Blvd, Sofia, Bulgaria', '67 G.M. Dimitrov Blvd, Sofia, Bulgaria', '08:30:00', '09:30:00', 10, 6.0, 'DRIVER', 4, 10.5),
('Jack Anderson', 'jack.anderson@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887567890', '9 Pozitano St, Sofia, Bulgaria', '123 Lomsko Shose St, Sofia, Bulgaria', '08:15:00', '09:15:00', 20, 3.5, 'DRIVER', 3, 12.0),
('Leo Jackson', 'leo.jackson@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887678901', '6 Alabin St, Sofia, Bulgaria', '200 Slivnitsa Blvd, Sofia, Bulgaria', '08:30:00', '09:30:00', 15, 6.5, 'DRIVER', 4, 11.0),
('Nick Harris', 'nick.harris@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887789012', '30 6th September St, Sofia, Bulgaria', '88 Ovcha Kupel Blvd, Sofia, Bulgaria', '08:15:00', '09:15:00', 20, 4.0, 'DRIVER', 3, 13.5),
('Paul Lee', 'paul.lee@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887890123', '18 Han Asparuh St, Sofia, Bulgaria', '150 Brussels Blvd, Sofia, Bulgaria', '08:30:00', '09:30:00', 10, 5.0, 'DRIVER', 2, 15.0),
('Rachel Hall', 'rachel.hall@example.com', '$2a$10$DowJonesLorem123HashExample', '+359887901234', '11 Maria Luiza Blvd, Sofia, Bulgaria', '25 Iskar St, Sofia, Bulgaria', '08:15:00', '09:15:00', 20, 6.0, 'DRIVER', 4, 10.0),
('Tina King', 'tina.king@example.com', '$2a$10$DowJonesLorem123HashExample', '+359886012345', '22 Solunska St, Sofia, Bulgaria', '75 Montevideo St, Sofia, Bulgaria', '08:30:00', '09:30:00', 15, 3.5, 'DRIVER', 3, 12.0);