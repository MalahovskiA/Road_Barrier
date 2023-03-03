package by.malahovski.barriers.controllers;

import by.malahovski.barriers.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/all")
    public String allAccess() {
        return "public API";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public String userAccess() {
        return "user API";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public String managerAccess() {
        return "manager API";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "admin API";
    }

    @GetMapping(value = "/admin/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDetails getUserByName(@PathVariable String name) {
        return userDetailsService.loadUserByUsername(name);
    }

    @DeleteMapping(value = "/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) {
		Boolean isDeleted = userDetailsService.deleteUserById(id);
		if (isDeleted) {
			return ResponseEntity.ok(isDeleted);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isDeleted);
	}
}
