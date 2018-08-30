package com.wzq.game.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/some")
    public String getSome() {
        return "FromWzq";
    }

}
