package uz.dev.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.dev.appjparelationships.entity.Faculty;
import uz.dev.appjparelationships.entity.Group;
import uz.dev.appjparelationships.payload.GroupDto;
import uz.dev.appjparelationships.repository.FacultyRepository;
import uz.dev.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    // VAZIRLIK UCHUN
    // READ
    @GetMapping
    private List<Group> groupList() {
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

    //Universitet ma'sul xodimi uchun
    @GetMapping("byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId) {
        List<Group> allByFacultyUniversityId = groupRepository.findAllByFaculty_UniversityId(universityId);
        List<Group> groupByUniversityId = groupRepository.getGroupByUniversityId(universityId);
//        List<Group> groupByUniversityIdNative = groupRepository.getGroupByUniversityIdNative(universityId);
        return allByFacultyUniversityId;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (optionalFaculty.isEmpty()) {
            return "Such faculty not found";
        }
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);

        return "Group added";
    }

    @PutMapping("/{id}")
    public String editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        Optional<Group> optionalGroup = groupRepository.findById(id);

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (optionalFaculty.isEmpty()) {
            return "Such faculty not found";
        }
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            group.setName(groupDto.getName());
            group.setFaculty(optionalFaculty.get());
            groupRepository.save(group);
            return "group edited";
        }

        return "Group not found";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id) {
        try {
            groupRepository.deleteById(id);
            return "group deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }

}
