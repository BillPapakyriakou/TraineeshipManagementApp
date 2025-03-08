package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import traineeship_app.assign.SupervisorAssignmentFactory;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.StudentMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;
import traineeship_app.search.PositionsSearchStrategy;

import java.util.List;

@Service
public class CommitteeServiceImpl implements CommitteeService {

    @Autowired
    PositionsSearchStrategy positionsSearchFactory;

    @Autowired
    SupervisorAssignmentFactory supervisorAssignmentFactory;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;


    @Override
    public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy) {
        return null;
    }

    @Override
    public List<Student> retrieveTraineeshipApplications() {
        return null;
    }

    @Override
    public void assignPosition(int positionId, String studentUsername) {

    }

    @Override
    public void assignSupervisor(int positionId, String strategy) {

    }

    @Override
    public List<TraineeshipPosition> listAssignedTraineeships() {
        return null;
    }

    @Override
    public void completeAssignedTraineeships(int positionId) {

    }
}
