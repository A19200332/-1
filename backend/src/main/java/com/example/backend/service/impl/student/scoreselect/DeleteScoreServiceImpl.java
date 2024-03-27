package com.example.backend.service.impl.student.scoreselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentScoreMapper;
import com.example.backend.pojo.StudentScore;
import com.example.backend.service.student.scoreselect.DeleteScoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteScoreServiceImpl implements DeleteScoreService {
    @Autowired
    private StudentScoreMapper studentScoreMapper;
    @Override
    public Map<String, String> delete(Map<String, String> data) {
        Integer id = Integer.parseInt(data.get("id"));
        QueryWrapper<StudentScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Map<String,String>map = new HashMap<>();
        if(!studentScoreMapper.exists(queryWrapper)){
             map.put("error_massage","不存在该记录");
             return map;
        }
        studentScoreMapper.deleteById(id);
        map.put("error_massage","success");
        return map;
    }
}
