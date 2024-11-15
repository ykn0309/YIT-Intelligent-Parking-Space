package com.example.yit.Controller;

//package com.flower.server.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserController {
//}

import com.example.yit.Bean.RespBean;
import com.example.yit.Service.UserService;
import com.example.yit.Bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.yit.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@RestController  //表明这是一个控制器类
@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/index")   //处理 /article 路径下的请求
public class UserController {
    @Autowired  //将 ArticleService 服务注入到控制器中，用于处理文章相关的逻辑。
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)  //将处理POST请求的URL映射到 addNewArticle 方法上。
    public @ResponseBody RespBean getUserById(@RequestBody User u) {
//        String id=user.id;
        String password=u.password();
        String username= u.userName();
        UserEntity a=userService.getUserById(username);
        if(a==null){return new RespBean("failure", "0",null);}
        List<String> value = new ArrayList<>();
        value.add(Long.toString(a.getUserId()));
        value.add(a.getUserName());


        if(password.equals((a.getPassword()))){
            return new RespBean("success", "1",value);
        }else{
            return new RespBean("failure", "0",value);
        }
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)  //将处理POST请求的URL映射到 addNewArticle 方法上。
    public @ResponseBody RespBean addNewUser(@RequestBody User u) {////这段代码定义了一个将文章添加到系统中的方法 addNewArticle。
        UserEntity userEntity = new UserEntity();
        String username= u.userName();
        String password= u.password();
        userEntity.setUserName(username);
        userEntity.setPassword(password);
        System.out.println("11111111");
        int result = userService.addNewUser(userEntity);
        if(result==1){
            return new RespBean("success", "1",null);
        }else{
            return new RespBean("failure", "0",null);
        }
    }

    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public @ResponseBody RespBean userInfo(int userId)
    {
        User result=userService.userInfo(userId);
        return new RespBean("success","",result);
    }

}