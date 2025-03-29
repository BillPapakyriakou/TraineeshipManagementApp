package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.StudentMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;
import traineeship_app.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {


    private final ProfessorMapper professorMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfessorServiceImpl(ProfessorMapper professorMapper, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.professorMapper = professorMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Professor retrieveProfile(String username) {
        return null;
    }

    @Override
    public void SaveProfile(Professor professor) {
        // Ελέγχουμε αν το username υπάρχει ήδη
        if (userMapper.existsByUsername(professor.getUsername())) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        Professor professorCopy = new Professor();
        professorCopy.setUsername(professor.getUsername());
        professorCopy.setPassword(passwordEncoder.encode(professor.getPassword()));
        professorCopy.setRole(professor.getRole());
        professorCopy.setProfessorName(professor.getProfessorName());
        // Αποθήκευση στη βάση δεδομένων
        professorMapper.save(professorCopy);

    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        return List.of();
    }

    @Override
    public void evaluateAssignedPosition(int positionId) {

    }

    @Override
    public void SaveEvaluation(int positionId, Evaluation evaluation) {

    }
/*
    private final ProfessorMapper professorMapper;
    private final TraineeshipPositionsMapper traineeshipPositionMapper;
    @Autowired
    public ProfessorServiceImpl(ProfessorMapper professorMapper, TraineeshipPositionsMapper traineeshipPositionMapper) {
        this.professorMapper = professorMapper;
        this.traineeshipPositionMapper = traineeshipPositionMapper;
    }

    @Override
    public Professor retrieveProfile(String username) {
       // return professorMapper.findByUsername(username);
        return null;
    }

    @Override
    public void SaveProfile(Professor professor) {
        professorMapper.save(professor);
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        // Gets list of traineeship positions for the given username
       // List<TraineeshipPosition> positions = professorMapper.findByProfessorUsername(username);

        // Returns ArrayList of positions
      //  return new ArrayList<>(positions);
        return null;
    }

    @Override
    public void evaluateAssignedPosition(int positionId) {
        // Find the position by its ID (findById returns Optional)
        Optional<TraineeshipPosition> positionOpt = traineeshipPositionMapper.findById(positionId);

        if (positionOpt.isPresent()) {
            TraineeshipPosition position = positionOpt.get();  // Extract the position

            // Check if position already has an evaluation
            if (position.getEvaluations() == null || position.getEvaluations().isEmpty()) {
                // Create a new Evaluation object based on required data (we assume some data for motivation, efficiency, and effectiveness)
                Evaluation evaluation = new Evaluation(1, Evaluation.EvaluationType.PROJECT, 7, 8, 9); // Just an example
                position.getEvaluations().add(evaluation);  // Add the new evaluation to the position

                traineeshipPositionMapper.save(position);  // Save the updated position
            } else {
                throw new IllegalStateException("Position already evaluated");
            }

        } else {
            // If the position is not found, throw an exception
            throw new IllegalArgumentException("Position not found with ID: " + positionId);
        }
    }

    @Override
    public void SaveEvaluation(int positionId, Evaluation evaluation) {
        // Find the position by its ID (findById returns Optional)
        Optional<TraineeshipPosition> position = traineeshipPositionMapper.findById(positionId);

        // Check if position exists
        if (position.isPresent()) {
            // Add the new evaluation to the list of evaluations
            position.get().getEvaluations().add(evaluation);

            // Save  position with the updated evaluations list
            traineeshipPositionMapper.save(position.get());
        } else {
            throw new IllegalArgumentException("Position not found with ID: " + positionId);
        }
    }*/
}
