package uz.dev.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dev.appjparelationships.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {


}
