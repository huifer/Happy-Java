package com.huifer.happy.file.controller;

import com.huifer.happy.file.service.LimiterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 限速查询
 */
@RestController
public class FileController {
    @GetMapping("/")
    public String heat() {
        return "heat";
    }

    @Autowired
    private LimiterFileService limiterFileService;


    @RequestMapping(value = "/download")
    public void getDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        limiterFileService.downloadBreak(name, request, response);
    }

}
