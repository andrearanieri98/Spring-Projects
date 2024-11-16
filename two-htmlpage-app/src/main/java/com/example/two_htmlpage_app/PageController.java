package com.example.two_htmlpage_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/page1")
    public String showPage1() {
        return "page1";  // Looks for page1.html in src/main/resources/templates
    }

    @GetMapping("/page2")
    public String showPage2() {
        return "page2";  // Looks for page2.html in src/main/resources/templates
    }
}

