package api.v1.authentication.controller;

import api.v1.authentication.persistency.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Validated User user,
                                          BindingResult bindingResult) {

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
