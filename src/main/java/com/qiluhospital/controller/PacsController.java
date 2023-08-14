package com.qiluhospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiluhospital.service.PacsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
//        log.info("systemAnalysis...");
        return pacsService.systemAnalysis();
//        return new JSONObject();
    }

    /**
     * 当日报告系统数据分析
     */
    @GetMapping("todayReportAnalysis")
    public JSONObject todayReportAnalysis() {
//        log.info("todayReportAnalysis...");
        return pacsService.todaySystemData();
    }

    @GetMapping("todayImageData")
    public List<Map> todayImageData() {
//        log.info("todayReportAnalysis...");
        return pacsService.todayImageData();
    }

    /**
     * 服务器监控
     */
    @GetMapping("serverMonitor")
    public JSONObject serverMonitor() {
//        log.info("serviceMonitor...");
        return pacsService.serverMonitor();
    }

    /**
     * 设备运行情况
     */
    @GetMapping("deviceWorkingStatus")
    public JSONObject deviceWorkingStatus() {
        return pacsService.deviceWorkingStatus();
    }

    /**
     * 年检查量chart
     */
    @GetMapping("annualVerifyAmount")
    public JSONObject annualVerifyAmount() {
        return pacsService.annualVerify();
    }


}
