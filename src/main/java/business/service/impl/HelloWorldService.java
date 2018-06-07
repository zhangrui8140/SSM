package business.service.impl;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import business.service.IHelloWorldService;
import business.bean.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import business.repository.HelloWorldRepository;

@Api(value="HelloWorld")
@RequestMapping({"/HelloWorld"})
@Service
public class HelloWorldService implements IHelloWorldService {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    @ResponseBody
    @RequestMapping("/getHelloWorldBean/{id}")
    @ApiOperation(value="根据ID获取用户信息",httpMethod="GET",notes="get user by id",response=HelloWorldBean.class)
    @Override
    public HelloWorldBean getHelloWorldBean(@ApiParam(required=true,name="id") @RequestParam(value = "id",defaultValue = "1") String id) {
        return helloWorldRepository.getHelloWorld();
    }
}
