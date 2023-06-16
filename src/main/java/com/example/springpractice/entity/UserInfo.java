package com.example.springpractice.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名前
     */
    @Column(name = "name")
    private String name;

    /**
     * パスワード
     */
    @Column(name = "password")
    private String password;

    /**
     * 誕生日
     */
    @Column(name = "birthday")
    private Date birthday;

}
