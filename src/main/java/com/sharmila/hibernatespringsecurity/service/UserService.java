/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.service;

import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sharmila
 */
@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserDao userDao;
   
    
            
    public UserService() {
//      getUsers();
    }

   
    
    public void insert(User user) {
        userDao.insert(user);

    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getByUserName(String username) {
        return userDao.getByUserName(username);
    }
    public User getByRole(String role){
        return userDao.getByRole(role);
    }
     public List<User> getFetchEager(){
        return userDao.getFetchEager();
    }
//
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = getByUserName(username);
        
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    //converting com.....User to org.springframework.security.core.userdetails.User
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isStatus(), true, true, true, authorities);

    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> role) {
        Set<GrantedAuthority> setAutho = new HashSet<GrantedAuthority>();

        //build's users authority
        for (Role r : role) {
            setAutho.add(new SimpleGrantedAuthority(r.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<>(setAutho);
        return result;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
//    public void getUsers(){
//        getAll();
//        for(User user:userDao.getAll()){
////            loadUserByUsername(user.getUserName());
//            System.out.println(user.toString());
//        }
       
        
    

 

}
