package com.babbangona.tenantmanagement.services;

import com.babbangona.commons.library.dto.TenantDto;
import com.babbangona.commons.library.dto.UserDto;
import com.babbangona.commons.library.dto.response.BaseResponse;
import com.babbangona.commons.library.entities.Role;
import com.babbangona.commons.library.entities.Tenant;
import com.babbangona.commons.library.entities.User;
import com.babbangona.commons.library.repo.RoleRepository;
import com.babbangona.commons.library.repo.TenantRepository;
import com.babbangona.commons.library.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TenantService {
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

 /*   public BaseResponse<UserDto> saveUser(UserDto dto) {
        BaseResponse response = new BaseResponse<>();
        User entity = new User();
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setUsername(dto.getUsername());

        //BeanUtils.copyProperties(dto, entity);

        Tenant tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Tenant ID"));
        entity.setTenant(tenant);

        Collection<Role> roles = roleRepository.findAllById(dto.getRoleIds());
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("Invalid Role IDs provided");
        }
        entity.setUserRoles(roles);

        User savedUser = userRepository.save(entity);

        dto.setPassword("******");
        dto.setUserId(savedUser.getId());
        response.setData(dto);
        return response;
    }*/



    public TenantDto updateTenant(TenantDto tenantUpdateDto) {
        return new TenantDto();
    }

    public boolean findByTenantId(Long tenantId) {
        Optional<User> user = userRepository.findByTenant_Id(tenantId);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }



/*    public User mapToEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        if (dto.getTenantId() != null) {
            Tenant tenant = tenantRepository.findById(dto.getTenantId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Tenant ID"));
            user.setTenant(tenant);
        }

        if (dto.getRoleIds() != null && !dto.getRoleIds().isEmpty()) {
            Collection<Role> roles = roleRepository.findAllById(dto.getRoleIds());
            user.setUserRoles(roles);
        }

        return user;
    }*/

}
