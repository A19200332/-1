package com.example.backend.controller.student.studentselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.service.student.studentselect.GetStudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetStudentListController {
    @Autowired
    private GetStudentListService getStudentListService;
    @PostMapping ("/student/select/getlist/")
    public List<StudentClass> getList(@RequestParam Map<String,String> data){
        QueryWrapper<StudentClass>queryWrapper=new QueryWrapper<>();
        String id = data.get("student_id");
        String studentName = data.get("student_name");
        String className = data.get("class_name");
        String major = data.get("major");
        System.out.println(id+" "+studentName+" "+className+" "+major);
        if(id!=null&&!id.equals("")){
            queryWrapper.eq("id",Integer.parseInt(id));
        }
        if(studentName!=null&&!studentName.equals("")){
            queryWrapper.eq("student_name",studentName);
        }
        if(className!=null&&!className.equals("allclassname")&&!className.equals("")){
            queryWrapper.eq("class_name",className);
        }
        if(major!=null&&!major.equals("allmajorname")&&!major.equals("")){
            queryWrapper.eq("major",major);
        }

        return getStudentListService.getList(queryWrapper);
    }

}
