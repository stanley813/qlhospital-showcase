package com.qiluhospital.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.qiluhospital.mapper.PacsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DS("IMPAX")
@Service
public class PacsService {

    @Autowired
    PacsMapper pacsMapper;

    public JSONObject systemAnalysis() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("annualVerifyAmount", pacsMapper.annualReportAmount());
        jsonObject.put("totalAmount", pacsMapper.totalAmount());
        return jsonObject;
    }

    @DS("ris")
    public JSONObject todaySystemData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("todayReportAmount", pacsMapper.todayReportAmount());
        jsonObject.put("todayVerifyAmount", pacsMapper.todayVerifyAmount());
        jsonObject.put("todayMaxReport", pacsMapper.todayMaxReport());
        jsonObject.put("todayMaxVerify", pacsMapper.todayMaxVerify());
        return jsonObject;
    }

    public JSONObject serverMonitor() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverMonitor", pacsMapper.serverMonitor());
        return jsonObject;
    }

    public List<Map> todayImageData() {
        return pacsMapper.todayImageData();
    }

    public JSONObject deviceWorkingStatus() {
        JSONObject jsonObject = new JSONObject();
        for (ModalityType value : ModalityType.values()) {
            jsonObject.put(value.name(), pacsMapper.deviceWorkingStatus(value.name()));
        }
        return jsonObject;
    }

    public JSONObject annualVerify() {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = pacsMapper.annualVerify();
        JSONArray year = new JSONArray();
        JSONArray amount = new JSONArray();
        for (Map map : list) {
            year.add(map.get("year"));
            amount.add(map.get("amount"));
        }
        jsonObject.put("year", year);
        jsonObject.put("amount", amount);
        return jsonObject;
    }

    enum ModalityType {
        CT, MR, DX;
    }

}
