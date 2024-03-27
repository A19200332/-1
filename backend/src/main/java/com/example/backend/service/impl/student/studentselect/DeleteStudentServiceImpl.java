package com.example.backend.service.impl.student.studentselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.service.student.studentselect.DeleteStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteStudentServiceImpl implements DeleteStudentService {
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Override
    public Map<String, String> delete(Map<String, String> data) {
        System.out.println("3434");
        String id = data.get("id");
        System.out.println(id);
        Map<String,String>map=new HashMap<>();
        QueryWrapper<StudentClass>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",Integer.parseInt(id));
        studentClassMapper.delete(queryWrapper);
        map.put("error_massage","success");
        System.out.println("1");
        return map;
    }
}
