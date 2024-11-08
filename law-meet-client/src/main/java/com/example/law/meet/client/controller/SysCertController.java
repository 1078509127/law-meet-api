package com.example.law.meet.client.controller;

import com.example.law.meet.client.service.SysCertService;
import com.example.law.meet.common.utils.Result;
import com.example.law.meet.db.entity.SysCert;
import com.example.law.meet.db.entity.SysCertExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 认证中心
 */
@RestController
@RequestMapping("/cert")
public class SysCertController {

    @Autowired(required = false)
    private SysCertService sysCertService;

    @PostMapping("/add")
    public Result add(@RequestParam("file")MultipartFile[] file,
                      @RequestParam("userId")String userId,
                      @RequestParam("name")String name,
                      @RequestParam("phone")String phone,
                      @RequestParam("idCard")String idCard,
                      @RequestParam("wkCard")String wkCard,
                      @RequestParam("city")String city,
                      @RequestParam("sex")String sex,
                      @RequestParam("basDate")String basDate,
                      @RequestParam("baeDate")String baeDate) throws IOException {

        Byte gender = 0;
        if (sex.equals("女")){
            gender = 1;
        }else {
            gender = 0;
        }

        SysCert sysCert = new SysCert();
        sysCert.setUserId(Integer.valueOf(userId));
        sysCert.setName(name);
        sysCert.setPhone(phone);
        sysCert.setIdCard(idCard);
        sysCert.setWkCard(wkCard);
        sysCert.setCity(city);
        sysCert.setGender(gender);
        sysCert.setBasDate(LocalDate.parse(basDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay());
        sysCert.setBaeDate(LocalDate.parse(baeDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay());
        sysCert.setCreateTime(LocalDateTime.now());
        sysCert.setUpdateTime(LocalDateTime.now());
        int add = sysCertService.add(file, sysCert);
        if (add>0){
            return Result.success(add);
        }
        return Result.fail();
    }

    @GetMapping("/select")
    public Result select(@RequestParam Integer userId,@RequestParam String status){
        List<String> list = Arrays.asList(status.split(","));
        List<Integer> statusList = list.stream().map(Integer::parseInt).collect(Collectors.toList());

        SysCertExample select = sysCertService.select(userId,statusList);
        return Result.success(select);
    }
}
