package shopmall;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

// spring + junit4
@RunWith(SpringRunner.class)
// @Entity 클레스 스켄
@DataJpaTest
// 실제 데이터베이스를 테스트 하기 위하여 아래 어노테이션이 필요함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void beforeTest(){
        Product product = new Product();
        product.setName("CARD");
        product.setPrice(1000);
        product.setStock(2);

        entityManager.persist(product);
    }

    @Test
    public void 상품_생성_테스트(){


        Product product = new Product();
        product.setName("CARD");
        product.setPrice(1000);
        product.setStock(2);

        List<Product> products = productRepository.findByName("CARD");

        assertThat(products)
                .isNotEmpty()
                .hasSize(1);

        for(Product p: products){
            then(1000).isEqualTo(p.getPrice());
            then(2).isEqualTo(p.getStock());
        }
    }

}