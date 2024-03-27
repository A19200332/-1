package com.example.backend.service.student.studentselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.pojo.StudentClass;

import java.util.List;

public interface GetStudentListService {
    public List<StudentClass> getList(QueryWrapper<StudentClass> queryWrapper);
}
