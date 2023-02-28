package com.qiluhospital.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PacsMapper {

    public List<Map> annualReportAmount();

    public Integer totalAmount();

    public List<Map> todayMaxReport();
    public List<Map> todayMaxVerify();

    public Integer todayVerifyAmount();

    public Integer todayReportAmount();

    public List<Map> serverMonitor();

//
//    public void systemAnalysis();
//
//    public void todayReportAnalysis();
//
//    public void serviceMonitor();
//
//    public void annualVerifyAmount();
//
//    public void equipmentWorkingStatus();
}


