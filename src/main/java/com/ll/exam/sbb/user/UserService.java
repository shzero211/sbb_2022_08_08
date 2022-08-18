package com.ll.exam.sbb.user;

import com.ll.exam.sbb.Question.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public SiteUser create(String username,String email,String password){
        SiteUser user=new SiteUser(username,email,passwordEncoder.encode(password));
        try{
            userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            SiteUser oldUser=userRepository.findByUsername(username).get();
            if(oldUser!=null){
                throw new DataIntegrityViolationException("이름이 중복되는 계정존재");
            }else{
                throw new DataIntegrityViolationException("이메일이 중복되는 계정존재");
            }
        }
        return user;
    }
    public SiteUser getUser(String username){
        Optional<SiteUser> siteUser =userRepository.findByUsername(username);
        if(siteUser.isPresent()){
            return siteUser.get();
        }else{
            throw new DataNotFoundException("siteUser not found");
        }
    }
}
