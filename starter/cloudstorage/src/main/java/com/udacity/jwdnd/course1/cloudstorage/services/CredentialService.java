package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    CredentialMapper credentialMapper;
    @Autowired
    EncryptionService encryptionService;

    public List<Credential> getAllUserCredentials(int userid){

        return credentialMapper.findAllUserCredentials(userid);
    }

    public Credential getCredential(int credentialid){
        Credential cr  = credentialMapper.findCredential(credentialid);
        cr.setPassword(encryptionService.decryptValue(cr.getPassword(),cr.getKey()));
        return cr;
    }

    public int saveCredential(Credential credential){

        credential.setPassword(encryptionService.encryptValue(credential.getPassword(),credential.getKey()));

        return credentialMapper.insertCredential(credential);
    }

    public int deleteCredential(int credentialid){

        try {
            return credentialMapper.deleteCredential(credentialid);

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int updateCredential(Credential credential){

        credential.setPassword(encryptionService.encryptValue(credential.getPassword(),credential.getKey()));

        return credentialMapper.updateCredential(credential);
    }

}
