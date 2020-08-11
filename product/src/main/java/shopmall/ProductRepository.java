package shopmall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // http http://localhost:8082/products/search/findByName\?name\=TV
    // http http://localhost:8082/products/search/findByName name==TV
    List<Product> findByName(String name);

    // http http://localhost:8082/products/search/findByStockAndName name==TV stock==10
    List<Product> findByStockAndName(int stock, String name);

    // http http://localhost:8082/products/search/getName\?name\=TV
    @Query(
            value = "SELECT * FROM Product u WHERE u.name = ?1 ",
            nativeQuery = true)
    List<Product> getName(String name);
}
