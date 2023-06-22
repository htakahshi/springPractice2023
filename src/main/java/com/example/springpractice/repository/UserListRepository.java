package com.example.springpractice.repository;

import com.example.springpractice.entity.User;
import com.example.springpractice.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserListRepository extends JpaRepository<User, Long> {
}