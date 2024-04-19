package taskmaster;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import taskmaster.Category.Category;
import taskmaster.Category.CategoryRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestControllerTests {

    @Autowired
    private MockMvc api;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    void setup() {
        this.categoryRepository.save(new Category("test1", "Red"));
        this.categoryRepository.save(new Category("test2", "Blue"));
    }

    @Test
    @Order(1)
    void testGetCategory() throws Exception {
        String accessToken = obtainAccessToken();
        api.perform(get("/api/category").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")));
    }

    @Test
    @Order(2)
    void testGetCategoryClubById() throws Exception {
        String accessToken = obtainAccessToken();
        api.perform(get("/api/category/1").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")));
    }

    @Test
    @Order(3)
    void testNewCategoryClub() throws Exception {
        String accessToken = obtainAccessToken();
        Category category = new Category("test3", "Yellow");
        ObjectMapper objectMapper = new ObjectMapper();
        String boxingJson = objectMapper.writeValueAsString(category);

        api.perform(post("/api/category")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boxingJson)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void testDeleteCategoryClub() throws Exception {
        String accessToken = obtainAccessToken();
        api.perform(delete("/api/category/1")
                        .header("Authorization", "Bearer " + accessToken).with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String obtainAccessToken() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "client_id=taskmaster&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=user&" +
                "password=admin";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> resp = rest.postForEntity(" http://localhost:8080/realms/TaskMaster/protocol/openid-connect/token", entity, String.class);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}