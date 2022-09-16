package com.wtf.webapp.wtfbe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class GetController {
    @GetMapping(path = "/health")
    public ResponseEntity<String> checkHealth(){
        return new ResponseEntity<>("Hello WTF!", OK);
    }
}
