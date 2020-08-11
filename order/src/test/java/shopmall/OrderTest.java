package shopmall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

// @Entity 클레스 스켄
@DataJpaTest
// 실제 데이터베이스를 테스트 하기 위하여 아래 어노테이션이 필요함
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void beforeTest(){
        Order order = new Order();
        order.setProductId(1L);
        order.setProductName("TV");
        order.setQty(3);

        entityManager.persist(order);
    }

    @Test
    public void 주문_생성_테스트(){


        Order order = new Order();
        order.setProductId(1L);
        order.setProductName("TV");
        order.setQty(3);

        Order orderData = orderRepository.findById(1L).get();

        then(order.getProductId()).isEqualTo(orderData.getProductId());
        then(order.getProductName()).isEqualTo(orderData.getProductName());
    }

}