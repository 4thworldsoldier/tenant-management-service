package com.babbangona.tenantmanagement.services;

import com.babbangona.commons.library.dto.TenantDto;
 import com.babbangona.commons.library.entities.User;
import com.babbangona.commons.library.repo.RoleRepository;
import com.babbangona.commons.library.repo.TenantRepository;
import com.babbangona.commons.library.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TenantService {
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public TenantDto updateTenant(TenantDto tenantUpdateDto) {
        return new TenantDto();
    }

    public boolean findByUsernameTenantId(String username, Long tenantId) {
        Optional<User> user = userRepository.findByUsernameAndTenant_Id(username,tenantId);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

}
