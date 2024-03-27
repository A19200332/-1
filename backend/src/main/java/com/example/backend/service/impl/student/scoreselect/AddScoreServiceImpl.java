package com.example.backend.service.impl.student.scoreselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.mapper.StudentScoreMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.pojo.StudentScore;
import com.example.backend.service.student.scoreselect.AddScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AddScoreServiceImpl implements AddScoreService {
    @Autowired
    private StudentScoreMapper studentScoreMapper;
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        Integer studentClassId = Integer.parseInt(data.get("student_class_id"));
        String course = data.get("course");
        Integer score = Integer.parseInt(data.get("score"));
        QueryWrapper<StudentClass>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",studentClassId);
        Map<String,String>map = new HashMap<>();
        if(!studentClassMapper.exists(queryWrapper)){
            map.put("error_massage","学生不存在");
            return map;
        }
        QueryWrapper<StudentScore>queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course",course);
        if(studentScoreMapper.exists(queryWrapper1)){
            map.put("error_massage","该学生已添加该课程成绩");
            return map;
        }
        if(score<0||score>100){
            map.put("error_massage","成绩应在0到100之间");
            return map;
        }
        StudentScore studentScore = new StudentScore(null,studentClassId,course,score);
        studentScoreMapper.insert(studentScore);
        map.put("error_massage","success");
        return map;
    }
}
