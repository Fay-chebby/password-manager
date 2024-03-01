package com.bootcamp.application.passwordmanager.Controllers;

import com.bootcamp.application.passwordmanager.DTOs.PasswordFront;
import com.bootcamp.application.passwordmanager.models.Password;
import com.bootcamp.application.passwordmanager.service.PO1MService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class ENCDECcryptoController {
    private final PO1MService po1MService;

    @PostMapping("/manage")
    public ResponseEntity<Password> saveManaged(@RequestBody PasswordFront front)throws Exception{
        log.info("request to manage details");
        return ResponseEntity.ok(po1MService.passwordToManage(front));
    }
}
