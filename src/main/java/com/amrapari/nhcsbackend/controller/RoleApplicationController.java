package com.amrapari.nhcsbackend.controller;

import com.amrapari.nhcsbackend.domain.Role;
import com.amrapari.nhcsbackend.domain.RoleApplication;
import com.amrapari.nhcsbackend.service.RoleApplicationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class RoleApplicationController {

    private final RoleApplicationService applicationService;

    @PostMapping
    public ResponseEntity<RoleApplication> applyForRole(@RequestBody ApplicationRequest request) {
        RoleApplication app = applicationService.applyForRole(request.getUsername(), request.getRequestedRole(), request.getNotes());
        return ResponseEntity.ok(app);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<RoleApplication>> getPendingApplications() {
        return ResponseEntity.ok(applicationService.getPendingApplications());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<RoleApplication> approveApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.approveApplication(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<RoleApplication> rejectApplication(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.rejectApplication(id));
    }
}

@Data
class ApplicationRequest {
    private String username;
    private Role requestedRole;
    private String notes;
}
