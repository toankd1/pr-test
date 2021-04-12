package toankd.cn.prtest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toankd.cn.prtest.entities.Product;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product where name like %?1%", nativeQuery = true)
    List<Product> findByName(String name);
}
