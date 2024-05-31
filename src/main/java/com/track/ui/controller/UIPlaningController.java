package com.track.ui.controller;

import com.track.service.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIPlaningController {

    @Autowired
    private ISprintService sprintService;


    @GetMapping("/ui/planing")
    public String viewPage(Model model){
        model.addAttribute("planing", null);
        return "planing/planing";
    }
}
