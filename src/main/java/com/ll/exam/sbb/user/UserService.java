package com.ll.exam.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            SiteUser oldUser=userRepository.findByUsername(username);
            if(oldUser!=null){
                throw new DataIntegrityViolationException("이름이 중복되는 계정존재");
            }else{
                throw new DataIntegrityViolationException("이메일이 중복되는 계정존재");
            }
        }
        return user;
    }
}
