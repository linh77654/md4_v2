package com.blog.blogupdate.repository;


import com.blog.blogupdate.model.AppUser;
import com.blog.blogupdate.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByAppUser(AppUser appUser);
}
