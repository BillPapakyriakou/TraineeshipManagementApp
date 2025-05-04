package traineeship_app.services.assign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

@Component
public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;
    @Autowired
    private ProfessorMapper professorMapper;


    @Override
    public void assign(Integer positionId) {
        TraineeshipPosition position = positionsMapper.findById(positionId)
            .orElseThrow(() -> new IllegalArgumentException("Position not found"));

        // Find the least busy professor
        String leastBusyProfessor = professorMapper.findLeastBusyProfessor();

        // Assign the supervisor
        Professor supervisor = professorMapper.findByUsername(leastBusyProfessor);
        if (supervisor == null) {
            throw new IllegalArgumentException("Supervisor not found");
        }

        position.setSupervisor(supervisor);  // Assign in entity
        positionsMapper.update(position);  // Save changes to DB
    }
}