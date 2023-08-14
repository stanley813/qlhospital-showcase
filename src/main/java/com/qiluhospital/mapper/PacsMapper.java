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

    List<Map> todayImageData();

    List<Map> serverMonitor();

    List<Map> deviceWorkingStatus(String modalityType);


    List<Map> annualVerify();

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


