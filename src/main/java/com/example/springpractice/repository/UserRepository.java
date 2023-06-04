package com.example.springpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springpractice.entity.UserInfo;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
}