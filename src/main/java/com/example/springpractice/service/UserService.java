package com.example.springpractice.service;

import java.util.List;

import com.example.springpractice.entity.User;
import com.example.springpractice.repository.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springpractice.dto.UserRequest;
import com.example.springpractice.entity.UserInfo;
import com.example.springpractice.repository.UserRepository;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserListRepository userListRepository;

    /**
     * ユーザー情報 主キー検索
     * @return 検索結果
     */
    public UserInfo findById(Long id) {
        return userRepository.findById(id).get();
    }

    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public List<UserInfo> searchAll() {
        return userRepository.findAll();
    }

    /**
     * ユーザー情報新規登録
     * @param userRequest ユーザー情報
     */
    public void create(UserRequest userRequest) {
        userRepository.save(CreateUser(userRequest));
    }

    /**
     * ユーザーTBLエンティティの生成
     * @param userRequest ユーザー情報リクエストデータ
     * @return ユーザーTBLエンティティ
     */
    private UserInfo CreateUser(UserRequest userRequest) {

        UserInfo user = new UserInfo();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    /**
     * ユーザー情報更新
     * @param userInfo ユーザーEntity
     */
    public void update(UserInfo userInfo) {
        userRepository.saveAndFlush(userInfo);
    }

    /**
     * ユーザー情報削除
     * @param id ユーザーID
     */
    public void delete(Long id) {
        UserInfo userInfo = findById(id);
        userRepository.delete(userInfo);
    }
    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public List<User> searchUserList() {
        return userListRepository.findAll();
    }

}