package pl.reaktor.w3.miniblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String helloPage(Model model, @RequestParam(required = false) String firstName){
model.addAttribute("value", "Value #1");


model.addAttribute("firstName", firstName);
        return  "helloPage";
}}
