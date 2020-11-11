package com.maryann.calculator.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Home page controller
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/*")
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String homeSpan() {
        return "home";
    }

}
