package traineeship_app.assign;

import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

    private TraineeshipPositionsMapper positionsMapper;
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
