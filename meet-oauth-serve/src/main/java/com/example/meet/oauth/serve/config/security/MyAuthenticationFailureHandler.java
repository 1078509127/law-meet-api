package com.example.meet.oauth.serve.config.security;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String msg = exception.getLocalizedMessage();
        Map<String,Object> result = new HashMap<>();
        result.put("code",-1);//失败
        result.put("message",msg);

        //将结果对象转换成json字符串。
        JSONObject data = new JSONObject(result);

        writer.write(data.toString());
        writer.flush();
        writer.close();
    }
}