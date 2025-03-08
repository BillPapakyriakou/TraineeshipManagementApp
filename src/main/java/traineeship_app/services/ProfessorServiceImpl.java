package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorMapper professorMapper;
    @Autowired
    private TraineeshipPositionsMapper traineeshipPositionMapper;

    @Override
    public Professor retrieveProfile(String username) {
        return professorMapper.findByUsername(username);
    }

    @Override
    public void SaveProfile(Professor professor) {
        professorMapper.save(professor);
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        // Gets list of traineeship positions for the given username
        List<TraineeshipPosition> positions = professorMapper.findByProfessorUsername(username);

        // Returns ArrayList of positions
        return new ArrayList<>(positions);
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
    }
}
