package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.fansite.FanSiteTop;
import com.example.zoutohanafansitedemo.service.FanSiteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fansite")
public class FanSiteController {
    private final FanSiteService fanSiteService;

    public FanSiteController(FanSiteService fanSiteService) {
        this.fanSiteService = fanSiteService;
    }

    @GetMapping
    public ResponseEntity<FanSiteTop> getFanSiteTopInfo() {
        return  ResponseEntity.ok(fanSiteService.getFanSiteTopInfo());
    }
}
