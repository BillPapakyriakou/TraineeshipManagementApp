package traineeship_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    private final TraineeshipPositionsMapper traineeshipPositionsMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, UserMapper userMapper, PasswordEncoder passwordEncoder, TraineeshipPositionsMapper traineeshipPositionsMapper) {
        this.studentMapper = studentMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.traineeshipPositionsMapper = traineeshipPositionsMapper;
    }


    @Override
    public void SaveProfile(Student student) {
        // Ελέγχουμε αν το username υπάρχει ήδη
        if (userMapper.existsByUsername(student.getUsername())) {
            throw new DataIntegrityViolationException("Username already exists");
        }

        // Ελέγχουμε αν το AM υπάρχει ήδη
        if (studentMapper.existsStudentByAM(student.getAM())) {
            throw new DataIntegrityViolationException("AM already exists");
        }

        // Δημιουργία αντιγράφου του student
        Student studentCopy = new Student();
        studentCopy.setUsername(student.getUsername());
        studentCopy.setPassword(passwordEncoder.encode(student.getPassword()));  // Κρυπτογράφηση του password
        studentCopy.setRole(student.getRole());
        studentCopy.setStudentName(student.getStudentName());
        studentCopy.setAM(student.getAM());
        studentCopy.setAvgGrade(student.getAvgGrade());
        studentCopy.setPreferredLocation(student.getPreferredLocation());
        studentCopy.setLookingForTraineeship(student.isLookingForTraineeship());
        studentCopy.setSkills(student.getSkills());
        studentCopy.setInterests(student.getInterests());

        // Αποθήκευση του φοιτητή στη βάση δεδομένων
        studentMapper.save(studentCopy);
    }




    @Override
    public void UpdateProfile(Student student) {
        if (student.getUsername() == null) {
            throw new RuntimeException("No username provided in form.");
        }
        Student existingStudent = studentMapper.findByUsername(student.getUsername());

        if (existingStudent == null) {
            throw new RuntimeException("Student not found.");
        }


        // Do not touch username or password
        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setAM(student.getAM());
        existingStudent.setAvgGrade(student.getAvgGrade());
        existingStudent.setPreferredLocation(student.getPreferredLocation());
        existingStudent.setLookingForTraineeship(student.isLookingForTraineeship());
        existingStudent.setSkills(student.getSkills());
        existingStudent.setInterests(student.getInterests());

        studentMapper.save(existingStudent);
    }



    @Override
    public Student retrieveProfile(String studentUsername) {
        return studentMapper.findByUsername(studentUsername);
    }

    @Override
    public void saveLogBook(TraineeshipPosition position) {

        Optional<TraineeshipPosition> existingPositionOpt = traineeshipPositionsMapper.findById(position.getId());

        if (existingPositionOpt.isPresent()) {
            TraineeshipPosition existingPosition = existingPositionOpt.get();  // Retrieve position from DB

            existingPosition.setStudentLogbook(position.getStudentLogbook());  // Update logbook content

            traineeshipPositionsMapper.save(existingPosition);  // Save updated position back to DB
        } else {
            throw new IllegalArgumentException("Position not found with ID: " + position.getId());
        }

    }
}

