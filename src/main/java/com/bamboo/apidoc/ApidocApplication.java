package com.bamboo.apidoc;


import com.bamboo.apidoc.code.model.Method;
import com.bamboo.apidoc.code.model.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/test")
public class ApidocApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApidocApplication.class, args);
    }


    @PostMapping("deleteMenu")
    public Method deleteMenu(@RequestParam Integer id, @RequestParam String nihh, @RequestBody Module module) {
        return new Method();
    }
}

