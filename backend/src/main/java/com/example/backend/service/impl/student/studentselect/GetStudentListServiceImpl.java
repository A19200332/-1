package com.example.backend.service.impl.student.studentselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.service.student.studentselect.GetStudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetStudentListServiceImpl implements GetStudentListService {
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Override
    public List<StudentClass> getList(QueryWrapper<StudentClass>queryWrapper) {
        List<StudentClass> list =new ArrayList<>();
        return studentClassMapper.selectList(queryWrapper);
    }
}
