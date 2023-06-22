package com.example.springpractice.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.example.springpractice.entity.User;
import com.example.springpractice.entity.UserInfo;
import com.example.springpractice.repository.UserRepository;
import com.example.springpractice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Autowired
    private MockMvc mvc;

    /**
     * ユーザー情報にアクセスした際に、正しいViewが返されるか検証する
     */
    @Test
    void startTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.get("/")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("top/top"));
    }

    /**
     * ユーザー情報一覧表示処理で正しいViewが返されるか検証する
     */
    @Test
    void displayListTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.get("/user/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/list"));
    }

    /**
     * ユーザー情報一覧表示処理で正しいViewが返されるか検証する
     */
    @Test
    void getUserList() throws Exception {

        User user = new User();
        user.setName("htakahashi");
        List<User> list = new ArrayList<User>();
        list.add(user);

        when(userService.searchUserList()).thenReturn(list);

        Method method = UserController.class.getDeclaredMethod("getUserList", String.class);
        method.setAccessible(true);
        List<User> actual = (List<User>) method.invoke(userController, "a");
        assertThat("htakahashi", is(actual.get(0).getName()));

    }

    /**
     * ユーザー新規登録表示処理で正しいViewが返されるか検証する
     */
    @Test
    void displayAddTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.get("/user/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/add"));
    }

    /**
     * 画面の入力から新規レコードがDBへ登録されるか検証する
     */
    @Test
    @DatabaseSetup(value = "/crud/create/")
    @ExpectedDatabase(value = "/crud/create/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void createTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/user/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "test003")
                        .param("password", "333333"));
    }

    /**
     * 対象のレコードが更新されるか検証する
     */
    @Test
    @DatabaseSetup(value = "/crud/update/")
    @ExpectedDatabase(value = "/crud/update/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void updateTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/user/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("name", "テスト十郎")
                        .param("password", "12341234")
                        .param("birthday", "2020/6/1"));
    }

    /**
     * 対象のレコードが削除されるか検証する
     */
    @Test
    @DatabaseSetup(value = "/crud/delete/")
    @ExpectedDatabase(value = "/crud/delete/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void deleteTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/user/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1"));
    }
}
