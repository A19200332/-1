package com.example.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Map<String,String> map=new HashMap<>();
        if(username==null||username.length()==0){
            map.put("error_massage","用户名不能为空");
            return map;
        }
        if(username.length()>100){
            map.put("error_massage","用户名长度不能超过100");
            return map;
        }
        if(userMapper.exists(queryWrapper)) {
            map.put("error_massage", "用户已存在");
            return map;
        }
        if(password==null||password.length()==0){
            map.put("error_massage","密码不能为空");
            return map;
        }
        if(confirmedPassword==null||confirmedPassword.length()==0){
            map.put("error_massage","重复密码不能为空");
            return map;
        }
        if(!password.equals(confirmedPassword)){
            map.put("error_massage","两次输入的密码不同");
            return map;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword=passwordEncoder.encode(password);
        User user =new User(null,username,newPassword,password,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202009%2F23%2F20200923185609_rQUdj.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663815292&t=1cce21e0e8cfcf1a248611ce40c84bdb");
        userMapper.insert(user);
        map.put("error_massage","success");
        return map;
    }
}
