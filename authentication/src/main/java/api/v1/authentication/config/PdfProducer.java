package api.v1.authentication.config;

import api.v1.authentication.Dto.PdfEvent;
import api.v1.authentication.persistency.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
public class PdfProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(PdfProducer.class);

    private final KafkaTemplate<String, PdfEvent> kafkaTemplate;

    public void sendMessage(PdfEvent pdfEvent){
        LOGGER.info(String.format("Доставка сообщения от пользователя : ",pdfEvent.getUser().getName()));
        Message<?>message=MessageBuilder.withPayload(pdfEvent)
                .setHeader("asd","asd")
                .build();
        kafkaTemplate.send(message);
    }


}
