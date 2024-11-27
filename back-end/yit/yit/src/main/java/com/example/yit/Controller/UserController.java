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
@CrossOrigin
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
        System.out.println("登录查询:");
        System.out.println(username);
        System.out.println(password);
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
        System.out.println("addUser");
        System.out.println("username");
        System.out.println("password");
        int result = userService.addNewUser(userEntity);
        if(result==1){
            return new RespBean("success", "1",null);
        }else{
            return new RespBean("failure", "0",null);
        }
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public @ResponseBody RespBean userInfo(int userId)
    {
        System.out.println("userInfo");
        User result=userService.userInfo(userId);
        return new RespBean("success","",result);
        
    }

    @RequestMapping(value = "/wallet", method = RequestMethod.GET)
    public @ResponseBody RespBean wallet(int userId)
    {
        System.out.println("getWallet");
        float result=userService.wallet(userId);
        return new RespBean("success","",result);
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public @ResponseBody RespBean recharge(int userId,float wallet)
    {
        System.out.println("recharge");
        float result=userService.recharge( userId, wallet);
        return new RespBean("success","",result);
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
    public @ResponseBody RespBean updateUserInfo(int userId, String userName, String carId)
    {
        System.out.println("updateUserInfo");
        Integer result=userService.updateUserInfo(  userId,  userName, carId);
        return new RespBean("success","",result);
    }

    @RequestMapping(value = "/vehicle", method = RequestMethod.GET)
    public @ResponseBody RespBean vehicle(int userId)
    {
        System.out.println("getCarId");
        String result=userService.vehicle(  userId);
        return new RespBean("success","",result);
    }
    

}