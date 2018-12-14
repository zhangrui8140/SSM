package business.controller;

import core.exception.SpliterNotFoundEx;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/hello"},consumes ="application/*",produces = "application/json")
@SessionAttributes(value ={"user"})
public class NewHelloWorldController {


    @ModelAttribute("user")
    public UserModel beforeLogin(HttpServletRequest request, Model model, ModelMap modelMap){
        UserModel user=new UserModel();
        user.setName("ZR");
        user.setAge(23);
        return user;
    }

    @RequestMapping(value = "/index6", method = RequestMethod.GET)
    @ResponseBody
    public List login(HttpServletRequest request, Model model, ModelMap modelMap){
        throw new SpliterNotFoundEx();
    }



    @RequestMapping(value = "/index5/{trcode}", method = RequestMethod.POST)
    @ResponseBody
    public List login(@PathVariable String trcode, @ModelAttribute("user") UserModel userModel, HttpServletRequest request, Model model, ModelMap modelMap){
        System.out.print(userModel);
            System.out.print(request.getSession());
        List user=new ArrayList();
        user.add("test");
        return user;
    }




    @RequestMapping(value = "/index4/{trcode}", method = RequestMethod.POST)
    @ResponseBody
    public List index(@PathVariable String trcode, @RequestBody String requestJson, HttpServletRequest request, Model model, ModelMap modelMap){
        System.out.print(requestJson);
        System.out.print(request.getSession());
        List user=new ArrayList();
        user.add("test2");
        return user;
    }





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

class UserModel{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
