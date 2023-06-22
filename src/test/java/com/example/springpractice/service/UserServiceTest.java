package com.example.springpractice.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.springpractice.controller.UserController;
import com.example.springpractice.entity.User;
import com.example.springpractice.repository.UserListRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
     * ユーザー情報 Repository Practice Practice
     */
    @InjectMocks
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserListRepository userListRepository;

    @Test
    @DatabaseSetup(value = "/testData/")
    @Transactional
    void searchAllTest() throws Exception {

        List<UserInfo> userlist = userRepository.findAll();
        assertThat(userlist.size(), is(3));
    }

    @Test
    void getUserList() throws Exception {

        User user = new User();
        user.setName("htakahashi");
        List<User> list = new ArrayList<User>();
        list.add(user);

        when(userListRepository.findAll()).thenReturn(list);

        Method method = UserController.class.getDeclaredMethod("searchUserList");
        method.setAccessible(true);
        List<User> actual = (List<User>) method.invoke(userService, "a");
        assertThat("htakahashi", is(actual.get(0).getName()));

    }
}
