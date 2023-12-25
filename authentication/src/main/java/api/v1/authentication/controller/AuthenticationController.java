package api.v1.authentication.controller;
import org.springframework.kafka.support.serializer.JsonSerializer;
import api.v1.authentication.config.PdfProducer;
import api.v1.authentication.dto.PdfEvent;
import api.v1.authentication.persistency.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    public  PdfProducer producer;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Validated User user,
                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        else
        return ResponseEntity.status(HttpStatus.OK).body("Успешно зарегестрирован !");
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(){
        return ResponseEntity.status(HttpStatus.OK).body("История");
    }

    @GetMapping("/create_pdf")
    public ResponseEntity<?> getfile(@RequestBody User user) {
        PdfEvent pdfEvent=new PdfEvent();
        pdfEvent.setMessage("Привет мир");
        pdfEvent.setStatus(10);
        pdfEvent.setUser(user);
        producer.sendMessage(pdfEvent);
        return ResponseEntity.status(HttpStatus.OK).body("Успешно");
    }
}
