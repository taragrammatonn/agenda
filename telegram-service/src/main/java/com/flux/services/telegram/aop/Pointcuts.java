package com.flux.services.telegram.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.flux.services.telegram.service.TelegramUpdateService.messageProcessing(..))")
    public void allReceivedMessages() {}
}
