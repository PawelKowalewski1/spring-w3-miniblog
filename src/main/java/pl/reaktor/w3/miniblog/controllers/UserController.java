package pl.reaktor.w3.miniblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.reaktor.w3.miniblog.entities.User;
import pl.reaktor.w3.miniblog.forms.RegisterForm;
import pl.reaktor.w3.miniblog.repositories.RoleRepository;
import pl.reaktor.w3.miniblog.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
@Controller
public class UserController {

    private RoleRepository roleRepository;
    private UserRepository userRepository;


    @Autowired
    public UserController(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }



    @GetMapping("/user/register")
    public String userRegistration (Model model){

        model.addAttribute("registerForm", new RegisterForm());


        return "user/userRegistration";
    }

//@RequestMapping( name= "/user/register", method = RequestMethod.POST)
@PostMapping("/user/register")
    public String registerFormAction(@ModelAttribute @Valid RegisterForm registerForm,
                                     BindingResult bindingResult,
                                     Model model) {

    if (registerForm.getPassword() == null ||
            !registerForm.getPassword().equals(registerForm.getPassword2())) {
        bindingResult.rejectValue("password", "123", "prosze wprowadź prawidłowe takie same hasło");
        bindingResult.rejectValue("password2", "123", "prosze wprowadź prawidłowe takie samo hasło");

    }
    System.out.println(registerForm);
    System.out.println(registerForm.getPassword());
    System.out.println(bindingResult.toString());

    List<User>allByEmail = userRepository.findAllByEmail(registerForm.getEmail());
    if(!allByEmail.isEmpty()){
        bindingResult.rejectValue("email", "123", "Dany email już istnieje.");

    }
    //if(!registerForm.getEmail().contains("@")){
    //   model.addAttribute("emailError", "Błędny email");}

    if (bindingResult.hasErrors()) {
        return "user/userRegistration";
    }


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    User user = new User();
    user.setEmail(registerForm.getEmail());
    user.setFirstName(registerForm.getFirstName());
    user.setLastName(registerForm.getLastName());
    user.setAdded(new Date());
    user.setPassword(bCryptPasswordEncoder.encode(registerForm.getPassword()));
    user.setActive(true);
    user.getRoles().add(roleRepository.findOneByRoleName("USER").get());


    userRepository.save(user);
//return "goodJob";
    return "redirect:/";



}

    @GetMapping("/user/login")
    public String loginForm (){




        return "user/userLogin";
    }

}
