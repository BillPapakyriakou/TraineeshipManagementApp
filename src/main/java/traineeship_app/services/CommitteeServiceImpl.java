package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Committee;
import traineeship_app.domainmodel.Professor;
import traineeship_app.mappers.*;
import traineeship_app.services.assign.SupervisorAssignmentFactory;
import traineeship_app.services.assign.SupervisorAssignmentStrategy;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.services.search.PositionsSearchStrategy;
import traineeship_app.services.search.PositionsSearchFactory;


import java.util.List;
import java.util.Optional;

@Service
public class CommitteeServiceImpl implements CommitteeService {

    private final CommitteeMapper committeeMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final StudentMapper studentMapper;
    private final TraineeshipPositionsMapper positionsMapper;
    private final PositionsSearchFactory positionsSearchFactory;
    private final SupervisorAssignmentFactory supervisorAssignmentFactory;

    // Only one constructor to inject all dependencies
    @Autowired
    public CommitteeServiceImpl(CommitteeMapper committeeMapper,
                                UserMapper userMapper,
                                PasswordEncoder passwordEncoder,
                                StudentMapper studentMapper,
                                TraineeshipPositionsMapper positionsMapper,
                                PositionsSearchFactory positionsSearchFactory,
                                SupervisorAssignmentFactory supervisorAssignmentFactory) {
        this.committeeMapper = committeeMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.studentMapper = studentMapper;
        this.positionsMapper = positionsMapper;
        this.positionsSearchFactory = positionsSearchFactory;
        this.supervisorAssignmentFactory = supervisorAssignmentFactory;
    }

    @Override
    public void SaveProfile(Committee committee) {
        if (userMapper.existsByUsername(committee.getUsername())) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        Committee committeeCopy = new Committee();
        committeeCopy.setUsername(committee.getUsername());
        committeeCopy.setPassword(passwordEncoder.encode(committee.getPassword()));
        committeeCopy.setRole(committee.getRole());
        committeeCopy.setCommitteeMemberName(committee.getCommitteeMemberName());

        committeeMapper.save(committeeCopy);
    }

    @Override
    public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy) {
        // Get the search strategy
        PositionsSearchStrategy searchStrategy = positionsSearchFactory.create(strategy);
        // Use the strategy to search for positions
        return searchStrategy.search(applicantUsername);
    }

    @Override
    public List<Student> retrieveTraineeshipApplications() {
        return studentMapper.findAllApplications();
    }

    @Override
    public void assignPosition(int positionId, String studentUsername) {
        TraineeshipPosition position = positionsMapper.findById(positionId).orElse(null);
        Student student = studentMapper.findByUsername(studentUsername);

        if (position == null) {
            throw new IllegalArgumentException("Traineeship position not found.");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }
        if (position.isAssigned()) {
            throw new IllegalStateException("Traineeship position is already assigned.");
        }

        // Assign the student to the position
        position.setStudent(student);
        position.setAssigned(true);
        positionsMapper.update(position);
    }

    @Override
    public void assignSupervisor(int positionId, String strategy) {
        // Ensure the position exists
        Optional<TraineeshipPosition> position = positionsMapper.findById(positionId);
        if (position == null) {
            throw new IllegalArgumentException("Traineeship position not found.");
        }

        // Use the factory to create a strategy and assign a supervisor
        SupervisorAssignmentStrategy assignmentStrategy = supervisorAssignmentFactory.create(strategy);
        assignmentStrategy.assign(positionId);
    }

    @Override
    public List<TraineeshipPosition> listAssignedTraineeships() {
        return positionsMapper.findAssignedPositions();
    }

    @Override
    public void completeAssignedTraineeships(int positionId) {
        // Retrieve the position
        TraineeshipPosition position = positionsMapper.findById(positionId).orElse(null);

        if (position == null) {
            throw new IllegalArgumentException("Traineeship position not found.");
        }
        if (!position.isAssigned()) {
            throw new IllegalStateException("Traineeship position is not assigned.");
        }

        // Mark the position as completed (assumption: setting passFailGrade to true means completion)
        position.setPassFailGrade(true);
        positionsMapper.update(position);
    }
}
