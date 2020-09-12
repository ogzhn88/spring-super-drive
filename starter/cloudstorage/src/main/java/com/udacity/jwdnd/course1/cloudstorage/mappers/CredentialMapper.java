package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("Select * from CREDENTIALS where userid = #{userid}")
    List<Credential> findAllUserCredentials(Integer userid);

    @Select("Select * from CREDENTIALS where credentialid=#{credentialid}")
    Credential findCredential(int credentialid);

    @Insert("Insert into CREDENTIALS (url,username,key,password,userid) VALUES (#{url},#{username},#{key},#{password},#{userid})")
    @Options(keyProperty = "credentialid" ,useGeneratedKeys = true)
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialid}")
    int deleteCredential(int credentialid);

    @Update("Update CREDENTIALS SET url=#{url},username=#{username},key=#{key},password=#{password},userid=#{userid} where credentialid=#{credentialid}")
    int updateCredential(Credential credential);

}
