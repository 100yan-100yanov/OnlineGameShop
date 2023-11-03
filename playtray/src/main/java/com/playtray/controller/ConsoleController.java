package com.playtray.controller;

import com.playtray.service.ConsoleService;
import org.springframework.stereotype.Controller;

@Controller
public class ConsoleController {

    private final ConsoleService consoleService;

    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }
}
