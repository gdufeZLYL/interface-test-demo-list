package com.mytest.register.controller;

import com.mytest.register.enums.Status;
import com.mytest.register.model.Resource;
import com.mytest.register.utils.Constants;
import com.mytest.register.utils.PageInfo;
import com.mytest.register.utils.Result;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 11:38
 */
public class BaseController {

    /**
     * comma
     */
    public static final String COMMA = ",";

    /**
     * http header
     */
    public static final String HTTP_HEADER_UNKNOWN = "unKnown";

    /**
     * http X-Real-IP
     */
    public static final String HTTP_X_REAL_IP = "X-Real-IP";

    /**
     * http X-Forwarded-For
     */
    public static final String HTTP_X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * check params
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> checkPageParams(int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>(2);
        Status resultEnum = Status.SUCCESS;
        String msg = Status.SUCCESS.getMsg();
        if (pageNo <= 0) {
            resultEnum = Status.REQUEST_PARAMS_NOT_VALID_ERROR;
            msg = MessageFormat.format(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getMsg(), Constants.PAGE_NUMBER);
        } else if (pageSize <= 0) {
            resultEnum = Status.REQUEST_PARAMS_NOT_VALID_ERROR;
            msg = MessageFormat.format(Status.REQUEST_PARAMS_NOT_VALID_ERROR.getMsg(), Constants.PAGE_SIZE);
        }
        result.put(Constants.STATUS, resultEnum);
        result.put(Constants.MSG, msg);
        return result;
    }

    /**
     * get ip address in the http request
     *
     * @param request
     * @return client ip address
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String clientIp = request.getHeader(HTTP_X_FORWARDED_FOR);

        if (StringUtils.isNotEmpty(clientIp) && !StringUtils.equalsIgnoreCase(HTTP_HEADER_UNKNOWN, clientIp)) {
            int index = clientIp.indexOf(COMMA);
            if (index != -1) {
                return clientIp.substring(0, index);
            } else {
                return clientIp;
            }
        }

        clientIp = request.getHeader(HTTP_X_REAL_IP);
        if (StringUtils.isNotEmpty(clientIp) && !StringUtils.equalsIgnoreCase(HTTP_HEADER_UNKNOWN, clientIp)) {
            return clientIp;
        }

        return request.getRemoteAddr();
    }

    /**
     * return data list
     *
     * @param result
     * @return
     */
    public Result returnDataList(Map<String, Object> result) {
        Status status = (Status) result.get(Constants.STATUS);
        if (status == Status.SUCCESS) {
            String msg = Status.SUCCESS.getMsg();
            Object datalist = result.get(Constants.DATA_LIST);
            return success(msg, datalist);
        } else {
            Integer code = status.getCode();
            String msg = (String) result.get(Constants.MSG);
            return error(code, msg);
        }
    }

    /**
     * return data list with paging
     * @param result
     * @return
     */
    public Result returnDataListPaging(Map<String, Object> result) {
        Status status = (Status) result.get(Constants.STATUS);
        if (status == Status.SUCCESS) {
            result.put(Constants.MSG, Status.SUCCESS.getMsg());
            PageInfo<Resource> pageInfo = (PageInfo<Resource>) result.get(Constants.DATA_LIST);
            return success(pageInfo.getLists(), pageInfo.getCurrentPage(), pageInfo.getTotalCount(),
                    pageInfo.getTotalPage());
        } else {
            Integer code = status.getCode();
            String msg = (String) result.get(Constants.MSG);
            return error(code, msg);
        }
    }

    /**
     * success
     *
     * @return
     */
    public Result success() {
        Result result = new Result();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());

        return result;
    }

    /**
     * success does not need to return data
     *
     * @param msg
     * @return
     */
    public Result success(String msg) {
        Result result = new Result();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(msg);

        return result;
    }

    /**
     * return data no paging
     *
     * @param msg
     * @param list
     * @return
     */
    public Result success(String msg, Object list) {
        Result result = getResult(msg, list);
        return result;
    }

    /**
     * return data no paging
     *
     * @param list
     * @return
     */
    public Result success(Object list) {
        Result result = getResult(Status.SUCCESS.getMsg(), list);
        return result;
    }

    /**
     * return the data use Map format, for example, passing the value of key, value, passing a value
     * eg. "/user/add"  then return user name: zhangsan
     *
     * @param msg
     * @param object
     * @return
     */
    public Result success(String msg, Map<String, Object> object) {
        Result result = getResult(msg, object);
        return result;
    }

    /**
     * return data with paging
     *
     * @param totalList
     * @param currentPage
     * @param total
     * @return
     */
    public Result success(Object totalList, Integer currentPage,
                          Integer total, Integer totalPage) {
        Result result = new Result();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());

        Map<String, Object> map = new HashMap<>(4);
        map.put(Constants.TOTAL_LIST, totalList);
        map.put(Constants.CURRENT_PAGE, currentPage);
        map.put(Constants.TOTAL_PAGE, totalPage);
        map.put(Constants.TOTAL, total);
        result.setData(map);
        return result;
    }

    /**
     * error handle
     *
     * @param code
     * @param msg
     * @return
     */
    public Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

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
     * get result
     * @param msg
     * @param list
     * @return
     */
    private Result getResult(String msg, Object list) {
        Result result = new Result();
        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(msg);

        result.setData(list);
        return result;
    }
}
