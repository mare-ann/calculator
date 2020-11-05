package com.maryann.calculator.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    private List<String> book = new ArrayList<>();
    {
        book.add("Harry potter 1");
        book.add("Harry potter 2");
        book.add("Harry potter 3");
        book.add("Harry potter 4");
        book.add("Harry potter 5");
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id) {
        return book.get(id);
    }

    @PostMapping
    public Integer getBook(@RequestBody String name) {
        book.add(name);
        return book.size() - 1;
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @RequestBody String name) {
        book.set(id, name);
        return name;
    }

    @GetMapping
    public String allBook() {
        return book.toString();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        book.remove(id);
        return book.toString();
    }

    @PostMapping("/test/{id}")
    public String test(@PathVariable String id, @RequestParam String q, @RequestBody String body,
                           @RequestHeader Map<String, String> headers) {
        System.out.println("ID = " + id);
        System.out.println("q = " + q);
        System.out.println("body = " + body);
        System.out.println("headers = " + headers);
        return "test/" + id + "?q=" +  q + "\nheaders:\n" + headers + "\nbody:\n" + body;
    }
}
