package toankd.cn.prtest.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    @Value("${spring.elasticsearch.host}")
    private String ELASTIC_HOST;

    @Value("${spring.elasticsearch.port}")
    private String ELASTIC_PORT;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo(ELASTIC_HOST + ":" + ELASTIC_PORT)
                        .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
