package com.example.backend.controller.student.studentselect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetMajorNames {
    String[]majors= {"软件工程","物联网工程","人工智能","计算机科学与技术"};
    @GetMapping("/student/select/getmajornames/")
    public List<String> get(){
        List<String>st=new ArrayList<>();
        for(String s:majors){
            st.add(s);
        }
        return st;
    }
}
