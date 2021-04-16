package toankd.cn.prtest.elastic.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import toankd.cn.prtest.elastic.index.ProductIndex;

@Repository
public interface ProductIndexRepo extends ElasticsearchRepository<ProductIndex, String> {
}
