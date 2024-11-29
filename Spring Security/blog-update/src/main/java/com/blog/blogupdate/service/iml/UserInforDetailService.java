package com.blog.blogupdate.service.iml;

import com.blog.blogupdate.dto.UserInfoUserDetails;
import com.blog.blogupdate.model.AppUser;
import com.blog.blogupdate.model.UserRole;
import com.blog.blogupdate.repository.IUserRepository;
import com.blog.blogupdate.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserInforDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iUserRepository.findByUserName(username);
//        Lấy tất cả role của AppUser
        List<UserRole> userRoles = iUserRoleRepository.findAllByAppUser(appUser);
        UserInfoUserDetails infoUserDetails = new UserInfoUserDetails(appUser, userRoles);
        return infoUserDetails;
    }
}
