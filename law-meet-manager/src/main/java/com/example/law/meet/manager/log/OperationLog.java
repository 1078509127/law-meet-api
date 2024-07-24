package com.example.law.meet.manager.log;

import java.lang.annotation.*;

/**
 * @author dam
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})  // 表示注解可以用在参数、方法上面
@Retention(RetentionPolicy.RUNTIME)  // 设置注解的保留策略为 RUNTIME，这意味着该注解信息将在编译后的字节码中保留，并能在运行时通过反射获取
@Documented // 标记该注解将被包含在生成的 JavaDoc 文档中，便于开发者查阅和理解
public @interface OperationLog {

    /**
     * 模块名称
     */
    public String title() default "";

    /**
     * 方法名称
     */
    public String detail() default "";

    /**
     * 业务类型
     */
    public BusinessTypeEnum businessType() default BusinessTypeEnum.OTHER;

    /**
     * 操作人类别（手机用户、网页用户）
     */
    public OperatorTypeEnum operatorType() default OperatorTypeEnum.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;

}
