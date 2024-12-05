package sg.nus.edu.iss.vttp5a_ssf_day18l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import sg.nus.edu.iss.vttp5a_ssf_day18l.model.Person;

@Controller
@RequestMapping("/person")
public class PersonController {
    List<Person> persons = new ArrayList<>();
    
    @GetMapping()
    public ModelAndView viewPersonList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("persons", persons);
        mav.setViewName("personlist");
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createPersonPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("person", new Person());
        mav.setViewName("personcreate");
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView createPerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.setViewName("personcreate");
        } else {
            persons.add(new Person(person.getId(), person.getFullName(), person.getPhone(), person.getEmail(), person.getDateOfBirth()));
            mav.setViewName("redirect:/person");
        }
        return mav;

    }
}
