package io.github.firstblood1985.c2visualizer.controller;
import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * usage of this class: HelloController
 * created by limin @ 2022/4/17
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public CommonResult index() {
        return CommonResult.success(null, "Hello from C2Visualizer");
    }
}
