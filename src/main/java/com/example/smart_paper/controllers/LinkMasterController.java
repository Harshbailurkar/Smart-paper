package com.example.smart_paper.controllers;

import com.example.smart_paper.models.LinkMaster;
import com.example.smart_paper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class LinkMasterController {

    @Autowired
    private UserService userService;

    @PostMapping("/links")
    public List<LinkMaster> getLinksByRole(){
        return userService.getLinksByRole();
    }

    @PostMapping("/demoLink")
    public String getDemoLink(){
        return "Demo Link";
    }
   /*
   @GetMapping("/parent")
    public List<LinkMaster> getParentLinks() {
        return linkMasterService.getParentLinks();
    }

    @GetMapping("/admin")
    public List<LinkMaster> getAdminLinks() {
        return linkMasterService.getAdminLinks();
    }

    */
}
