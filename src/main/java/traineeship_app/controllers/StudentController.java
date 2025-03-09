package traineeship_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.services.StudentService;

@Controller  // responsible for processing HTTP requests, communicating with services and returning the correct views
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/student/dashboard")  // tells spring that this method should handle get-requests sent to the /student/dashboard URL
    public String getStudentDashboard() {
        return "student-dashboard"; // Redirects to the student dashboard view
    }

    @GetMapping("/profile")
    public String retrieveProfile(@RequestParam("username") String studentUsername, Model model) {
        // RequestParam binds the query parameter "username" to the method parameter "studentUsername"

        // Fetch student profile using the provided username
        Student student = studentService.retrieveProfile(studentUsername);

        // Add student to the model
        model.addAttribute("student", student);

        return "student-profile"; // Returns the profile view
    }

    @PostMapping("/profile/save")  // tells spring that this method should handle post-requests sent to the correct URL
    public String saveProfile(@ModelAttribute("student") Student student, Model theModel) {
        studentService.SaveProfile(student);
        theModel.addAttribute("student", student);
        return "redirect:/profile?username=" + student.getUsername();  // redirects back to the user's profile
    }

    @GetMapping("/logbook")
    public String fillLogbook(Model model) {
        model.addAttribute("position", new TraineeshipPosition()); // Adds an empty position object
        return "logbook-form"; // Returns the logbook form view
    }

    @PostMapping("/logbook/save")
    public String saveLogbook(@ModelAttribute("position") TraineeshipPosition position, Model theModel) {
        studentService.saveLogBook(position);
        return "redirect:/student/dashboard"; // Redirects to dashboard after saving logbook
    }

}
