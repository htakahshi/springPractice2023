package com.example.springpractice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ユーザー一覧 Entity
 */
@Entity
@Data
@Table(name = "user_list")
public class User implements Serializable {

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

}
