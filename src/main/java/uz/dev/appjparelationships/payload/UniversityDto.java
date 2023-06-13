package uz.dev.appjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDto {// Ma'lumotlar tashish uchun xizmat qiladi
    private String name;
    private String city;
    private String district;
    private String street;
}
