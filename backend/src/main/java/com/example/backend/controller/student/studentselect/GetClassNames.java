package com.example.backend.controller.student.studentselect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetClassNames {
    private String[]classes = {"物联2001","物联2002","物联2003","软件2001","软件2002",
            "软件2003","软件2004","微机2001","微机2002","微机2003","微机2004","微机2006","微机2005"};
    @GetMapping ("/student/select/getclassnames/")
    public List<String> get(){
        List<String>st=new ArrayList<>();
        for(String s:classes){
            st.add(s);
        }
        return st;
    }

}
