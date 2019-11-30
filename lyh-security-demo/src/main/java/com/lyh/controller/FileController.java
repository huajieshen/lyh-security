package com.lyh.controller;

import com.lyh.dto.FileInfo;


import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author shenhj
 * @version 1.0
 * @date 2019/11/27 20:53
 */
@RestController
@RequestMapping("/file")
public class FileController {

  String folder = "D:\\project\\lyh-security\\lyh-security-demo\\src\\main\\java\\com\\lyh\\controller";

  @PostMapping
  public FileInfo upload(MultipartFile file)throws Exception{


    System.out.println(file.getName());
    System.out.println(file.getOriginalFilename());
    System.out.println(file.getSize());

//    其他地方的时候
//    file.getInputStream();

//   本地
    File localFile = new File(folder, new Date().getTime() + ".txt");

    file.transferTo(localFile);
    return new FileInfo(localFile.getAbsolutePath());
  }


  @GetMapping("/{id}")
  public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)throws Exception{
    try(
            InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
            OutputStream outputStream = response.getOutputStream();
            ){

      response.setContentType("application/x-download");
      response.addHeader("Content-Disposition", "attachment;filename=test.txt");

      IOUtils.copy(inputStream, outputStream);
      outputStream.flush();


    }catch (Exception e){

    }
  }
}
