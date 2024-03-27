package com.example.backend.service.impl.student.scoreselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentScoreMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.pojo.StudentScore;
import com.example.backend.service.student.scoreselect.GetScoreListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetScoreListServiceImpl implements GetScoreListService {
    @Autowired
    private StudentScoreMapper studentScoreMapper;
    @Override
    public List<StudentScore> getList(QueryWrapper<StudentScore> queryWrapper) {
        return studentScoreMapper.selectList(queryWrapper);
    }
}
