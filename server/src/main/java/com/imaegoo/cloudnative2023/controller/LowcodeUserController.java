package com.imaegoo.cloudnative2023.controller;

import com.imaegoo.cloudnative2023.DTO.ResultDto;
import com.imaegoo.cloudnative2023.entity.LowcodeUser;
import com.imaegoo.cloudnative2023.service.LowcodeUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 项目名称：springboot
 * 类 名 称：LowcodeUserController
 * 类 描 述：TODO
 * 创建时间：2023/7/28 上午10:30
 * 创 建 人：wteng
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class LowcodeUserController {

    @Autowired
    private LowcodeUserService lowcodeUserService;

    /**
     * 员工登陆
     *
     * @param lowcodeUser 传入的是json 故需要将其转化为实体类，json中的类名与实体类名对应才可以封装
     *                    A. 由于需求分析时, 我们看到前端发起的请求为post请求, 所以服务端需要使用注解 @PostMapping
     *                    B. 由于前端传递的请求参数为json格式的数据, 这里使用LowcodeUser对象接收, 但是将json格式数据封装到实体类中, 在形参前需要加注解@RequestBody
     */
    @PostMapping("/login")
    public ResultDto<LowcodeUser> login(HttpServletRequest request, @RequestBody LowcodeUser lowcodeUser) {

        //  ①. 将页面提交的密码password进行md5加密处理, 得到加密后的字符串
        String password = lowcodeUser.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //  ②. 根据页面提交的用户名username查询数据库中员工数据信息
        LowcodeUser queryuser = lowcodeUserService.queryByUsername(lowcodeUser.getUsername());
        //  ③. 如果没有查询到, 则返回登录失败结果
        if (queryuser == null) {
            return ResultDto.error("用户名不存在");
        }
        //  ④. 密码比对，如果不一致, 则返回登录失败结果
        if (!queryuser.getPassword().equals(password)) {
            return ResultDto.error("密码错误");
        }
        //  ⑤. 查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (queryuser.getIsDeleted() == 1) {
            return ResultDto.error("账户已禁用");
        }
        //  ⑥. 登录成功，将员工id存入Session, 并返回登录成功结果
        request.getSession().setAttribute("LowcodeUser", queryuser.getId());
        return ResultDto.success(queryuser);
    }

    /**
     * 需要在Controller中创建对应的处理方法, 接收页面发送的POST请求 /LowcodeUserService/logout
     *
     * @version v1.0
     * @author LiBiGo
     * @date 2022/8/12 12:09
     */
    @PostMapping("/logout")
    public ResultDto<String> logout(HttpServletRequest request) {
        //  清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("LowcodeUserService");
        //  返回结果
        return ResultDto.success("退出成功");
    }

    /**
     * 员工注册
     *
     * @param lowcodeUser 传入的是json 故需要将其转化为实体类，json中的类名与实体类名对应才可以封装
     *                    A. 由于需求分析时, 我们看到前端发起的请求为post请求, 所以服务端需要使用注解 @PostMapping
     *                    B. 由于前端传递的请求参数为json格式的数据, 这里使用LowcodeUser对象接收, 但是将json格式数据封装到实体类中, 在形参前需要加注解@RequestBody
     */
    @PostMapping("/create")
    public ResultDto<LowcodeUser> create(HttpServletRequest request, @RequestBody LowcodeUser lowcodeUser) {
        String username = lowcodeUser.getUsername();
        LowcodeUser check = lowcodeUserService.queryByUsername(username);
        if (!ObjectUtils.isEmpty(check)) {
            return ResultDto.error("该用户已注册过");
        }
        //  ①. 将页面提交的密码password进行md5加密处理, 得到加密后的字符串
        String password = lowcodeUser.getPassword();
        lowcodeUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        //  ②. 根据页面提交的用户名username查询数据库中员工数据信息
        int result = lowcodeUserService.insert(lowcodeUser);
        LowcodeUser queryuser = lowcodeUserService.queryByUsername(username);
        //   注册成功，将员工id存入Session, 并返回登录成功结果
        request.getSession().setAttribute("LowcodeUser", queryuser.getId());
        return ResultDto.success(queryuser);
    }

    /**
     * 查询当前登录用户
     */
    @GetMapping("/me")
    public ResultDto<Integer> create(HttpServletRequest request) {
        return ResultDto.success((Integer) request.getSession().getAttribute("LowcodeUser"));
    }
}
