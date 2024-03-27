package com.example.backend.service.impl.student.scoreselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.mapper.StudentScoreMapper;
import com.example.backend.pojo.StudentScore;
import com.example.backend.service.student.scoreselect.UpdateScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateScoreServiceImpl implements UpdateScoreService {
    @Autowired
    private StudentScoreMapper studentScoreMapper;
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Override
    public Map<String, String> update(Map<String, String> data) {
        String course = data.get("course");
        String studentClassId = data.get("student_class_id");
        Integer score = Integer.parseInt(data.get("score"));
        QueryWrapper<StudentScore>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course",course).eq("student_class_id",studentClassId);
        Map<String,String>map=new HashMap<>();
        if(!studentScoreMapper.exists(queryWrapper)){
            map.put("error_massage","不存在该条记录");
            return map;
        }
        if(score<0||score>100){
            map.put("error_massage","成绩应该大于0小于100");
            return map;
        }
        StudentScore studentScore = studentScoreMapper.selectOne(queryWrapper);
        studentScore.setScore(score);
        studentScoreMapper.updateById(studentScore);
        map.put("error_massage","success");
        return map;
    }
}
