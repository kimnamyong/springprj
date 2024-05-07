package com.bbs1.repository;

import com.bbs1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

 User findByLoginName(String loginName);

}
