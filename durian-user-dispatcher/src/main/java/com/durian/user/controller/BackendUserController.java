package com.durian.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.durian.user.domain.annotation.NoLoginAuth;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.domain.to.LoginUser;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserInfoDetail;
import com.durian.user.service.BackendUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/backendUser")
public class BackendUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendUserController.class);

	
	@Autowired
	private BackendUserService backendUserService ;

    /**
     * 获取用户列表
     *
     * @return
     * @throws Exception
     */
	@NoLoginAuth
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Callable<UserAllInfo> login(@ModelAttribute LoginUser loginUser) throws Exception {
        LOGGER.info("用户登录:"+ JSONObject.toJSONString(loginUser));
        return () -> backendUserService.login(loginUser);
    }

    
    

    /**
     * 新增用户
     * @param registerUser
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@ModelAttribute RegisterUser registerUser,HttpServletRequest request, HttpServletResponse response) throws Exception {
       /* HttpSession session = request.getSession();
        String imageCode = (String)session.getAttribute(ImageUtil.WEB_IMAGE_CODE);
        //判断图形验证码,
        if(!registerUser.getImgCode().equalsIgnoreCase(imageCode)){
            throw new CustomException(UserExceptionEnum.USER_IMAGE_CODE);
        }*/
    	backendUserService.register(registerUser);
    }

    /**
     * 用户信息详情
     * @author daixibiao
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @NoLoginAuth
    public UserInfoDetail detail(String userId) throws Exception{
        return backendUserService.getUserInfoDetailById(userId);
    }
}
