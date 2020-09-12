package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialController {
    @Autowired
    CredentialService credentialService;
    @Autowired
    UserService userService;

    @PostMapping(value={"/credential/save"})
    public String saveUser(@RequestParam(name="url") String url, @RequestParam(name="username") String username,
                           @RequestParam(name="password") String password,Authentication authentication,@RequestParam(name = "credentialId") String credentialId){
        User sessionUser = userService.getUserByUserName(authentication.getName());
        Credential myCr = new Credential(url,username, password, sessionUser.getUserid());
        try {
            if (!credentialId.isEmpty() && !credentialId.isBlank()){
                myCr.setCredentialid(Integer.parseInt(credentialId));
                credentialService.updateCredential(myCr);
                return "redirect:/home?cedited#nav-credentials";
            }else {
                credentialService.saveCredential(myCr);
                return "redirect:/home?csaved#nav-credentials";
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/home?error#nav-credentials";
    }


    @GetMapping(value = "/credentials/{credentialId}")
    @ResponseBody
    public Credential getCredential(@PathVariable("credentialId") int credentialId,Model model) {

        model.addAttribute("credentialId",credentialId);
        return credentialService.getCredential(credentialId);
    }

    @GetMapping("/credential/delete/{credentialid}")
    public String deleteFile(@PathVariable("credentialid") int fileid){

        credentialService.deleteCredential(fileid);

        return "redirect:/home?cdeleted#nav-credentials";
    }


}
