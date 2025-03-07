package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.TraineeshipPosition;

import java.util.List;


//  TraineeshipPositionMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

@Repository
public interface TraineeshipPositionMapper extends JpaRepository<TraineeshipPosition, Integer> {
    // id (int) acts as the primary key

    // Custom query method to find TraineeshipPosition by username
    TraineeshipPosition findByUsername(String username);

    List<TraineeshipPosition> findByCompanyUsername(String username);


}
