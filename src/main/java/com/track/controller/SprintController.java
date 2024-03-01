package com.track.controller;

import com.track.service.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sprint")
public class SprintController {

    @Autowired
    private ISprintService sprintService;



}
