package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Company;
import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.CompanyMapper;
import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;
import traineeship_app.mappers.UserMapper;


import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.companyMapper = companyMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Company retrieveProfile(String username) {
        return null;
    }



    @Override
    public void SaveProfile(Company company) {
        // Ελέγχουμε αν το username υπάρχει ήδη
        if (userMapper.existsByUsername(company.getUsername())) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        Company companyCopy = new Company();
        companyCopy.setUsername(company.getUsername());
        companyCopy.setPassword(passwordEncoder.encode(company.getPassword()));
        companyCopy.setRole(company.getRole());
        companyCopy.setCompanyName(company.getCompanyName());
        companyCopy.setCompanyLocation(company.getCompanyLocation());

        companyMapper.save(companyCopy);
    }

    @Override
    public List<TraineeshipPosition> retrieveAvailablePositions(String username) {
        return List.of();
    }

    @Override
    public void addPosition(String username, TraineeshipPosition position) {

    }

    @Override
    public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
        return List.of();
    }

    @Override
    public void evaluateAssignedPosition(int positionId) {

    }

    @Override
    public void saveEvaluation(int positionId, Evaluation evaluation) {

    }

    @Override
    public void deletePosition(int positionId) {

    }
/*
    private final CompanyMapper companyMapper;
    private final TraineeshipPositionsMapper traineeshipPositionMapper;
    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper, TraineeshipPositionsMapper traineeshipPositionMapper) {
        this.companyMapper = companyMapper;
        this.traineeshipPositionMapper = traineeshipPositionMapper;
    }

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

    @Override
    public void deletePosition(int positionId) {
        // Check if the position exists
        if (traineeshipPositionMapper.existsById(positionId)) {
            traineeshipPositionMapper.deleteById(positionId);
        } else {
            throw new IllegalArgumentException("Position with ID " + positionId + " not found.");
        }
    }
*/

}
