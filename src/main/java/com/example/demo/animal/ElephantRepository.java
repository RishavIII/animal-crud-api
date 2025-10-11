package com.example.demo.animal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ElephantRepository extends JpaRepository<Elephant, Long> {

    List<Elephant> getElephantsByGender(String gender);

    @Query(value = "select * from elephants e where e.age >= ?1", nativeQuery = true)
    List<Elephant> getAdultElephants(double age);

    @Query(value = "select * from elephants e where e.name like %?1% ", nativeQuery = true)
    List<Elephant> getElephantsByName(String name);
}