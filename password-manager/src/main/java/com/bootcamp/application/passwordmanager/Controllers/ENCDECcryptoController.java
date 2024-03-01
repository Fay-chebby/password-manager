package com.bootcamp.application.passwordmanager.Controllers;

import com.bootcamp.application.passwordmanager.DTOs.DecryptedDetails;
import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.models.Password;
import com.bootcamp.application.passwordmanager.service.PO1MService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class ENCDECcryptoController {
    private final PO1MService po1MService;


    @PostMapping("/manage")
    public ResponseEntity<Password> encode(@RequestBody PasswordFront front)throws Exception{
        log.info("request to manage password");
        return ResponseEntity.ok(po1MService.encryptDetails(front));
    }

    @PostMapping("/details")
    public ResponseEntity<DecryptedDetails> decode(@RequestParam Long id)throws Exception{
        log.info("request to get details");
        return ResponseEntity.ok(po1MService.decrypt(id));
    }

}
