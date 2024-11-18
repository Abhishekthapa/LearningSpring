package com.abhishek.thapa.learning_spring.web;

import com.abhishek.thapa.learning_spring.data.GuestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(@RequestParam(value="id", required=false)  String id, Model model) {
        model.addAttribute("guestList", guestRepository.findAll());
        return "guest";
    }
}
