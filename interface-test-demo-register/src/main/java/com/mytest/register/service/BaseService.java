package com.mytest.register.service;

import com.mytest.register.enums.Status;
import com.mytest.register.utils.Constants;
import com.mytest.register.utils.Result;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 13:11
 */
public class BaseService {

    /**
     * put message to map
     *
     * @param result
     * @param status
     * @param statusParams
     */
    protected void putMsg(Map<String, Object> result, Status status, Object... statusParams) {
        result.put(Constants.STATUS, status);
        if (statusParams != null && statusParams.length > 0) {
            result.put(Constants.MSG, MessageFormat.format(status.getMsg(), statusParams));
        } else {
            result.put(Constants.MSG, status.getMsg());
        }
    }

    /**
     * put message to result object
     *
     * @param result
     * @param status
     */
    protected void putMsg(Result result, Status status, Object... statusParams) {
        result.setCode(status.getCode());

        if (statusParams != null && statusParams.length > 0) {
            result.setMsg(MessageFormat.format(status.getMsg(), statusParams));
        } else {
            result.setMsg(status.getMsg());
        }

    }

    /**
     * get cookie info by name
     * @param request
     * @param name
     * @return get cookie info
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equalsIgnoreCase(name, cookie.getName())) {
                    return cookie;
                }
            }
        }

        return null;
    }
}