package com.mytest.register.controller;

import com.mytest.register.enums.Status;
import com.mytest.register.utils.Constants;
import com.mytest.register.utils.RandomUtils;
import com.mytest.register.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 15:38
 */
@Slf4j
@Api(tags = "SHORT_CUT_TAG" , position = 14)
@RestController
@RequestMapping("/shortCut")
public class ShortCutController extends BaseController {


    @ApiOperation(value = "getRandomNum", notes= "GET_RANDOM_NUM_NOTES")
    @ApiImplicitParam(name = "length", value = "length", dataType = "Int", example = "100")
    @GetMapping(value = "/getRandomNum")
    @ResponseStatus(HttpStatus.OK)
    public Result getRandomNum(@RequestParam(value = "length") int length) {
        String randomNum = RandomUtils.getRandomNumString(11);
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.STATUS, Status.SUCCESS);
        result.put(Constants.DATA_LIST, randomNum);
        return returnDataList(result);
    }
}
