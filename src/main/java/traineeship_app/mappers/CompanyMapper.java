package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.Company;


//  CompanyMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

@Repository
public interface CompanyMapper extends JpaRepository<Company, String> {
    // username (String) acts as the primary key

    // Custom query method to find Company by username
    Company findByUsername(String username);



}
