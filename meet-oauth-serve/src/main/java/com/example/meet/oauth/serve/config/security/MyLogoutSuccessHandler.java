package com.example.meet.oauth.serve.config.security;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义退出
 * */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);//失败
        result.put("message","注销成功");

        //将结果对象转换成json字符串。
        JSONObject data = new JSONObject(result);
        //返回json数据到前端。
        writer.write(data.toString());
        writer.flush();
        writer.close();
    }
}

