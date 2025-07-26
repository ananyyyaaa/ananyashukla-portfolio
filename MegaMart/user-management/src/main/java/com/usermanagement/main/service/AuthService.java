package com.usermanagement.main.service;

import com.usermanagement.main.dto.AuthRequest;
import com.usermanagement.main.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
