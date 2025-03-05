package traineeship_app.services;

import traineeship_app.domainmodel.Company;
import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.TraineeshipPosition;

import java.util.ArrayList;

public interface CompanyService {

    Company retrieveProfile(String username);

    void SaveProfile(Company company);

    ArrayList<TraineeshipPosition> retrieveAvailablePositions(String username);

    void addPosition(String username, TraineeshipPosition position);

    ArrayList<TraineeshipPosition> retrieveAssignedPositions(String username);

    void evaluateAssignedPosition(int positionId);

    void saveEvaluation(int positionId, Evaluation evaluation);


}
