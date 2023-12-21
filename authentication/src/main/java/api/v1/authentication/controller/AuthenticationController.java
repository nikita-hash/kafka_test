package api.v1.authentication.controller;

import api.v1.authentication.config.PdfProducer;
import api.v1.authentication.persistency.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import api.v1.authentication.dto.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    public  PdfProducer producer;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Validated User user,
                                          BindingResult bindingResult) {
        User user1=new User();
        user1.setEmail("Email");
        user1.setName("Никита");
        user1.setPassword("Password");
        producer.sendMessage(
                PdfEvent
                        .builder()
                        .message("Создать файл")
                        .user(user1)
                        .status(10).build());
        return ResponseEntity.status(HttpStatus.OK).body("Успешно зарегестрирован !");
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(){
        return ResponseEntity.status(HttpStatus.OK).body("История");
    }

    @GetMapping("/create_pdf")
    public ResponseEntity<?> getfile() {
        return ResponseEntity.status(HttpStatus.OK).body(new byte[200]);
    }
}
