package com.durian.user.dao;

import com.durian.user.domain.po.BackendUser;
import com.durian.user.domain.po.BackendUserLogin;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;

import java.util.List;

/**
 * 后台用户操作
 * @author hufeng
 *
 */
public interface BackendUserDao {

	UserAllInfo saveUser(RegisterUser registerUser);

	UserAllInfo getUserInfoByName(String name);

	int saveLogin(BackendUserLogin backendUserLogin);


	List<BackendUser> getUserList();

}
