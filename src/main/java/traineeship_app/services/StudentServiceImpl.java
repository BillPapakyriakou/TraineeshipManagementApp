package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.StudentMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;
import traineeship_app.mappers.UserMapper;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TraineeshipPositionsMapper traineeshipPositionMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper,
                              UserMapper userMapper,
                              PasswordEncoder passwordEncoder,
                              TraineeshipPositionsMapper traineeshipPositionMapper) {
        this.studentMapper = studentMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.traineeshipPositionMapper = traineeshipPositionMapper;
    }

    @Override
    public void SaveProfile(Student student) {
        if (userMapper.existsByUsername(student.getUsername())) {
            throw new RuntimeException("username already exists");
        }
        if (studentMapper.existsStudentByAM(student.getAM())) {
            throw new RuntimeException("AM already exists");
        }

        Student studentCopy = new Student();
        studentCopy.setUsername(student.getUsername());
        studentCopy.setPassword(passwordEncoder.encode(student.getPassword()));
        studentCopy.setRole(student.getRole());
        studentCopy.setStudentName(student.getStudentName());
        studentCopy.setAM(student.getAM());
        studentCopy.setAvgGrade(student.getAvgGrade());
        studentCopy.setPreferredLocation(student.getPreferredLocation());
        studentCopy.setLookingForTraineeship(student.isLookingForTraineeship());
        studentCopy.setSkills(student.getSkills());
        studentCopy.setInterests(student.getInterests());

        studentMapper.save(studentCopy);
    }

    @Override
    public Student retrieveProfile(String studentUsername) {
        return studentMapper.findByUsername(studentUsername);
    }

    @Override
    public void saveLogBook(TraineeshipPosition position) {
        Optional<TraineeshipPosition> existingPositionOpt = traineeshipPositionMapper.findById(position.getId());

        if (existingPositionOpt.isPresent()) {
            TraineeshipPosition existingPosition = existingPositionOpt.get();
            existingPosition.setStudentLogbook(position.getStudentLogbook());
            traineeshipPositionMapper.save(existingPosition);
        } else {
            throw new IllegalArgumentException("Position not found with ID: " + position.getId());
        }
    }
}
