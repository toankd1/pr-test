package toankd.cn.prtest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toankd.cn.prtest.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
