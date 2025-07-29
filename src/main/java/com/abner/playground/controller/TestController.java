package com.abner.playground.controller;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class TestController {

   /* private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("testFileUpload")
    @ResponseBody
    public String testFileUpload(@RequestParam("uploadFile") MultipartFile file, @RequestParam("FileType") String fileType) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        LOGGER.info("fileName {}, size {}", fileName, size);
        LOGGER.info(fileType);
        String path = "C:/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return "true";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("multifile")
    public String multifile() {
        return "/multifile";
    }*/
}
