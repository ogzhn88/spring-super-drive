package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    CredentialService credentialService;
    @Autowired
    NoteService noteService;

    @GetMapping("/home")
    public String home(Authentication authentication, Model model){
        User sessionUser = userService.getUserByUserName(authentication.getName());
        List<File> files = fileService.getAllUserFiles(sessionUser.getUserid());
        model.addAttribute("files",files);
        List<Credential> credentials = credentialService.getAllUserCredentials(sessionUser.getUserid());
        model.addAttribute("credentials",credentials);
        List<Note> notes = noteService.getAllUserNotes(sessionUser.getUserid());
        model.addAttribute("notes",notes);

        return "home";
    }

}
