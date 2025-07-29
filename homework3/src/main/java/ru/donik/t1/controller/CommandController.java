package ru.donik.t1.controller;

import ru.donik.t1.model.Command;
import ru.donik.t1.service.DefaultCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    private final DefaultCommandService bishopService;

    public CommandController(DefaultCommandService bishopService) {
        this.bishopService = bishopService;
    }

    @PostMapping("/commands")
    public ResponseEntity<String> submitCommand(@RequestBody Command command) {
        bishopService.executeCommand(command);
        return ResponseEntity.ok("Команда успешно отправлена.");
    }
}
