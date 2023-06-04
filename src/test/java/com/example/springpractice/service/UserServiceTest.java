package com.example.springpractice.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.springpractice.CsvDataSetLoader;
import com.example.springpractice.SpringpracticeApplication;
import com.example.springpractice.entity.UserInfo;
import com.example.springpractice.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@SpringBootTest(classes = {SpringpracticeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;

    @Test
    @DatabaseSetup(value = "/testData/")
    @Transactional
    void searchAllTest() throws Exception {

        List<UserInfo> userlist = userRepository.findAll();
        assertThat(userlist.size(), is(3));
    }
}
