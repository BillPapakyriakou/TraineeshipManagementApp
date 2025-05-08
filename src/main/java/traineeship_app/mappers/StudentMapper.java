package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.Student;


import java.util.List;


//  StudentMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

@Repository
public interface StudentMapper extends JpaRepository<Student, Long>{
    // username (String) acts as the primary key

    // Custom query method to find user by username
    Student findByUsername(String username);

    Student findByAM(String AM);

    boolean existsStudentByAM(String am);

    boolean existsStudentByUsername(String username);

    List<Student> findByLookingForTraineeship(boolean lookingForTraineeship);

    String findInterestsByUsername(String username);

    //List<Student> findAllApplications();

    //String getInterests(String applicantUsername);
}
