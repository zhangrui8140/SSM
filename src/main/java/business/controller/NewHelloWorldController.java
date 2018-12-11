package business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/hello"},consumes ="application/*",produces = "application/json")
public class NewHelloWorldController {
    @RequestMapping(value = "/index1/{trcode}", method = RequestMethod.POST,produces = "application/xml")
    @ResponseBody
    public List index(@PathVariable String trcode,@RequestBody String requestJson,HttpServletRequest request){
        System.out.print(requestJson);
        Map<String,Object> test=new HashMap<>();
        test.put("user","1");
        List<Object> a=new ArrayList<>();
        a.add(test);
        return a;
    }

    @RequestMapping(value = "/index2/{trcode}", method = RequestMethod.POST,produces = "application/xml")
    public String index(@PathVariable String trcode,@RequestBody Map<String,Object> requestMap,HttpServletRequest request){
        System.out.print(requestMap);
        return "HelloWorld";
    }

    @RequestMapping(value = "/index3/{trcode}", method = RequestMethod.POST)
    public String index(@PathVariable String trcode,@RequestBody String requestXml){
        System.out.print(requestXml);
        return "HelloWorld";
    }
}
