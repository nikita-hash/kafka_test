package api.v2.pdf_creator.config;

import api.v2.pdf_creator.dto.PdfEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PdfConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(PdfConsumer.class);
    private final String TOPIC="pdf_topic";

    @KafkaListener(topics = "pdf_topic",groupId = "pdfFile")
    public void consume( PdfEvent pdfEvent){
        LOGGER.info(String.format("Событие : %s",pdfEvent.toString()));
    }

    @Bean
    public DefaultJackson2JavaTypeMapper jackson2JavaTypeMapper() {
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("pdfEvent", PdfEvent.class); // здесь указываем имя типа и соответствующий класс
        typeMapper.setIdClassMapping(mappings);
        typeMapper.addTrustedPackages("api.v1.authentication.dto");
        return typeMapper;
    }

}
