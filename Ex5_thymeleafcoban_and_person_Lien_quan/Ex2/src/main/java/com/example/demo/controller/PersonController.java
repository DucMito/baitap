package com.example.demo.controller;

import com.example.demo.db.InitDB;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.db.PersonDB.people;

@Controller
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String home() {
        return "index"; // Trả về trang index.html
    }


    @GetMapping("/getAll")
    public String getAllPerson(Model model) {
        List<Person> people = personService.getAllPerson();
        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("/person/{id}")
    public String getPersonById(Model model, @PathVariable String id){

        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        Map<String, List<Person>> peopleByCity = personService.getAllPerson().stream()
                .collect(Collectors.groupingBy(Person::getCity));
        model.addAttribute("peopleByCity", peopleByCity);
        return "peopleByCity";
    }

    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        Map<String, Long> jobCounts = personService.getAllPerson().stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.counting()));
        model.addAttribute("jobCounts", jobCounts);
        return "jobCounts";
    }

    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model) {
        double averageSalary = personService.getAllPerson().stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0);
        List<Person> aboveAverage = personService.getAllPerson().stream()
                .filter(p -> p.getSalary() > averageSalary)
                .collect(Collectors.toList());
        model.addAttribute("aboveAverage", aboveAverage);
        return "aboveAverageSalary";
    }

    @GetMapping("/longestName")
    public String longestName(Model model) {
        Person longestNamePerson = personService.getAllPerson().stream()
                .max(Comparator.comparingInt(p -> p.getFullName().length()))
                .orElse(null);
        model.addAttribute("longestNamePerson", longestNamePerson);
        return "longestName";
    }


    @GetMapping("/getPersonGender")
    public String getPersonGender(Model model, @RequestParam("id") String id) {
        Person person = people.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (person != null) {
            List<Person> sameGenderPeople = people.stream()
                    .filter(p -> p.getGender().equals(person.getGender()) && !p.getId().equals(person.getId())) // Use equals() for String comparison
                    .collect(Collectors.toList());
            model.addAttribute("person", person);
            model.addAttribute("sameGenderPeople", sameGenderPeople);
            return "personGender";
        } else {
            return "error";
        }
    }

    @GetMapping("/get")
    public String getSameIdPeople(Model model, @RequestParam("id") String id) {
        Person person = people.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (person != null) {
            List<Person> sameIdPeople = people.stream()
                    .filter(p -> !p.getId().equals(person.getId())) // Filter for *different* IDs
                    .collect(Collectors.toList());
            model.addAttribute("person", person);
            model.addAttribute("sameIdPeople", sameIdPeople);
            return "sameIdPeople";
        } else {
            return "error";
        }
    }

    @GetMapping("/getPersonIdDesc")
    public String getPersonIdDesc(Model model, @RequestParam("id") String id) {
        Person person = people.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (person != null) {
            List<Person> personIdDesc = people.stream()
                    .filter(p -> !p.getId().equals(person.getId())) // Sử dụng equals() để so sánh String
                    .sorted(Comparator.comparing(Person::getId).reversed()) // Sắp xếp String theo thứ tự ngược lại
                    .collect(Collectors.toList());
            model.addAttribute("person", person);
            model.addAttribute("personIdDesc", personIdDesc);
            return "personIdDesc";
        } else {
            return "error";
        }
    }

    @GetMapping("/getFourPerson")
    public String getFourPeople(Model model, @RequestParam("id") String id) {
        Person person = people.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (person != null) {
            List<Person> fourPeople = people.stream()
                    .filter(p -> !p.getId().equals(person.getId())) // Sử dụng equals() để so sánh String
                    .limit(4)
                    .collect(Collectors.toList());
            model.addAttribute("person", person);
            model.addAttribute("fourPeople", fourPeople);
            return "fourPeople";
        } else {
            return "error";
        }
    }

}



