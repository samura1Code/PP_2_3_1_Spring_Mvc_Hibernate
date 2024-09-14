package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UsersServiceImpl;


@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UsersServiceImpl usersServiceImpl;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersServiceImpl.getAllUsers());
        return "users";
    }

    @GetMapping("/userDetails")
    public String getUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUser(id));
        return "userDetails";
    }

    @GetMapping("/formUsers")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "formUsers";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        usersServiceImpl.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", usersServiceImpl.getUser(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        usersServiceImpl.update(user.getId(), user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        usersServiceImpl.delete(id);
        return "redirect:/";
    }
}