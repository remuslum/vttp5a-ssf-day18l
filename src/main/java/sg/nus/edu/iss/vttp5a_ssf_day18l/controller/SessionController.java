package sg.nus.edu.iss.vttp5a_ssf_day18l.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import sg.nus.edu.iss.vttp5a_ssf_day18l.model.Session;

@Controller
@RequestMapping()
public class SessionController {

    @GetMapping("/create")
    public ModelAndView getToCreateSessionPage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("createSession");
        return mav;
    }
    
    @PostMapping("/create")
    public ModelAndView createHttpSession(@RequestBody MultiValueMap<String, String> form, HttpSession httpSession){
        ModelAndView mav = new ModelAndView();
        Session session = new Session(form.getFirst("name"), form.getFirst("dateOfBirth"));
        httpSession.setAttribute("session", session);
        mav.setViewName("redirect:/list");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView viewHttpSession(HttpSession httpSession){
        ModelAndView mav = new ModelAndView();
        Optional<Session> session = Optional.ofNullable((Session) httpSession.getAttribute("session"));
        session.ifPresentOrElse(x -> mav.addObject("sessions", x), () -> mav.addObject("sessions", new Session()));
        mav.setViewName("sessionlist");
        return mav;
    }
}