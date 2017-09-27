package com.durian.user.controller;

import com.durian.user.capital.service.UserCapitalService;
import com.durian.user.domain.annotation.NoLoginAuth;
import com.durian.user.domain.po.UserLogin;
import com.durian.user.domain.to.FindPwd;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.service.UserService;
import com.durian.user.utils.code.RandomValidateCode;
import com.durian.user.utils.image.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
/*
    @Autowired
    private TfsClientService tfsClientService;*/

    @Autowired
    private UserCapitalService userCapitalService;



    /**
     * 获取web验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @NoLoginAuth
    @ResponseBody
    @RequestMapping(value="/verify/image", method = RequestMethod.GET)
    public void validateAuthCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String s1 = request.getHeader("user-agent");
    	System.out.println(s1);
        HttpSession session = request.getSession();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String imageCode = RandomValidateCode.getRandomString(4);
        session.setAttribute(ImageUtil.WEB_IMAGE_CODE, imageCode);
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = ImageUtil.generateImageCode(imageCode, 90, 30, 4, true, Color.white, null,null);
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }


    /**
     * 找回密码
     * @param findPwd
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @NoLoginAuth
    @RequestMapping(value = "/password/forget", method = RequestMethod.POST)
    public Callable<String> findPwd(@ModelAttribute FindPwd findPwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*HttpSession session = request.getSession();
        String imageCode = (String)session.getAttribute(ImageUtil.WEB_IMAGE_CODE);
        LOGGER.info("服务器图片验证码:"+imageCode +" 客户提交的验证码:"+registerUser.getImgCode());
        //判断图形验证码,
        if(!registerUser.getImgCode().equalsIgnoreCase(imageCode)){
            throw new CustomException(UserExceptionEnum.USER_IMAGE_CODE);
        }*/
        return () -> userService.findPwd(findPwd);
    }


    /**
     * 找回
     * @param findPwd
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @NoLoginAuth
    @RequestMapping(value = "/password/forget/code", method = RequestMethod.POST)
    public Callable<String> findPwdMobileCode(@ModelAttribute FindPwd findPwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*HttpSession session = request.getSession();
        String imageCode = (String)session.getAttribute(ImageUtil.WEB_IMAGE_CODE);
        LOGGER.info("服务器图片验证码:"+imageCode +" 客户提交的验证码:"+registerUser.getImgCode());
        //判断图形验证码,
        if(!registerUser.getImgCode().equalsIgnoreCase(imageCode)){
            throw new CustomException(UserExceptionEnum.USER_IMAGE_CODE);
        }*/
        return () -> userService.findPwdMobileCode(findPwd);
    }

    @NoLoginAuth
    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public void resetPwd(@ModelAttribute FindPwd findPwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userService.resetPwd(findPwd);
    }

    /**
     * 启用用户
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/enableUser", method = RequestMethod.POST)
    public Callable<Integer> enableUser(@ModelAttribute String userId) throws Exception {
        return () -> userService.enableUser(userId);
    }

    /**
     * 禁用用户
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/disableUser", method = RequestMethod.POST)
    public Callable<Integer> disableUser(@ModelAttribute String userId) throws Exception {
        return () -> userService.disableUser(userId);
    }

    /**
     * 注册用户列表
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "info/list", method = RequestMethod.GET)
    public Callable<List<UserAllInfo>> userInfoList(String userId) throws Exception {
        LOGGER.info("注册用户列表: " + userId);
        return () -> userService.selectUserInfoList(userId);
    }
    /**
     * 用户个人资料
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Callable<UserAllInfo> userInfo(String userId) throws Exception {
        LOGGER.info("用户个人资料:"+ userId);
        return () -> userService.userInfo(userId);
    }

    /**
     * 设置修改个人资料
     * @param userId
     * @param userAllInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "info", method = RequestMethod.PUT)
    public Callable<String> updateUserInfo(String userId, @ModelAttribute UserAllInfo userAllInfo,
                                           @RequestParam(value = "nickIcon", required = false) MultipartFile file) throws Exception {
        LOGGER.info("设置修改个人信息：" + userId);
        if (file != null) {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            /*String nickIcon = tfsClientService.saveFile(file.getBytes(), null, suffix);
            if (nickIcon != null) {
                userAllInfo.setNickIcon(nickIcon + suffix);
            } else {
                throw new CustomException(UserExceptionEnum.USER_NICKICON_ERROR);
            }*/
        }
        return () -> userService.updateUserInfo(userId, userAllInfo);
    }

    /**
     * 用户登录历史
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login/history", method = RequestMethod.GET)
    public Callable<List<UserLogin>> userLoginHistory(String userId) throws Exception {
        LOGGER.info("用户登陆历史:"+ userId);
        return () -> userService.userLoginHistory(userId);
    }




    @RequestMapping(value = "test/test", method = RequestMethod.GET)
    public void test(String levelAllot1,String levelAllot2,String levelAllot3) throws Exception {
        LOGGER.info("用户登陆历史:"+ levelAllot1);
        userService.test(levelAllot1,levelAllot2,levelAllot3);
    }

}
