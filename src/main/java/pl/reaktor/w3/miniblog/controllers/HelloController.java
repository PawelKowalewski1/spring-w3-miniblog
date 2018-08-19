package pl.reaktor.w3.miniblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String helloPage(Model model,
                            @RequestParam(name = "firstNameParam", required = false) String firstNameParam,
                            @RequestHeader("User-Agent") String userAgent) {
        model.addAttribute("value", "Value #1");
        model.addAttribute("ua", userAgent);

        model.addAttribute("firstNameParam", firstNameParam);
        return "helloPage";
    }

    @RequestMapping("/name/{name}")
    public String showName(Model model, @PathVariable String name) {
        model.addAttribute("modelName", name);

        return "showName";
    }

    //https://ksiegarnia.pwn.pl/Chromatografia-i-techniki-elektromigracyjne,487064082,p.html
    @RequestMapping("/{title},{id},p.html")
    public String showBook(Model model, @PathVariable String title,
                           @PathVariable String id) {
        model.addAttribute("bookId", id);
        model.addAttribute("bookName", title);
        return "showBook";

    }
    @RequestMapping("/osoba/{name}/{age}")
    public String showAge(Model model, @PathVariable String name,
                           @PathVariable String age) {
        model.addAttribute("userAge", age);
        model.addAttribute("userName", name);
        return "showAge";


}
}

