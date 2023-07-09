package com.example.springpractice.service;

import com.example.springpractice.CsvDataSetLoader;
import com.example.springpractice.SpringpracticeApplication;
import com.example.springpractice.entity.UserInfo;
import com.example.springpractice.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

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
    @Mock UserRepository userRepository;

    @Test
    void findByIdTest() {

        MockitoAnnotations.openMocks(this);

        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setName("htakahashi");
        user.setPassword("password");

        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());

        try {
            UserInfo result = userService.findById(1L);
            assertThat("htakahashi", is(result.getName()));
        } catch (Exception e) {
            fail("test失敗");
        }

//        Method method = UserController.class.getDeclaredMethod("searchUserList");
//        method.setAccessible(true);
//        List<User> actual = (List<User>) method.invoke(userService, "a");
//        assertThat("htakahashi", is(actual.get(0).getName()));
    }
}
