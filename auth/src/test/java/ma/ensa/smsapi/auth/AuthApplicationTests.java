package ma.ensa.smsapi.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.ensa.smsapi.auth.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String asJsonString(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void signup() throws Exception{

        final String URL = "http://localhost:8082/auth/singup";

        UserDTO dto = UserDTO.builder()
                .firstName("mohcine")
                .lastName("sahtani")
                .email("mohcine.sahtani.12@gmail.com")
                .phoneNumber("+212704261627")
                .password("mohcine01")
                .build();


        mockMvc.perform(
            post(URL)
                    .contentType("application/json")
                    .content(asJsonString(dto))
        )
        .andDo(r -> System.out.println(r.getResponse().getContentAsString()))
        .andExpect(status().is2xxSuccessful());
    }

}
