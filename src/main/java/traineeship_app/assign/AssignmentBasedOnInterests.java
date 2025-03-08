package traineeship_app.assign;

import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {

    private TraineeshipPositionsMapper positionsMapper;
    private ProfessorMapper professorMapper;


    @Override
    public void assign(Integer positionId) {

        String topics = positionsMapper.getTopics(positionId);
        String bestProfessor = professorMapper.findProfessorByExpertise(topics);
        positionsMapper.assignSupervisor(positionId, bestProfessor);

    }
}
