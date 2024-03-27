package com.example.backend.controller.student.studentselect;

import com.example.backend.service.student.studentselect.DeleteStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DeleteStudentController {
    @Autowired
    private DeleteStudentService deleteStudentService;
    @PostMapping("/student/select/delete/")
    public Map<String,String> delete(@RequestParam Map<String,String>data){
        String id=data.get("id");
        System.out.println(id);
        return deleteStudentService.delete(data);
    }
}
