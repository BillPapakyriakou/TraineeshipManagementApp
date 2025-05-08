package traineeship_app.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import traineeship_app.domainmodel.TraineeshipPosition;

import java.util.List;


//  TraineeshipPositionMapper is now a Jpa repository, which comes with
//  built-in methods like save(), findById(), findAll(), etc

@Repository
public interface TraineeshipPositionsMapper extends JpaRepository<TraineeshipPosition, Integer> {
    // id (int) acts as the primary key

    // Custom query method to find TraineeshipPosition by username
    TraineeshipPosition findByStudentUsername(String username);

    //void update(TraineeshipPosition position);

    List<TraineeshipPosition> findByCompanyUsername(String username);

    List<TraineeshipPosition> findBySupervisorUsername(String username);

    List<TraineeshipPosition> findByStudentInterests(String interests);

    List<TraineeshipPosition> findByIsAssignedTrue();

    String findTopicsById(Integer id);

    //String getTopics(Integer positionId);
}
