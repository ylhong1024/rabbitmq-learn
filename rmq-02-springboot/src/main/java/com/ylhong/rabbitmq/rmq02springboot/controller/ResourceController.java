package com.ylhong.rabbitmq.rmq02springboot.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ylhong
 * @date 2021/10/24
 */
@RestController
public class ResourceController {

    @GetMapping("/resource")
    public String getResource(String filepath) throws IOException {
        String result = "";
        final InputStream is = ClassUtils.getDefaultClassLoader().getResourceAsStream( filepath );
        result =  IOUtils.toString( is );
        return result;
    }

}
