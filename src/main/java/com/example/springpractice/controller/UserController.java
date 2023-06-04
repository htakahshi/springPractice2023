package com.example.springpractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springpractice.dto.UserRequest;
import com.example.springpractice.entity.UserInfo;
import com.example.springpractice.service.UserService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {

    /**
     * ユーザー情報 Service bbb
     */
    @Autowired
    UserService userService;

    /**
     * 初期画面
     * @return 初期画面のHTML
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        return "top/top";
    }

    /**
     * ユーザー情報一覧画面
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String displayList(Model model) {
        List<UserInfo> userlist = userService.searchAll();
        model.addAttribute("userlist", userlist);
        return "user/list";
    }

    /**
     * ユーザー情報詳細画面
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/detail", method = RequestMethod.POST)
    public String displayById(@ModelAttribute UserInfo userInfo, Model model) {
        Long id = userInfo.getId();
        UserInfo userDetail = userService.findById(id);
        model.addAttribute("userdetail", userDetail);
        return "user/detail";
    }

    /**
     * ユーザー新規登録画面
     * @param model Model
     * @return ユーザー新規登録画面
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String displayAdd(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "user/add";
    }

    /**
     * ユーザー新規登録処理
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(@ModelAttribute UserRequest userRequest, Model model) {
        // ユーザー情報の登録
        userService.create(userRequest);
        return "redirect:/user/list";
    }

    /**
     * ユーザー更新処理
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update(@ModelAttribute UserInfo userInfo, Model model) {
        // ユーザー情報の更新
        userService.update(userInfo);
        return "user/list";
    }

    /**
     * ユーザー削除処理
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute UserInfo userInfo, Model model) {
        // ユーザー情報の削除
        Long id = userInfo.getId();
        userService.delete(id);
        List<UserInfo> userlist = userService.searchAll();
        model.addAttribute("userlist", userlist);
        return "user/list";
    }
}