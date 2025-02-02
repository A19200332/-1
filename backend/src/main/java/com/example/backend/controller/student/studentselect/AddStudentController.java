package com.example.backend.controller.student.studentselect;

import com.example.backend.service.student.studentselect.AddStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddStudentController {
    @Autowired
    private AddStudentService addStudentService;
    @PostMapping("/student/select/add/")
    public Map<String,String> add(@RequestParam Map<String,String>data){
        return addStudentService.add(data);
    }
}
