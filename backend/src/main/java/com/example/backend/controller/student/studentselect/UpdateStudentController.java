package com.example.backend.controller.student.studentselect;

import com.example.backend.service.student.studentselect.UpdateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateStudentController {
    @Autowired
    private UpdateStudentService updateStudentService;
    @PostMapping("/student/select/update/")
    public Map<String,String> update(@RequestParam Map<String,String>data){
         return updateStudentService.update(data);
    }
}
