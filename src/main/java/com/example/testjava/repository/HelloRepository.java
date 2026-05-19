package com.example.testjava.repository;

import com.example.testjava.domain.HelloMessage;

public interface HelloRepository {

    HelloMessage findHelloMessage(String recipient);
}
/*Controller -> Service -> Repository -> Domain -> DTO Response

Controller 只处理 HTTP 入参和出参。

Service 组织业务逻辑

Repository 模拟数据来源，，

Domain 表达业务对象，

DTO 负责接口响应，*/

//
//domain - 业务对象
//repository - 模拟数据来源
//dto-负责接口响应
