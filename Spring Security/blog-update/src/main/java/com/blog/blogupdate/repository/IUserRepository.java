package com.blog.blogupdate.repository;

import com.blog.blogupdate.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUserName(String username);
}
