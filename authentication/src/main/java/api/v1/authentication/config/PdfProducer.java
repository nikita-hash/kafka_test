package api.v1.authentication.config;

import api.v1.authentication.dto.PdfEvent;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PdfProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(PdfProducer.class);
    private final String TOPIC="pdf_topic";

    @Autowired
    public PdfProducer(NewTopic topic, KafkaTemplate<String, PdfEvent> kafkaTemplate) {
        this.pdfTopic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    private NewTopic pdfTopic;

    private KafkaTemplate<String, PdfEvent> kafkaTemplate;


    public void sendMessage(PdfEvent pdfEvent){

        LOGGER.info(String.format("Доставка сообщения от пользователя : ",pdfEvent.getUser().getName()));
        kafkaTemplate.send(TOPIC,String.valueOf(pdfEvent.getStatus()),pdfEvent);
    }


}
