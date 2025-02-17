package com.babbangona.tenantmanagement.controllers;


import com.babbangona.commons.library.dto.TenantDto;
import com.babbangona.commons.library.dto.response.BaseResponse;
import com.babbangona.tenantmanagement.services.TenantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenant")
@AllArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PreAuthorize("hasAuthority('TENANT_UPDATE')")
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<TenantDto>> updateTenant(
            @RequestHeader(name = "Tenant-Id", required = false) Long tenantIdHeader,
            @Valid @RequestBody TenantDto tenantUpdateDto) {

        // Check if header is equals -1 (MASTER_ADMIN)
        if (tenantIdHeader.equals(-1L)) {
            TenantDto updatedTenant = tenantService.updateTenant(tenantUpdateDto);
            return ResponseEntity.ok(new BaseResponse<>(200, "Tenant updated successfully by MASTER_ADMIN"));
        } else {
            // Otherwise, check if the header tenant id matches the tenant id in the DTO
            if (tenantIdHeader.equals(tenantUpdateDto.getTenantId()) && tenantService.findByUsernameTenantId(tenantUpdateDto.getUsername(),tenantUpdateDto.getTenantId())) {
                TenantDto updatedTenant = tenantService.updateTenant(tenantUpdateDto);
                return ResponseEntity.ok(new BaseResponse<>(200, "Tenant updated successfully by DOMAIN_ADMIN"));
            } else {
                throw new AccessDeniedException("Unauthorized: Tenant ID mismatch.");
            }
        }
    }

}
