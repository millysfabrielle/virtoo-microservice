package br.edu.unievangelica.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/foo")
public class FooController {

    @RequestMapping(method = RequestMethod.GET)
    public String readFoo() {
        return "read foo " + UUID.randomUUID().toString();
    }

    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(method = RequestMethod.POST)
    public String writeFoo() {
        return "write foo " + UUID.randomUUID().toString();
    }

}
