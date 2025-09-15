//package com.ridetogether.user_service.config;
//
//import com.ridetogether.user_service.model.User;
//import com.ridetogether.user_service.model.UserRole;
//import com.ridetogether.user_service.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalTime;
//
//@Configuration
//public class DataLoader {
//
//    @Bean
//    CommandLineRunner initDatabase(UserRepository repository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Only load data if database is empty
//            if (repository.count() == 0) {
//                // Create passengers
//                repository.save(createUser("Alice Smith", "alice.smith@example.com", passwordEncoder, "+359888123456",
//                        "1 Vitosha Blvd, Sofia, Bulgaria", "85 Bulgaria Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 0), LocalTime.of(9, 0), 15, 5.0, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Carol Williams", "carol.williams@example.com", passwordEncoder, "+359888234567",
//                        "15 Stamboliyski Blvd, Sofia, Bulgaria", "1 Bulgaria Square, Sofia, Bulgaria",
//                        LocalTime.of(7, 45), LocalTime.of(8, 45), 20, 6.0, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Eve Davis", "eve.davis@example.com", passwordEncoder, "+359888345678",
//                        "42 Graf Ignatiev St, Sofia, Bulgaria", "33 Sitnyakovo Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 0), LocalTime.of(9, 0), 10, 5.0, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Grace Wilson", "grace.wilson@example.com", passwordEncoder, "+359888456789",
//                        "12 Rakovski St, Sofia, Bulgaria", "15 Cherni Vrah Blvd, Sofia, Bulgaria",
//                        LocalTime.of(7, 45), LocalTime.of(8, 45), 15, 4.5, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Ivy Taylor", "ivy.taylor@example.com", passwordEncoder, "+359888567890",
//                        "88 Vasil Levski Blvd, Sofia, Bulgaria", "22 Pencho Slaveikov Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 0), LocalTime.of(9, 0), 15, 5.5, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Karen Thomas", "karen.thomas@example.com", passwordEncoder, "+359888678901",
//                        "5 Alexander Battenberg Square, Sofia, Bulgaria", "44 Georgi S. Rakovski St, Sofia, Bulgaria",
//                        LocalTime.of(7, 45), LocalTime.of(8, 45), 10, 4.0, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Mia White", "mia.white@example.com", passwordEncoder, "+359888789012",
//                        "25 Tsar Osvoboditel Blvd, Sofia, Bulgaria", "18 James Bourchier Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 0), LocalTime.of(9, 0), 10, 5.0, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Olivia Martin", "olivia.martin@example.com", passwordEncoder, "+359888890123",
//                        "7 William Gladstone St, Sofia, Bulgaria", "55 Dragan Tsankov Blvd, Sofia, Bulgaria",
//                        LocalTime.of(7, 45), LocalTime.of(8, 45), 15, 3.5, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Quinn Walker", "quinn.walker@example.com", passwordEncoder, "+359888901234",
//                        "3 Ivan Vazov St, Sofia, Bulgaria", "77 Simeonovsko Shose Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 0), LocalTime.of(9, 0), 15, 4.0, UserRole.PASSENGER, 0, 0));
//
//                repository.save(createUser("Sam Young", "sam.young@example.com", passwordEncoder, "+359889012345",
//                        "21 Patriarch Evtimiy Blvd, Sofia, Bulgaria", "8 Friedrich Schiller St, Sofia, Bulgaria",
//                        LocalTime.of(7, 45), LocalTime.of(8, 45), 10, 5.0, UserRole.PASSENGER, 0, 0));
//
//                // Create drivers
//                repository.save(createUser("Bob Johnson", "bob.johnson@example.com", passwordEncoder, "+359887123456",
//                        "10 Knyaz Boris I St, Sofia, Bulgaria", "90 Tsarigradsko Shose Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 15), LocalTime.of(9, 15), 10, 3.5, UserRole.DRIVER, 3, 12.5));
//
//                repository.save(createUser("David Brown", "david.brown@example.com", passwordEncoder, "+359887234567",
//                        "20 Angel Kanchev St, Sofia, Bulgaria", "111 Aleksandar Malinov Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 30), LocalTime.of(9, 30), 15, 4.0, UserRole.DRIVER, 2, 15.0));
//
//                repository.save(createUser("Frank Miller", "frank.miller@example.com", passwordEncoder, "+359887345678",
//                        "35 Hristo Botev Blvd, Sofia, Bulgaria", "45 Todor Aleksandrov Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 15), LocalTime.of(9, 15), 20, 3.0, UserRole.DRIVER, 3, 13.0));
//
//                repository.save(createUser("Henry Moore", "henry.moore@example.com", passwordEncoder, "+359887456789",
//                        "14 Dondukov Blvd, Sofia, Bulgaria", "67 G.M. Dimitrov Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 30), LocalTime.of(9, 30), 10, 6.0, UserRole.DRIVER, 4, 10.5));
//
//                repository.save(createUser("Jack Anderson", "jack.anderson@example.com", passwordEncoder, "+359887567890",
//                        "9 Pozitano St, Sofia, Bulgaria", "123 Lomsko Shose St, Sofia, Bulgaria",
//                        LocalTime.of(8, 15), LocalTime.of(9, 15), 20, 3.5, UserRole.DRIVER, 3, 12.0));
//
//                repository.save(createUser("Leo Jackson", "leo.jackson@example.com", passwordEncoder, "+359887678901",
//                        "6 Alabin St, Sofia, Bulgaria", "200 Slivnitsa Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 30), LocalTime.of(9, 30), 15, 6.5, UserRole.DRIVER, 4, 11.0));
//
//                repository.save(createUser("Nick Harris", "nick.harris@example.com", passwordEncoder, "+359887789012",
//                        "30 6th September St, Sofia, Bulgaria", "88 Ovcha Kupel Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 15), LocalTime.of(9, 15), 20, 4.0, UserRole.DRIVER, 3, 13.5));
//
//                repository.save(createUser("Paul Lee", "paul.lee@example.com", passwordEncoder, "+359887890123",
//                        "18 Han Asparuh St, Sofia, Bulgaria", "150 Brussels Blvd, Sofia, Bulgaria",
//                        LocalTime.of(8, 30), LocalTime.of(9, 30), 10, 5.0, UserRole.DRIVER, 2, 15.0));
//
//                repository.save(createUser("Rachel Hall", "rachel.hall@example.com", passwordEncoder, "+359887901234",
//                        "11 Maria Luiza Blvd, Sofia, Bulgaria", "25 Iskar St, Sofia, Bulgaria",
//                        LocalTime.of(8, 15), LocalTime.of(9, 15), 20, 6.0, UserRole.DRIVER, 4, 10.0));
//
//                repository.save(createUser("Tina King", "tina.king@example.com", passwordEncoder, "+359886012345",
//                        "22 Solunska St, Sofia, Bulgaria", "75 Montevideo St, Sofia, Bulgaria",
//                        LocalTime.of(8, 30), LocalTime.of(9, 30), 15, 3.5, UserRole.DRIVER, 3, 12.0));
//
//                System.out.println("Database initialized with sample data");
//            }
//        };
//    }
//
//    private User createUser(String name, String email, PasswordEncoder passwordEncoder, String phone,
//                            String homeAddress, String officeAddress, LocalTime startTime, LocalTime endTime,
//                            int flexibilityMinutes, double flexibilityKm, UserRole role,
//                            int availableSeats, double costPer100Km) {
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode("Password123!"));
//        user.setPhone(phone);
//        user.setHomeAddress(homeAddress);
//        user.setOfficeAddress(officeAddress);
//        user.setPreferredArrivalStart(startTime);
//        user.setPreferredArrivalEnd(endTime);
//        user.setFlexibilityMinutes(flexibilityMinutes);
//        user.setFlexibilityKm(flexibilityKm);
//        user.setRole(role);
//        user.setAvailableSeats(availableSeats);
//        user.setCostPer100KmEUR(costPer100Km);
//        return user;
//    }
//}