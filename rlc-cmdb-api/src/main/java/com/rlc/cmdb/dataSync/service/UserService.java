package com.rlc.cmdb.dataSync.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rlc.cmdb.dataSync.entity.UserEntity;

import java.util.Map;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

}
