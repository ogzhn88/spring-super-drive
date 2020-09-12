package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteMapper noteMapper;

    public List<Note> getAllUserNotes(int userid){

        return noteMapper.findAllUserNotes(userid);
    }

    public Note getNote(int noteid){

        return noteMapper.findNote(noteid);
    }

    public int saveNote(Note note){


        return noteMapper.insertNote(note);
    }

    public int deleteNote(int noteid){

        try {
            return noteMapper.deleteNote(noteid);

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int updateNote(Note note){

        return noteMapper.updateNote(note);
    }

}
