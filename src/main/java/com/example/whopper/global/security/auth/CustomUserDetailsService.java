package com.example.whopper.global.security.auth;

import com.example.whopper.domain.student.dao.StudentMongoRepository;
import com.example.whopper.domain.student.domain.StudentEntity;
import com.example.whopper.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentMongoRepository studentMongoRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {
        StudentEntity student = studentMongoRepository.findFirstByAccountId(accountId)
                .orElseThrow(()-> StudentNotFoundException.EXCEPTION);

        return new CustomUserDetails(student.getId());
    }
}
