package com.example.httpPoller.controller;

import com.example.httpPoller.HttpPollerApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class RestartRestController {

    @PostMapping("/restart")
    public void restart() {
        HttpPollerApplication.restart();
    }
}
