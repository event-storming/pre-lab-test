package shopmall;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductController2Test {

    @Autowired
    MockMvc mockMvc;

//    @Autowired
//    WebApplicationContext webApplicationContext;

    @Test
    void getEnvValue() throws Exception {

        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        // given:
        String orderUrl = "http://localhost:8081";
//        ProductApplication.applicationContext = webApplicationContext;

        // when:
        final ResultActions actions = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/envValue"));
//                .perform(MockMvcRequestBuilders.get("/env"));

        // then:
        actions
                .andExpect(content().string(containsString(orderUrl)))
                .andExpect(ok);
    }

}
