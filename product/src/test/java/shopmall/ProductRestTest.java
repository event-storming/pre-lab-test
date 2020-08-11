package shopmall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest
public class ProductRestTest {

    @Autowired
    private MockRestServiceServer mockServer;

    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();

        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
    }

    @Test
    public void getProductsTest() {

        /**
         * MockRestServiceServer 는 url 을 보냈을때 가상의 리턴값을 셋팅하는 방식이다.
         */
        String url = "/products" + "/1";
        this.mockServer.expect(requestTo(url))
                .andRespond(withSuccess(new ClassPathResource("/productsData.json", getClass()), MediaType.APPLICATION_JSON));

        Long productId = 1L;
        String productName = "CARD";

        // given:
        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setPrice(1000);
        product.setStock(2);

        // when
        ResponseEntity<Product> productEntity = restTemplate.getForEntity(url, Product.class);
        Product productMap = productEntity.getBody();

        // then
        assertThat(productMap.getName()).isEqualTo("CARD");
        assertThat(productMap.getPrice()).isEqualTo(1000);


    }



}