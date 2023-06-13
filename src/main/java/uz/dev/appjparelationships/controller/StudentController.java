package uz.dev.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.dev.appjparelationships.entity.Student;
import uz.dev.appjparelationships.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // 1. Ministry
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        // 1-1=0 2-1=1 3-1=2
        // select * from student limit 10 offset (0*10)
        // select * from student limit 10 offset (1*10)
        // select * from student limit 10 offset (2*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    // 2. University
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        // 1-1=0 2-1=1 3-1=2
        // select * from student limit 10 offset (0*10)
        // select * from student limit 10 offset (1*10)
        // select * from student limit 10 offset (2*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    // 3. Faculty dekanat
    // 4. Group owner
}
