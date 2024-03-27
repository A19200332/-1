package com.example.backend.service.impl.student.studentselect;

import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.service.student.studentselect.AddStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddStudentServiceImpl implements AddStudentService {
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        Map<String,String> map= new HashMap<>();
        map.put("物联2001","物联网工程");
        map.put("物联2002","物联网工程");
        map.put("物联2003","物联网工程");

        map.put("软件2001","软件工程");
        map.put("软件2002","软件工程");
        map.put("软件2003","软件工程");
        map.put("软件2004","软件工程");

        map.put("微机2001","计算机科学与技术");
        map.put("微机2002","计算机科学与技术");
        map.put("微机2003","计算机科学与技术");
        map.put("微机2004","计算机科学与技术");
        map.put("微机2005","计算机科学与技术");
        map.put("微机2006","计算机科学与技术");
        String studentName = data.get("student_name");
        String className = data.get("class_name");
        String major = data.get("major");
        Map<String,String> mp=new HashMap<>();
        if(studentName==null||studentName.length()==0){
            mp.put("error_massage","学生姓名不能为空");
            return mp;
        }
        if(className==null||className.length()==0||className.equals("")) {
            mp.put("error_massage","班级不能为空");
            return mp;
        }
        if(major==null||major.length()==0||major.equals("")){
            mp.put("error_massage","专业不能为空");
            return mp;
        }
        if(!map.get(className).equals(major)){
            mp.put("error_massage","专业班级不匹配");
            return mp;
        }
        StudentClass studentClass = new StudentClass(null,studentName,className,major);
        studentClassMapper.insert(studentClass);
        mp.put("error_massage","success");
        return mp;
    }
}
