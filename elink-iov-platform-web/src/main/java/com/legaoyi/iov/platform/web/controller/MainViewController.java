package com.legaoyi.iov.platform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainViewController {

    @GetMapping({"/{page}"})
    public String view(@PathVariable String page) {
        return "/" + page;
    }

    @GetMapping({"/{path}/{page}"})
    public String view(@PathVariable String path, @PathVariable String page) {
        return "/" + path + "/" + page;
    }

}
