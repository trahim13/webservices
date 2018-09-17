package org.trahim.rest.webservices2.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @GetMapping("/hello-word")
    public String hellowWord() {
        return "Hello Word";
    }

    @GetMapping("/hello-word-bean")
    public HelloWordBean helloWordBean() {
        return new HelloWordBean("Hello word bean");
    }

    @GetMapping("/hello-word-path-variable/{name}")
    public HelloWordBean helloWordPath(@PathVariable String name) {
        return new HelloWordBean(String.format("Hello word, %s", name));
    }

}
