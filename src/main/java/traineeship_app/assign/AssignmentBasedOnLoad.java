package traineeship_app.assign;

import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {

    private TraineeshipPositionsMapper positionsMapper;
    private ProfessorMapper professorMapper;

    @Override
    public void assign(Integer positionId) {
        String leastBusyProfessor = professorMapper.findLeastBusyProfessor();
        positionsMapper.assignSupervisor(positionId, leastBusyProfessor);
    }
}
