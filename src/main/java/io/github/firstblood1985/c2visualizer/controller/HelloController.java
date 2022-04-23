package io.github.firstblood1985.c2visualizer.controller;
import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * usage of this class: HelloController
 * created by limin @ 2022/4/17
 */
@Api(tags="this is hello controller")
@RestController
@RequestMapping("/")
public class HelloController {

    @ApiOperation("Just say hello")
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult hello() {
        return CommonResult.success(null, "Hello from C2Visualizer");
    }
}
