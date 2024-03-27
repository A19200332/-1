package com.example.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.StudentClassMapper;
import com.example.backend.mapper.StudentScoreMapper;
import com.example.backend.pojo.StudentClass;
import com.example.backend.pojo.StudentScore;
import com.example.backend.service.user.account.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {
	@Autowired
	private LoginService loginService;
	@Autowired
	private StudentClassMapper studentClassMapper;
	@Autowired
	private StudentScoreMapper studentScoreMapper;
	@Test
	void contextLoads() {
		String[]majors= {"软件工程","物联网工程","人工智能","计算机科学与技术"};
		String[]classes = {"物联2001","物联2002","物联2003","软件2001","软件2002",
		 "软件2003","软件2004","微机2001","微机2002","微机2003","微机2004","微机2006","微机2005"};
		Map<String,String> map= new HashMap<>();
		map.put("物联2001","物联网工程");
		map.put("物联2002","物联网工程");
		map.put("物联2003","物联网工程");

		map.put("软件2001","软件工程");
		map.put("软件2002","软件工程");
		map.put("软件2003","软件工程");
		map.put("软件2004","软件工程");

		map.put("微机2001","计算机科学与技术");
		map.put("微机2002","计算机科学与技术");
		map.put("微机2003","计算机科学与技术");
		map.put("微机2004","计算机科学与技术");
		map.put("微机2005","计算机科学与技术");
		map.put("微机2006","计算机科学与技术");
		for(int i=0;i<200;i++)
		{
			int r1,r2;
			r1 = (int)(Math.random()*4)%4;
			r2= (int)(Math.random()*13)%13;
			String name="";
			for(int j=0;j<4;j++){
				int r3 = (int)(Math.random()*26);
				name+=(char)('a'+r3);
			}
			if(!map.get(classes[r2]).equals(majors[r1]))continue;
			StudentClass studentClass = new StudentClass(null,name,classes[r2],majors[r1]);
			studentClassMapper.insert(studentClass);
		}
	}
	@Test
	void listMerge(){
		List<Integer> list = new ArrayList<>();
		List<Integer> list1 = new ArrayList<>();
		list.add(1);
		list.add(2);
		list1.add(3);
		list1.add(4);
		list.addAll(list1);
		for(Integer s:list){
			System.out.println(s);
		}
	}
	@Test
	void addScore(){
		String []courses = {"C语言基础","数据结构","计算机组成原理","操作系统","计算机网络","高等数学","离散数学","数字逻辑电路","专业英语","计算方法","python","java","汇编","软件工程","单片机","模拟电路","电路基础","线性代数","概率论与数理统计"};
		System.out.println(courses.length);
		QueryWrapper<StudentClass>queryWrapper = new QueryWrapper<>();
		List<StudentClass> sclist = studentClassMapper.selectList(queryWrapper);
		for(StudentClass s:sclist){
             for(int i=0;i<6;i++){
				 QueryWrapper<StudentScore>queryWrapper2 = new QueryWrapper<>();
				 queryWrapper2.eq("course",courses[i]).eq("student_class_id",s.getId());
				 if(studentScoreMapper.exists(queryWrapper2))continue;
				 StudentScore studentScore = new StudentScore(null,s.getId(),courses[i],(int)(Math.random()*101)%101);
				 studentScoreMapper.insert(studentScore);
			 }
			for(int i=6;i<19;i++){
				QueryWrapper<StudentScore>queryWrapper1 = new QueryWrapper<>();

					queryWrapper1.eq("course",courses[i]).eq("student_class_id",s.getId());
					if(studentScoreMapper.exists(queryWrapper1))continue;
					StudentScore studentScore = new StudentScore(null,s.getId(),courses[i],(int)(Math.random()*101)%101);
					studentScoreMapper.insert(studentScore);

			}
		}
	}

}
