package sg.nus.edu.iss.vttp5a_ssf_day18l.controller;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.Valid;
import sg.nus.edu.iss.vttp5a_ssf_day18l.model.Person;
import sg.nus.edu.iss.vttp5a_ssf_day18l.repo.MapRepo;
import sg.nus.edu.iss.vttp5a_ssf_day18l.util.Constants;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    MapRepo mapRepo;
    
    @GetMapping()
    public ModelAndView viewPersonList(){
        ModelAndView mav = new ModelAndView();
        Map<Object, Object> entries = mapRepo.getEntries(Constants.REDISKEY);
        List<Person> persons = new ArrayList<>();

        for(Entry<Object, Object> entry:entries.entrySet()){
            JsonReader jsonReader = Json.createReader(new StringReader(entry.getValue().toString()));
            JsonObject jsonObject = jsonReader.readObject();

            Person p = new Person(jsonObject.getInt("id"), jsonObject.getString("fullName"), jsonObject.getString("phone"), jsonObject.getString("email"), LocalDate.parse(jsonObject.getString("dateOfBirth")));
            persons.add(p);
        }

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
            JsonObject jObject = Json.createObjectBuilder().add("id", person.getId())
            .add("fullName", person.getFullName()).add("phone", person.getPhone())
            .add("email",person.getEmail()).add("dateOfBirth", person.getDateOfBirth().toString())
            .build();

            mapRepo.create(Constants.REDISKEY, String.valueOf(person.getId()), jObject.toString());
            mav.setViewName("redirect:/person");
        }
        return mav;

    }
}
