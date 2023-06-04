package com.example.springpractice.crud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.springpractice.CsvDataSetLoader;
import com.example.springpractice.SpringpracticeApplication;
import com.example.springpractice.dto.UserRequest;
import com.example.springpractice.entity.UserInfo;
import com.example.springpractice.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@SpringBootTest(classes = {SpringpracticeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class CRUDDaoTest {

    // ユーザ情報を扱うためのEntityクラス
    @Autowired
    private UserService userService;

    /**
     * create処理で新規レコードが作成されるか検証する
     * エンティティによってDBが想定通りに書き換えられたかExpectedDatabaseと比較することで検証
     */
    @Test
    @DatabaseSetup(value = "/crud/create/")
    @ExpectedDatabase(value = "/crud/create/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void createTest01() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("test003");
        userRequest.setPassword("333333");

        userService.create(userRequest);
    }

    /**
     * update処理で変更したレコードが更新されるか検証する
     */
    @Test
    @DatabaseSetup(value = "/crud/update/")
    @ExpectedDatabase(value = "/crud/update/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void updateTest01() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong("1"));
        userInfo.setName("テスト十郎");
        userInfo.setPassword("12341234");

        userService.update(userInfo);
    }

    /**
     * delete処理で選択されたレコードが削除されるか検証する
     */
    @Test
    @DatabaseSetup(value = "/crud/delete/")
    @ExpectedDatabase(value = "/crud/delete/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void deleteTest01() {

        userService.delete(Long.parseLong("1"));
        userService.searchAll();
    }
}

