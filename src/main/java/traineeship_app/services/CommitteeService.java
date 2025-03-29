package traineeship_app.services;

import traineeship_app.domainmodel.Committee;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;

import java.util.List;

public interface CommitteeService {

    List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy);

    List<Student> retrieveTraineeshipApplications();

    void assignPosition(int positionId, String studentUsername);

    void assignSupervisor(int positionId, String strategy);

    List<TraineeshipPosition> listAssignedTraineeships();

    void completeAssignedTraineeships(int positionId);

    void SaveProfile(Committee committee);

}
