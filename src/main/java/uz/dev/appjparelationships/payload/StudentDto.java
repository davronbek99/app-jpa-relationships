package uz.dev.appjparelationships.payload;

import jakarta.persistence.Column;
import lombok.Data;
import uz.dev.appjparelationships.entity.Subject;

import java.util.List;

@Data
public class StudentDto {

    private String firstName;
    private String lastName;
    private Integer groupId;
    private String city;
    private String district;

    private String street;

    private List<Subject> subjectList;

}
