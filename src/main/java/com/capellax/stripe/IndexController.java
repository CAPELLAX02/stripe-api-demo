package com.capellax.stripe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; // returns the resources/templates/index.html page
    }

    @GetMapping("/success")
    public String success() {
        return "success"; // similarly returns the success.html page
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "cancel"; // similarly returns the cancel.html page
    }

}
