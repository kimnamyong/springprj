package com.blog.repository;

import com.blog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository  // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
 //JPA Naming 쿼리
 // SELECT * FROM user WHERE username=?1 AND password=?2 ;
 User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

 @Query(value="SELECT * FROM user WHERE username=?1",nativeQuery=true)
 Optional<User> findByUsername(@Param("username") String username);



 //@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery=true)
 // User login(String username, String password);

}