package uz.dev.appjparelationships.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.dev.appjparelationships.entity.Address;
import uz.dev.appjparelationships.entity.University;
import uz.dev.appjparelationships.payload.UniversityDto;
import uz.dev.appjparelationships.repository.AddressRepository;
import uz.dev.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    // READ
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> universityList() {
        List<University> universities = universityRepository.findAll();
        return universities;
    }

    // CREATE
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    private String addUniversity(@RequestBody UniversityDto universityDto) {
        // Yangi address ochib oldik
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        // Yasab olgan Address objectimizni db ga saqladik va bizga saqlangan Addressni berdi
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "University added";
    }

    // UPDATE
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    private String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();

            university.setName(universityDto.getName());

            // University address

            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

//            university.setAddress(address1);

            universityRepository.save(university);

            return "University updated";
        }
        return "University not found";
    }

    // UPDATE
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    private String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University deleted";
    }
}
