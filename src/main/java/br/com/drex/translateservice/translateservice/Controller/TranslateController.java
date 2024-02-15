package br.com.drex.translateservice.translateservice.Controller;

import br.com.drex.translateservice.translateservice.Model.TranslateRequest;
import br.com.drex.translateservice.translateservice.Service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @PostMapping("/translate")
    public ResponseEntity<String> translate(@RequestBody TranslateRequest translateRequest) {
        try {
            return new ResponseEntity<>(TranslateService.translateText(translateRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
