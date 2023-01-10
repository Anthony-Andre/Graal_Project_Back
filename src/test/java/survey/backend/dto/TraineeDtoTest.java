package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

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
        long traineeId = 2;
        String traineePhoneNumber = "+33(6)22222222";

        var traineeDto = TraineeDto.builder()
                .id(2L)
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
        long traineeId = 1;
        String traineeEmail = "sly-stallone@test.fr";
        String traineePhoneNumber = "+33(6)22222222";
       var birthDate = new Date(1946,7,6);
        var traineeDto = new TraineeDto(
                1L,
                "Stallone",
                "Sly",
                "sly-stallone@test.fr",
                "+33(6)22222222",
                new Date(1946,7,6));

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




