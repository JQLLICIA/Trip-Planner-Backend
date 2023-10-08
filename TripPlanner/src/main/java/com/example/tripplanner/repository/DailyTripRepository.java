package com.example.tripplanner.repository;

import com.example.tripplanner.entity.DailyTripEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface DailyTripRepository extends ListCrudRepository<DailyTripEntity, Integer>{
    /*
    * public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Long> {

    List<CustomerEntity> findByFirstName(String firstName);


    List<CustomerEntity> findByLastName(String lastName);


    CustomerEntity findByEmail(String email);


    @Modifying
    @Query("UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE id = :id")
    void updateNameById(long id, String firstName, String lastName);


    @Modifying
    @Query("UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE email = :email")
    void updateNameByEmail(String email, String firstName, String lastName);
}*/
}
