package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.TraineeshipPosition;

import java.util.List;


//  ProfessorMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

@Repository
public interface ProfessorMapper extends JpaRepository<Professor, String>{
    // username (String) acts as the primary key

    // Custom query method to find user by username
    Professor findByUsername(String username);

    List<TraineeshipPosition> findByProfessorUsername(String username);
}
