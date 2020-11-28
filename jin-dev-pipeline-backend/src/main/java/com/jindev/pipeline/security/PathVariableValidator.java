package com.jindev.pipeline.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PathVariableValidator {
    public boolean accessTest(Authentication authentication, String build) {
//        System.out.println("build : " + build);
        return true;
    }
}
