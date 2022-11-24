package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaultConstructor(){
        // when
        var traineeDto = new TraineeDto();
        // then
        assertAll(
                () -> assertNull(traineeDto.getId(), "id"),
                () -> assertNull(traineeDto.getFirstname(), "firstname"),
                () -> assertNull(traineeDto.getLastname(), "lastname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthdate(), "birthdate"));
    }

    // TODO: builder

    @Test
    void testBuilder(){

        var traineeFirstName = "Sly";
        int traineeId = 2;
        String traineePhoneNumber = "+33(6)22222222";

        var traineeDto = TraineeDto.builder()
                .id(2)
                .firstname("Sly")
                .phoneNumber("+33(6)22222222")
                .build();

        assertAll(
                () -> assertEquals(traineeId, traineeDto.getId(), "id"),
                () -> assertNull(traineeDto.getLastname(), "lastname"),
                () -> assertEquals(traineeFirstName, traineeDto.getFirstname(), "firstname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertEquals(traineePhoneNumber, traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthdate(), "birthdate")
        );

    }

    // TODO: all args constructor

    @Test
    void testAllArgsConstructor(){

        String traineeFirstName = "Sly";
        String traineeLastName = "Stallone";
        int traineeId = 1;
        String traineeEmail = "sly-stallone@test.fr";
        String traineePhoneNumber = "+33(6)22222222";
        LocalDate birthDate = LocalDate.of(1946,7,6);
        var traineeDto = new TraineeDto(
                1,
                "Stallone",
                "Sly",
                "sly-stallone@test.fr",
                "+33(6)22222222",
                LocalDate.of(1946,7,6));

        assertAll(
                () -> assertEquals(traineeId, traineeDto.getId(), "id"),
                () -> assertEquals(traineeLastName, traineeDto.getLastname(), "lastname"),
                () -> assertEquals(traineeFirstName, traineeDto.getFirstname(), "firstname"),
                () -> assertEquals(traineeEmail, traineeDto.getEmail(), "email"),
                () -> assertEquals(traineePhoneNumber, traineeDto.getPhoneNumber(), "phone number"),
                () -> assertEquals(birthDate, traineeDto.getBirthdate(), "birthdate")
        );

    }

}




