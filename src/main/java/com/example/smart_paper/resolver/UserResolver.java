package com.example.smart_paper.resolver;

import com.example.smart_paper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserResolver {
    @Autowired
    private UserService userService;

}
