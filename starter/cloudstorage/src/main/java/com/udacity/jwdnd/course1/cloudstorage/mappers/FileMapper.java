package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("Select * from FILES where userid=#{userid}")
    List<File> findAllUserFiles(int userid);

    @Select("Select * from FILES where fileid=#{fileid}")
     File findFile(int fileid);

    @Select("Select * from FILES where filename=#{filename}")
    File findFileByFileName(String filename);

    @Insert("Insert into FILES (filename,contenttype,filesize,userid,filedata) VALUES (#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(keyProperty = "fileid" ,useGeneratedKeys = true)
    int insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid=#{fileid}")
    int deleteFile(int fileid);

    @Update("Update FILES SET filename=#{filename},conttenttype=#{contenttype},filesize=#{filesize},userid=#{userid},filedata=#{filedata} where fileid=#{fileid}")
    int updateFile(File file);

}
