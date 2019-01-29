package com.test.controller;

import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("success")
    public String success() {
        return "success";
    }

}
