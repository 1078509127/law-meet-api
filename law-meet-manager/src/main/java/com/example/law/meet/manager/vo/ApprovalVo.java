package com.example.law.meet.manager.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalVo {

    private Integer id;

    private Integer adminId;

    private String status;

    private String traceMsg;
}
