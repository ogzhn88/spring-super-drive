package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    public List<File> getAllUserFiles(int userid){

        return fileMapper.findAllUserFiles(userid);
    }

    public File getFile(int fileid){
        return fileMapper.findFile(fileid);
    }

    public File getFileByFileName(String filename){
        return fileMapper.findFileByFileName(filename);
    }


    public int saveFile(File file){

        return fileMapper.insertFile(file);
    }

    public int deleteFile(int fileid){

        try {
            return fileMapper.deleteFile(fileid);

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int updateFile(File file){
        return fileMapper.updateFile(file);
    }

}
