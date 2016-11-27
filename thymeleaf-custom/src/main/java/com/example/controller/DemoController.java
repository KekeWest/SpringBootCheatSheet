package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.URL;

/**
 * デモ用のコントローラー
 *
 * @author keke
 *
 */
@Controller
public class DemoController {

    @GetMapping(path = URL.DEMO1)
    public String demo1() {
        return "demo1";
    }

    @GetMapping(path = URL.DEMO2)
    public String demo2() {
        return "demo2";
    }

}
