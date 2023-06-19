package com.rama.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    private final MathOperation  mathOperation;
    @Autowired
    public AppController(MathOperation mathOperation){
        this.mathOperation=mathOperation;
    }
    @GetMapping("/getdata")
    public String getApp(@RequestParam("data") String data) {
        return "My data is: " + data;
    }

    @GetMapping("/getdata/{data}")
    public String gatDataVariable(@PathVariable String data) {
        return "Variable data is: " + data;
    }

    @GetMapping("/submitdata")
    public String submitForm(@RequestParam("name") String name, @RequestParam("name") String age, @RequestParam("name") String address) {
        return "Variable data is name: " + name + " age:" + age + " address:" + address;
    }

    @GetMapping("/getversion/{version}")
    public String getversion(@PathVariable String version) {
        String result=null;
        if (version.equals("v1"))
            result= "version  data is: " + version;
        if (version.equals("v2"))
            result= "new version  data is: " + version;
        return result;
    }

    @PostMapping("/submit/userdata")
    public UserData submitUserdata(@RequestBody UserData userData){
        return userData;
    }

    @GetMapping("/addString")
    public String addValue(@RequestParam("str1") String str1,@RequestParam("str2") String str2){
        return mathOperation.addString(str1,str2);
    }

    @GetMapping("/getfib")
    public List<Integer> getFibbonacci(@RequestParam("count") int count){
        return mathOperation.getFibbonaciNumber(count);
    }

}
