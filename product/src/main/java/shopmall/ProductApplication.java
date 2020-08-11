package shopmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ProductApplication {
    public static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(ProductApplication.class, args);

        ProductRepository productRepository = applicationContext.getBean(ProductRepository.class);
        // 초기 상품 셋팅
        String[] products = {"TV", "MASK", "NOTEBOOK", "TABLE", "CLOCK"};
        int i = 1;
        for(String p : products){
            Product product = new Product();
            product.setName(p);
            product.setPrice(i*10000);
            product.setStock(10);
            i++;
            productRepository.save(product);
        }
    }
}
