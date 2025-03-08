package traineeship_app.services;

import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.TraineeshipPosition;

import java.util.List;

public interface ProfessorService {

    Professor retrieveProfile(String username);

    void SaveProfile(Professor professor);

    List<TraineeshipPosition> retrieveAssignedPositions(String username);

    void evaluateAssignedPosition(int positionId);

    void SaveEvaluation(int positionId, Evaluation evaluation);

}
