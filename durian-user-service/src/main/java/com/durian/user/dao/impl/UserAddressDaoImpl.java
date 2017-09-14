package com.durian.user.dao.impl;

import com.durian.user.dao.UserAddressDao;
import com.durian.user.domain.po.UserAddress;
import com.durian.user.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huangguang on 2017/7/6.
 */
@Service("userAddressDao")
@Transactional
public class UserAddressDaoImpl implements UserAddressDao {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getUserAddressList(UserAddress userAddress) throws Exception {
        return userAddressMapper.selectByUserId(userAddress);
    }

    @Override
    public int saveUserAddress(UserAddress userAddress) throws Exception {
        return userAddressMapper.insertSelective(userAddress);
    }

    @Override
    public int updateUserAddress(UserAddress userAddress) throws Exception {
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Override
    public UserAddress getUserAddressById(Integer id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }
}
