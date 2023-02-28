package com.qiluhospital.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @RequestMapping
    public String index(ModelMap map) {
        // 添加一个属性,用来在模板中根据这个key来读取对应的值
        map.addAttribute("msg", "thymeleaf");
        // return 模板文件的名称-->对应src/main/resources/templates/index.html
        return "index";
    }
}
