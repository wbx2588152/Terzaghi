package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.Power;
import com.jk.model.User;
import com.jk.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public List<Power> getAllNav(HttpServletRequest request) {
        // TODO Auto-generated method stub
/*        HttpSession session = request.getSession();
        Users attribute = (Users)session.getAttribute(session.getId());*/
        String userId = "1";
        List<Power> powerlist0=new ArrayList<Power>();
        Power power=new Power();
        List<Power> powerlist=userMapper.getpowerlist(userId);
        List<Power> powerlist2=powerlist;
        for(int i=0;i<powerlist.size();i++){
            List<Power> powsarr=new ArrayList<Power>();
            if(powerlist.get(i).getPid().equals("0")){
                for(int j=0;j<powerlist2.size();j++){
                    String x=powerlist.get(i).getId();
                    String y=powerlist2.get(j).getPid();
                    if(x.equals(y)){
                        powsarr.add(powerlist2.get(j));
                    }
                }
                powerlist.get(i).setChildren(powsarr);
                powerlist0.add(powerlist.get(i));
            }
        }
        return powerlist0;
    }


}
