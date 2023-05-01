package com.techacademy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository AuthenticationRepository;

    public AuthenticationService(AuthenticationRepository repository) {
        this.AuthenticationRepository = repository;
    }

    /** 1件を検索して返す */
    public Optional<Authentication> getAuthentication(String code) {
        // リポジトリのfindByIdメソッドを呼び出す
        return AuthenticationRepository.findById(code);
    }


}
