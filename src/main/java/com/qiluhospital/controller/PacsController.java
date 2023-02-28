package com.qiluhospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiluhospital.service.PacsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacs")
@Slf4j
public class PacsController {

    @Autowired
    PacsService pacsService;

    /**
     * 系统分析
     */
    @GetMapping("systemAnalysis")
    public JSONObject systemAnalysis() {
        log.info("systemAnalysis...");
//        return pacsService.systemAnalysis();
        return new JSONObject();
    }

    /**
     * 当日报告系统数据分析
     */
    @GetMapping("todayReportAnalysis")
    public JSONObject todayReportAnalysis() {
        log.info("todayReportAnalysis...");
        return pacsService.todaySystemData();
    }

    /**
     * 服务器监控
     */
    @GetMapping("serviceMonitor")
    public JSONObject serviceMonitor() {
        log.info("serviceMonitor...");
        return pacsService.serverMonitor();
    }

    /**
     * 年检查量
     */
    @GetMapping("annualVerifyAmount")
    public void annualVerifyAmount() {
        System.out.println(123);
    }

    /**
     * 设备运行情况
     */
    @GetMapping("equipmentWorkingStatus")
    public void equipmentWorkingStatus() {
        System.out.println(123);
    }

}
