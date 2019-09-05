package com.huifer.happy.file.controller;

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

    @RequestMapping("/download_file")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {


    }
}
