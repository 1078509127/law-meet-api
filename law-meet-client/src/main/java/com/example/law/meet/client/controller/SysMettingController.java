package com.example.law.meet.client.controller;

import com.example.law.meet.client.service.AuthService;
import com.example.law.meet.client.service.SysMettingService;
import com.example.law.meet.db.entity.SysMetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metting")
public class SysMettingController {
    @Autowired
    private SysMettingService sysmettingService;
    @Autowired
    private AuthService authService;

    //添加会见
    @PostMapping("/addmetting")
    public Object  addmetting(@RequestBody SysMetting sysMetting){

    int addMetting =  sysmettingService.addMetting(sysMetting);

        return  addMetting;
    }

    // 会见前一天消息提醒
    //查询 登陆用户是否存在 并预约成功
    // 查出预约信息 返回给前台

    /**
     * 活动推送
     * */
//    @GetMapping("/sendMsg")
//    public String sendMsg(@RequestParam String theme, @RequestParam String time, @RequestParam String provider, @RequestParam String site, @RequestParam String organ, @RequestParam(required = false) String content){
//        String accessToken = wechatUtil.getAccessToken();
//
//
//        List<System> all = dtsUserService.all();
//        String result = null;
//        for (DtsUser user : all) {
//            SubscriberVo subscriberVo = new SubscriberVo();
//            subscriberVo.setTouser(user.getWeixinOpenid());
//            subscriberVo.setTemplate_id("FAapMIqVsN3El4ONaIeHha1B0LHuYkJE4yCzLnCvMvk");
//            subscriberVo.setPage("pages/appointment/line_up");
//            Map<String,TemplateData> map = new HashMap<>();
//            map.put("thing2",new TemplateData(theme));
//            map.put("time4",new TemplateData(time));
//            map.put("thing1",new TemplateData(provider));
//            map.put("thing3",new TemplateData(site));
//            map.put("thing7",new TemplateData(organ));
//            subscriberVo.setData(map);
//            result = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken,subscriberVo, String.class);
//            System.out.println(result);
//        }
//        return result;
//    }


}
