package com.rama.app;

import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    @RequestMapping(value="/request/hello",method = RequestMethod.GET)
    public String requestHello(){
        return "Hello from Request mapping";
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from say Hello:";
    }

    @PostMapping("/post")
    public String sayPost(){
        return "Hello from post:";
    }

    @GetMapping("/add")
    public double add(@RequestParam("a") double a,@RequestParam("b") double b){
        return a+b;
    }

}
