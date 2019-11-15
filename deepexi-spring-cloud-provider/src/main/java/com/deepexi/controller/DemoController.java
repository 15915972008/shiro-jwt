package com.deepexi.controller;

import lombok.Data;

import com.deepexi.service.DemoEntityService;
import com.deepexi.exception.DemoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.deepexi.pojo.converter.utils.ConverterUtils.convert;

@RestController
@RequestMapping("demo")
@Payload
public class DemoController {
    @Autowired
    private DemoEntityService service;

    @GetMapping("greeting")
    public String sayHello() {
        return "welcome!";
    }

    @GetMapping("convert")
    public Model doConvert() {
        return convert("welcome!", Model.class);
    }

    @GetMapping("sys-error")
    public void syserror() {
        throw new RuntimeException();
    }

    @GetMapping("biz-error")
    public void bizerror() {
        throw new DemoException();
    }

    @Data
    public static class Model {
        private String content;

		public void setContent(String source) {
			System.out.println("11");
			
		}
    }
}
