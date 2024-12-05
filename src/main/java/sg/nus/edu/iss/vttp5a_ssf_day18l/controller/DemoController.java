package sg.nus.edu.iss.vttp5a_ssf_day18l.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.nus.edu.iss.vttp5a_ssf_day18l.model.Person;
import sg.nus.edu.iss.vttp5a_ssf_day18l.repo.MapRepo;
import sg.nus.edu.iss.vttp5a_ssf_day18l.util.Constants;


@Controller
@RequestMapping("/person/demo")
public class DemoController {
    @Autowired
    MapRepo mapRepo;
    
    @GetMapping()
    @ResponseBody
    public String testSerailizePerson(){
        List<Person> persons = new ArrayList<>();
        Person p1 = new Person(1,"Remus","99008877","remus@abc.com",LocalDate.now());
        Person p2 = new Person(2, "Gabriel", "90987654", "gabriel@abc.com", LocalDate.now());
        Person p3 = new Person(3, "Zack", "12345678", "zack@abc.com", LocalDate.now());
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        for(Person person:persons){
            JsonObject jObject = Json.createObjectBuilder().add("id", person.getId())
            .add("fullName", person.getFullName()).add("phone", person.getPhone())
            .add("email",person.getEmail()).add("dateOfBirth", person.getDateOfBirth().toString())
            .build();

            mapRepo.create(Constants.REDISKEY, String.valueOf(person.getId()), jObject.toString());
        }
        return "success";
        
    }
    
}
