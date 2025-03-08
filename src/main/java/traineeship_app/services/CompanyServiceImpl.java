package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Company;
import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.CompanyMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;


import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private TraineeshipPositionsMapper traineeshipPositionMapper;

    @Override
    public Company retrieveProfile(String username) {
        return companyMapper.findByUsername(username);
    }

    @Override
    public void SaveProfile(Company company) {
        companyMapper.save(company);
    }

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String username) {
        // Gets list of traineeship positions for the given username
        List<TraineeshipPosition> positions = traineeshipPositionMapper.findByCompanyUsername(username);

        // Returns ArrayList of positions
        return new ArrayList<>(positions);
    }

    @Override
    public void addPosition(String username, TraineeshipPosition position) {

        Company company = companyMapper.findByUsername(username);

        if (company != null) {
            position.setCompany(company);  // Set company on position
            traineeshipPositionMapper.save(position);  // Save the new position
        } else {
            throw new IllegalArgumentException("Company not found with username: " + username);
        }
    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {

        List<TraineeshipPosition> positions = traineeshipPositionMapper.findByCompanyUsername(username);
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
    public void saveEvaluation(int positionId, Evaluation evaluation) {
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
