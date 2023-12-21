package api.v1.authentication.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, PdfProducer> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        // Здесь можно добавить другие настройки
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, PdfProducer> kafkaTemplate() {
        return new KafkaTemplate<String, PdfProducer>(producerFactory());
    }
}
