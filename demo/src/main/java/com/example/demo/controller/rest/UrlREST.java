package com.example.demo.controller.rest;

import com.example.demo.request.UrlRequest;
import com.example.demo.service.UrlService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UrlREST {

    @Inject
    UrlService urlService;

    @PostMapping()
    public String shortenUrl(@RequestBody UrlRequest request, HttpServletRequest servelet){
        return urlService.shortenUrl(servelet, request.longUrl);
    }

    @GetMapping("/{url}")
    public RedirectView redirect(@PathVariable String url){
        return urlService.redirectUrl(url);
    }
}
