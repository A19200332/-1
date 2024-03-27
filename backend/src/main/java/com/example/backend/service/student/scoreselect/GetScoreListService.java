package com.example.backend.service.student.scoreselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.pojo.StudentScore;

import java.util.List;

public interface GetScoreListService {
    public List<StudentScore> getList(QueryWrapper<StudentScore> queryWrapper);
}
