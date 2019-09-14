package com.mytest.register.service;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 13:04
 */

import com.mytest.register.enums.Status;
import com.mytest.register.mapper.UserMapper;
import com.mytest.register.model.User;
import com.mytest.register.utils.Constants;
import com.mytest.register.utils.EncryptionUtils;
import com.mytest.register.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * user service
 */
@Slf4j
@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userMapper;

    /**
     * create user
     *
     * @param name
     * @param password
     * @param phone
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> createUser(String name,
                                          String password,
                                          String phone) throws Exception {

        Map<String, Object> result = new HashMap<>(5);
        if (check(result, StringUtils.isAnyEmpty(name, password, phone),
                Status.USER_NAME_OR_PASSWORD_OR_PHONE_EMPTY, Constants.STATUS)) {
            return result;
        }

        User user = userMapper.selectByPhone(phone);
        if (check(result, null != user, Status.USER_PHONE_EXIST, Constants.STATUS)) {
            return result;
        }

        user = new User();
        user.setName(name);
        user.setUserId(RandomUtils.getRandomNumString(8));
        user.setPassword(EncryptionUtils.getMd5(password));
        user.setPhone(phone);

        // save user
        userMapper.insert(user);
        result.put(Constants.DATA_LIST, user);
        putMsg(result, Status.SUCCESS);
        return result;
    }

    /**
     * check
     *
     * @param result
     * @param bool
     * @param userNoOperationPerm
     * @param status
     * @return
     */
    private boolean check(Map<String, Object> result, boolean bool, Status userNoOperationPerm, String status) {
        //only admin can operate
        if (bool) {
            result.put(Constants.STATUS, userNoOperationPerm);
            result.put(status, userNoOperationPerm.getMsg());
            return true;
        }
        return false;
    }
}
