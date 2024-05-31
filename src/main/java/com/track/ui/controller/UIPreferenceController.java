package com.track.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIPreferenceController {


    @GetMapping("/ui/preference")
    private String viewPage(Model model){
        model.addAttribute("pref", null);
        return "preference/preference";
    }
}
