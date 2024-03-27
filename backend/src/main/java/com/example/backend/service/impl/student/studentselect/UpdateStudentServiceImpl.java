package com.example.backend.service.impl.student.studentselect;

import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.service.student.studentselect.UpdateStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateStudentServiceImpl implements UpdateStudentService {
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Override
    public Map<String, String> update(Map<String, String> data) {
        String id = data.get("id");
        String studentName = data.get("student_name");
        String className = data.get("class_name");
        String major = data.get("major");
        System.out.println(id+" "+studentName+" "+className+" "+major);
        Map<String,String> map= new HashMap<>();
        StudentClass studentClass = studentClassMapper.selectById(Integer.parseInt(id));
        if(studentName==null||studentName.length()==0){
            map.put("error_massage","姓名不能为空");
            return map;
        }
        if(studentName.length()>100){
            map.put("error_massage","姓名长度不能大于100");
            return map;
        }
        StudentClass studentClass1 = new StudentClass(Integer.parseInt(id),studentName,className,major);
        studentClassMapper.updateById(studentClass1);
        map.put("error_massage","success");
        return map;
    }

}
