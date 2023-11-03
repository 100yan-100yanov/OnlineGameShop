package com.playtray.service.impl;

import com.playtray.repository.ConsoleRepository;
import com.playtray.service.ConsoleService;
import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final ConsoleRepository consoleRepository;

    public ConsoleServiceImpl(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }
}
