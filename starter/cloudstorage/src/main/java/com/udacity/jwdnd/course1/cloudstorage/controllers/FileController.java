package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;


    @PostMapping("/saveFile")
    public String saveFile (@RequestParam("fileUpload") MultipartFile multipartFile, Model model, Authentication authentication) throws IOException {
         if(fileService.getFileByFileName(multipartFile.getOriginalFilename())!= null){
           return "redirect:/home?fexists#nav-files";
         }
        User sessionUser = userService.getUserByUserName(authentication.getName());

        try {
            InputStream fis = multipartFile.getInputStream();
            byte[] filebytes =  fis.readAllBytes();
            String fileSize = String.valueOf(multipartFile.getSize());
            File file = new File(multipartFile.getOriginalFilename(),multipartFile.getContentType(),fileSize,sessionUser.getUserid(),filebytes);
            fileService.saveFile(file);
            fis.close();
            return "redirect:/home?filesaved#nav-files";

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/home?error#nav-files";

    }

    @GetMapping("/file/delete/{fileid}")
    public String deleteFile(@PathVariable("fileid") int fileid){

        fileService.deleteFile(fileid);

        return "redirect:/home?filedeleted#nav-files";
    }

    @ResponseBody
    @GetMapping("/file/view/{fileid}")
    public  ResponseEntity<Resource> getFile(Authentication authentication, @PathVariable("fileid") int fileid){

         User sessionUser = userService.getUserByUserName(authentication.getName());
         File userFile = fileService.getFile(fileid);
         return ResponseEntity.ok().contentType(MediaType.parseMediaType(userFile.getContenttype())).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + userFile.getFilename()).body(new ByteArrayResource(userFile.getFiledata()));

    }


}
