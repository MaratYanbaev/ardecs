package com.ardecs.rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 12.08.2019
 */
@ApiIgnore
@RestController
@RequestMapping("/myRestError")
public class RestErrorController {

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "You are not authorized to DELETE data!")
    public void methodDeleteNotAllowed() {
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "You are not authorized to UPDATE data!")
    public void methodPutNotAllowed() {
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "You are not authorized to CREATE data!")
    public void methodPostNotAllowed() {
    }
}
