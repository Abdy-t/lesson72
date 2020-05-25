package com.example.exam9.frontend;

import com.example.exam9.domain.message.Message;
import com.example.exam9.domain.message.MessageRepository;
import com.example.exam9.domain.message.MessageService;
import com.example.exam9.domain.user.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class FrontendController {

    private final UserService userService;
    private final PropertiesService propertiesService;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final ResetRepository resetRepository;
    private final MessageRepository messageRepository;
    @Autowired
    RestTemplate restTemplate;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    @GetMapping
    public String getMainPage(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        return "index";
    }

    @GetMapping("/chat")
    public String pageChat(Model model,Principal principal)
    {
        return "chat";
    }
    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
        var user = userService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new UserRegisterForm());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid UserRegisterForm userRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes, @RequestParam(name = "g-recaptcha-response") String captchaResponse) {
        attributes.addFlashAttribute("dto", userRequestDto);
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LfeMvcUAAAAADN-fheeuDZ8mISg78nVjdXNle61&response="+captchaResponse;
        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        userService.register(userRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/forgot-password")
    public String pageForgotPassword(Model model) {
        return "forgot";
    }

    @PostMapping("/forgot-password")
    public String submitForgotPasswordPage(@RequestParam("email") String email,
                                           RedirectAttributes attributes) {

        if (!userRepository.existsByEmail(email)) {
            attributes.addFlashAttribute("errorText", "Entered email does not exist!");
            return "redirect:/";
        }

        PasswordResetToken pToken = PasswordResetToken.builder()
                .user(userRepository.findByEmail(email).get())
                .token(UUID.randomUUID().toString())
                .build();

        resetRepository.deleteAll();
        resetRepository.save(pToken);

        return "redirect:/forgot-success";
    }

    @GetMapping("/forgot-success")
    public String pageResetPassword(Model model) {
        return "forgot-success";
    }

}
