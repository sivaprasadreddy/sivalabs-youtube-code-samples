package com.sivalabs.myapp.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
/**
 TEMP FIX: https://github.com/zalando/problem-spring-web/issues/707
 */
@ControllerAdvice
public class ProblemSecurityAdvice implements ProblemHandling, SecurityAdviceTrait {
}