package uz.dev.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.dev.appjparelationships.entity.Subject;
import uz.dev.appjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    // CREATE
    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName) {
            return "This subject already exist";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    // READ
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject editedSubject = optionalSubject.get();
            editedSubject.setName(subject.getName());
            subjectRepository.save(editedSubject);
            return "Subject edited";
        }
        return "Subject not found";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        return "Subject deleted";
    }
}
