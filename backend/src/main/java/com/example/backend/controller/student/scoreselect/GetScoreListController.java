package com.example.backend.controller.student.scoreselect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.mapper.StudentScoreMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.pojo.StudentScore;
import com.example.backend.service.student.scoreselect.GetScoreListService;
import com.example.backend.service.student.studentselect.GetStudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GetScoreListController {
    @Autowired
    private StudentScoreMapper studentScoreMapper;
    @Autowired
    private StudentClassMapper studentClassMapper;
    @Autowired
    private GetScoreListService getScoreListService;
    @PostMapping("/student/score/getlist/")
    public List<StudentScore> getList(@RequestParam Map<String,String> data){
        String id = data.get("stu_id");
        String studentName = data.get("stu_name");
        String className = data.get("class_name");
        String major = data.get("major");
        System.out.println(id+" "+studentName+" "+className+" "+major);
        QueryWrapper<StudentClass>queryWrapper = new QueryWrapper<>();
        if(id!=null&&!id.equals("")){
            queryWrapper.eq("id",Integer.parseInt(id));
        }
        if(studentName!=null&&!studentName.equals("")){
            queryWrapper.eq("student_name",studentName);
        }
        if(className!=null&&!className.equals("")){
            queryWrapper.eq("class_name",className);
        }
        if(major!=null&&!major.equals("")){
            queryWrapper.eq("major",major);
        }
        List<StudentClass> sclist = studentClassMapper.selectList(queryWrapper);
        List<StudentScore> sslist = new ArrayList<>();
        for(StudentClass s:sclist){
              QueryWrapper<StudentScore>queryWrapper1 = new QueryWrapper<>();
              queryWrapper1.eq("student_class_id",s.getId());
              sslist.addAll(getScoreListService.getList(queryWrapper1));
        }
        //System.out.println(sslist);
        return sslist;
    }

}
