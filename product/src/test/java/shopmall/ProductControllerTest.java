package shopmall;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// spring + junit5
//@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductRepository productRepository;

    @Test
    void productStockCheck() throws Exception {

        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        Long productId = 1L;
        String productName = "CARD";

        // given:
        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setPrice(1000);
        product.setStock(2);

        // given:
        given(this.productRepository.findById(productId)).willReturn(Optional.of(product));


        // when:
        final ResultActions actions = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/product/"+productId));

        // then:
        actions
                .andExpect(content().string(containsString(productName)))
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.name").value(productName))
                .andExpect(ok);
    }

}
