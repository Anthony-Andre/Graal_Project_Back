package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import survey.backend.dto.TraineeDto;
import survey.backend.handler.controller.TraineeController;
import survey.backend.service.TraineeService;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TraineeController.class)
class TraineeControllerTest {

    final static String BASE_URL = "/api/trainee";

    // Component to call TraineeController with HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TraineeService traineeService;

    @Test
    void testGetByIdOk() throws Exception {

        // prepare
        long id = 123;
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastname("Stallone")
                .firstname("Sly")
                .build();
        given(traineeService.findById(id))
                .willReturn(Optional.of(traineeDto))
        ;

        // when
        mockMvc.perform(get("/api/trainee/123")
                        .accept(MediaType.APPLICATION_JSON))

                // then / verify Http communication
                .andDo(print()) // log request/response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));

        // then / verify Mock service has been called
        then(traineeService)
                .should()
                .findById(id);
    }

    @Test
    void testGetByIdKoNotFound() throws Exception {

        // prepare
        long id = 0;
        var traineeDto = TraineeDto.builder().build();
        given(traineeService.findById(id))
                .willReturn(Optional.empty())
        ;

        // when
        mockMvc.perform(get(BASE_URL + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))

                // then / verify Http communication
                .andDo(print()) // log request/response
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Trainee with id " + id + " not found"));

        // then / verify Mock service has been called
        then(traineeService)
                .should()
                .findById(id);

    }

}