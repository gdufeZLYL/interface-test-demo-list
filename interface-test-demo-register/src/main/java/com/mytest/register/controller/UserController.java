package com.mytest.register.controller;

import com.mytest.register.service.UserService;
import com.mytest.register.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mytest.register.enums.Status.*;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 9:47
 */
@Slf4j
@Api(tags = "USER_TAG" , position = 14)
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * create user
     *
     * @param name
     * @param password
     * @param phone
     * @return
     */
    @ApiOperation(value = "createUser", notes= "CREATE_USER_NOTES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "NAME",type = "String"),
            @ApiImplicitParam(name = "password", value = "PASSWORD", type ="String"),
            @ApiImplicitParam(name = "phone", value = "PHONE", type = "String")
    })
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public Result createUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "phone") String phone) {

        log.info("create user, name: {}, password: {}, phone: {}", name, password, phone);
        try {
            Map<String, Object> result = userService.createUser(name, password, phone);
            return returnDataList(result);
        } catch (Exception e){
            log.error(CREATE_USER_ERROR.getMsg(),e);
            return error(CREATE_USER_ERROR.getCode(), CREATE_USER_ERROR.getMsg());
        }
    }
}
