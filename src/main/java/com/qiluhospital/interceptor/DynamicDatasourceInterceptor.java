package com.qiluhospital.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class DynamicDatasourceInterceptor implements HandlerInterceptor {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DruidDataSourceCreator druidDataSourceCreator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dataSource;
        boolean result = true;
        String ds = request.getHeader("ds");
        try {
            log.info("dynamic datasource change: {}", ds);

            if (StringUtils.isEmpty(ds)) {
//                response(response, "缺少数据源id", "");
                //fixme 没datasource id就走默认的？
                return true;
            } else if (!((DynamicRoutingDataSource) dataSource).getDataSources().keySet().contains(ds)) {
                //去system module查对应的数据库连接信息再加入数据源
                DataSourceProperty dataSourceProperty = new DataSourceProperty();
                dataSourceProperty.setPoolName(ds);
//                    dataSourceProperty.setUrl(jsonObject.getString("dbUrl"));
                dataSourceProperty.setUrl("jdbc:mysql://");
                dataSourceProperty.setDriverClassName("dbDriver");
                dataSourceProperty.setUsername("username");
                dataSourceProperty.setPassword("password");
                //fixme: 具体的连接池参数（连接数等等）
                //dataSourceProperty.setDruid();
                DataSource dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
                dynamicRoutingDataSource.addDataSource(dataSourceProperty.getPoolName(), dataSource);
            }
            DynamicDataSourceContextHolder.push(ds);
        } catch (Exception e) {
            //fixme 切换数据源报错了怎么处理？ response给前端一个通知？
            log.error("dynamic datasource change error,keep default datasource", e);
            response(response, "切换数据源出错", ds);
            result = false;
        }
        return result;
    }

    private void response(HttpServletResponse response, String errMsg, String ds) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.print("");
        } catch (IOException e) {
            log.error("response error", e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //切回默认数据源
        DynamicDataSourceContextHolder.clear();
    }

}