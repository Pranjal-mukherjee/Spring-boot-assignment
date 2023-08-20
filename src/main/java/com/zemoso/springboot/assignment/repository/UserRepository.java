package com.zemoso.springboot.assignment.repository;

import com.zemoso.springboot.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBookById(Long bookId);
}
