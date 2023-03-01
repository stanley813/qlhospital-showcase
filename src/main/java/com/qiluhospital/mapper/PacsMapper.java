package com.qiluhospital.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface PacsMapper {

    List<Map> annualReportAmount();

    Integer totalAmount();

    List<Map> todayMaxReport();

    List<Map> todayMaxVerify();

    Integer todayVerifyAmount();

    Integer todayReportAmount();

    List<Map> serverMonitor();

//
//    void systemAnalysis();
//
//    void todayReportAnalysis();
//
//    void serviceMonitor();
//
//    void annualVerifyAmount();
//
//    void equipmentWorkingStatus();
}


