package com.nathan.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsController {
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/details")
    public String getDetails() {
        return "All users can see this";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/details")
    public String getPrivateDetails() {
        return "Only admins can see this";
    }
}
