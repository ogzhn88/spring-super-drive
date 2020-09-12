package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;


    @PostMapping("/note/save")
    public String saveNote (@RequestParam(name = "noteTitle") String noteTitle , @RequestParam(name = "noteDescription") String noteDescription
     , @RequestParam(name = "noteId") String noteId, Authentication authentication){
        User sessionUser = userService.getUserByUserName(authentication.getName());

               Note note = new Note();
        try {
            if (noteId.isBlank() && noteId.isEmpty()){
                note = new Note(noteTitle,noteDescription,sessionUser.getUserid());
                noteService.saveNote(note);
                return "redirect:/home?nsaved#nav-notes";
            }else {
                note = noteService.getNote(Integer.parseInt(noteId));
                note.setNotedescription(noteDescription);
                note.setNotetitle(noteTitle);
                noteService.updateNote(note);
                return "redirect:/home?nedited#nav-notes";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/home?error#nav-notes";
    }

    @GetMapping("/note/delete/{noteid}")
    public String deleteFile(@PathVariable("noteid") int noteid){

        noteService.deleteNote(noteid);

        return "redirect:/home?ndeleted#nav-notes";
    }

}
