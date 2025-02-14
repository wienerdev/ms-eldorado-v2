package com.eldorado.microservico.usuario.controller;

import com.eldorado.commons.dto.UserLoginDto;
import com.eldorado.commons.exceptions.BadRequestException;
import com.eldorado.microservico.usuario.dto.UserDto;
import com.eldorado.microservico.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {

        try {
            return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> getLogin(@RequestBody UserLoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping("/{document}")
    public ResponseEntity<UserDto> findById(@PathVariable String document) {
        log.info("Retrieving all employees");
        return ResponseEntity.ok(userService.findById(document));
    }
}
