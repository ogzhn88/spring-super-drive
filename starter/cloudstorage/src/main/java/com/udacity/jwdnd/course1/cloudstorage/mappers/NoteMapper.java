package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("Select * from NOTES where userid = #{userid}")
    List<Note> findAllUserNotes(Integer userid);

    @Select("Select * from NOTES where noteid=#{noteid}")
    Note findNote(int noteid);

    @Insert("Insert into NOTES (notetitle,notedescription,userid) VALUES (#{notetitle},#{notedescription},#{userid})")
    @Options(keyProperty = "noteid" ,useGeneratedKeys = true)
    int insertNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteid}")
    int deleteNote(int noteid);

    @Update("Update NOTES SET notetitle=#{notetitle},notedescription=#{notedescription},userid=#{userid} where noteid=#{noteid}")
    int updateNote(Note note);

}
