package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.StudentMapper;
import traineeship_app.mappers.TraineeshipPositionMapper;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TraineeshipPositionMapper traineeshipPositionMapper;

    @Override
    public void SaveProfile(Student student) {
        studentMapper.save((student));
    }

    @Override
    public Student retrieveProfile(String studentUsername) {
        return studentMapper.findByUsername(studentUsername);
    }

    @Override
    public void saveLogBook(TraineeshipPosition position) {

        Optional<TraineeshipPosition> existingPositionOpt = traineeshipPositionMapper.findById(position.getId());

        if (existingPositionOpt.isPresent()) {
            TraineeshipPosition existingPosition = existingPositionOpt.get();  // Retrieve position from DB

            existingPosition.setStudentLogbook(position.getStudentLogbook());  // Update logbook content

            traineeshipPositionMapper.save(existingPosition);  // Save updated position back to DB
        } else {
            throw new IllegalArgumentException("Position not found with ID: " + position.getId());
        }
    }
}
