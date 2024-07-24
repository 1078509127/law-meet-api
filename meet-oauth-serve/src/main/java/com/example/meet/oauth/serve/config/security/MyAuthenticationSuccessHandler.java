package com.example.meet.oauth.serve.config.security;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 成功处理器
 */
public class MyAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {

    /*private String url;


    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }*/

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Object principal = authentication.getPrincipal();
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("messsage","登录成功");
        result.put("data",principal);
        JSONObject data = new JSONObject(result);
        writer.write(data.toString());
        writer.flush();
        writer.close();
    }
}
