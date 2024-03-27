package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.pojo.StudentScore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentScoreMapper extends BaseMapper<StudentScore> {

}
