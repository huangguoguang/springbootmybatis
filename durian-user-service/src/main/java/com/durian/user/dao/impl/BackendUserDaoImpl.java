package com.durian.user.dao.impl;

import java.util.Date;

import com.durian.user.mapper.BackendUserLoginMapper;
import com.durian.user.utils.date.IdentificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durian.user.dao.BackendUserDao;
import com.durian.user.domain.enums.UserStatusEnum;
import com.durian.user.domain.po.BackendUser;
import com.durian.user.domain.po.BackendUserLogin;
import com.durian.user.domain.to.RegisterUser;
import com.durian.user.domain.to.UserAllInfo;
import com.durian.user.mapper.BackendUserMapper;
import com.durian.user.utils.encrypt.MD5Utils;


/**
 * 
 * @author hufeng
 *
 */
@Service("backendUserDao")
public class BackendUserDaoImpl implements BackendUserDao{
	
	@Autowired
	private BackendUserMapper backendUserMapper;
	
	@Autowired
	private BackendUserLoginMapper backendUserLoginMapper ;

	@Override
	public UserAllInfo saveUser(RegisterUser registerUser) {
		BackendUser backendUser = new BackendUser();
		backendUser.setName(registerUser.getName());
		backendUser.setPassword(MD5Utils.sign(registerUser.getPassword(), MD5Utils.PWD_KEY, MD5Utils.DEFAULT_UTF_8_INPUT_CHARSET));
		backendUser.setCreateTime(new Date().getTime());
		backendUser.setId(IdentificationUtil.generateTimestamp16());
		backendUser.setStatus(UserStatusEnum.Enable.getCode());
		backendUserMapper.insert(backendUser);
		//UserAllInfo userAllInfo = new UserAllInfo();
		return null;
	}

	@Override
	public UserAllInfo getUserInfoByName(String name) {
		return backendUserMapper.selectByName(name);
	}

	@Override
	public int saveLogin(BackendUserLogin backendUserLogin) {
		return backendUserLoginMapper.insert(backendUserLogin);
	}

	
}
