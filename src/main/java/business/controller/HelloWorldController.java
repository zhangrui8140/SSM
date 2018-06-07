package business.controller;

import business.bean.HelloWorldBean;
import business.repository.HelloWorldRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping({"/home"})
public class HelloWorldController {
    @RequestMapping("/index/{id}")
    public HelloWorldBean index(@RequestParam(value = "id",defaultValue = "1") int id,@PathVariable("id") int j){
        return new HelloWorldRepository().getHelloWorld();
    }
}
