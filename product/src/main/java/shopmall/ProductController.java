package shopmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product/{productId}")
    Product productStockCheck(@PathVariable(value = "productId") Long productId) {
        return  this.productRepository.findById(productId).get();
    }

    @PostMapping("/product")
    void productInsert(@RequestBody Product data) {
        System.out.println(data);
        this.productRepository.save(data);
    }

    @Value("${api.url.order}")
    String orderUrl;

    @Value("${api.url.orderDefault:http://order:8080}")
    String orderUrlDefault;

    @Value("${api.url.delivery}")
    String deliveryUrl;

    @Autowired
    Environment env;
    /**
     * env 혹은 설정 값을 가져오는 테스트 코드
     */
    @GetMapping("/env")
    String getEnvSample() {

        // 1. applicationContext 에서 가져오기
        Environment envContext = ProductApplication.applicationContext.getEnvironment();
        System.out.println(" applicationContext = " + envContext.getProperty("api.url.order"));

        // 2. Autowired Environment
        System.out.println(" Autowired Environment = " + env.getProperty("api.url.order"));

        // 3. @Value
        System.out.println(" @Value = " + orderUrl);

        return orderUrl;
    }

    @GetMapping("/envValue")
    String getEnvValue() {
        return orderUrl;
    }

    @GetMapping("/envDefault")
    String getEnvDefault() {

        // 1. applicationContext 에서 가져오기
        Environment envContext = ProductApplication.applicationContext.getEnvironment();
        System.out.println(" applicationContext = " + envContext.getProperty("api.url.delivery"));

        // 2. Autowired Environment
        System.out.println(" Autowired Environment = " + env.getProperty("api.url.delivery"));

        // 3. @Value
        System.out.println(" @Value orderUrlDefault = " + orderUrlDefault);
        System.out.println(" @Value deliveryUrl = " + deliveryUrl);

        return deliveryUrl;
    }

}
