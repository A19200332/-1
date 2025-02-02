package com.example.backend.controller.user.account;

import com.example.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;
    @GetMapping("/user/account/getinfo/")
    public Map<String,String> getInfo(){
        return infoService.getInfo();
    }
}
