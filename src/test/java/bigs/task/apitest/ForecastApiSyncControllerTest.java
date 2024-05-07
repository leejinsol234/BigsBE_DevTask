package bigs.task.apitest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.profiles.active=test")
public class ForecastApiSyncControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST 요청시 공공 데이터 포털 API를 호출하여 DB에 적재 테스트")
    void syncTest() throws Exception {
        mockMvc.perform(post("/api/v1/forecast/sync"))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
