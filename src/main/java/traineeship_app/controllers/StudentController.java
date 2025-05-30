package traineeship_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/home")
    public String studentHome(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("role", "ROLE_STUDENT");
        return "students/home";  // This maps to templates/students/home.html
    }

    @PostMapping("/register")
    public String registerStudent(
            @ModelAttribute("student") Student student,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.student", result);
            redirectAttributes.addFlashAttribute("student", student);
            return "redirect:/users/register?role=STUDENT";
        }

        try {
            studentService.SaveProfile(student);
            redirectAttributes.addFlashAttribute("success", "Student registration successful!");
            return "redirect:/users/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("student", student);
            return "redirect:/users/register?role=STUDENT";
        }
    }


    @GetMapping("/dashboard")  // tells spring that this method should handle get-requests sent to the /student/dashboard URL
    public String getStudentDashboard(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "students/dashboard"; // Redirects to the student dashboard view
    }

    @GetMapping("/profile")
    public String retrieveProfile(@RequestParam("username") String studentUsername, Model model) {
        // RequestParam binds the query parameter "username" to the method parameter "studentUsername"

        // Fetch student profile using the provided username
        Student student = studentService.retrieveProfile(studentUsername);

        // Add student to the model
        model.addAttribute("student", student);

        return "students/profile"; // Returns the profile view
    }
    /*
    @PostMapping("/profile/save")  // tells spring that this method should handle post-requests sent to the correct URL
    public String saveProfile(@ModelAttribute("student") Student student, Model theModel) {
        studentService.SaveProfile(student);
        theModel.addAttribute("student", student);
        return "redirect:/students/profile?username=" + student.getUsername();  // redirects back to the user's profile
    }
    */
    @PostMapping("/profile/save")  // tells spring that this method should handle post-requests sent to the correct URL
    public String updateProfile(@ModelAttribute("student") Student student, Model theModel) {
        studentService.UpdateProfile(student);
        theModel.addAttribute("student", student);
        return "redirect:/students/profile?username=" + student.getUsername();  // redirects back to the user's profile
    }

    @GetMapping("/logbook")
    public String fillLogbook(Model model) {
        model.addAttribute("position", new TraineeshipPosition()); // Adds an empty position object
        return "/students/logbook"; // Returns the logbook form view
    }

    @PostMapping("/logbook/save")
    public String saveLogbook(@ModelAttribute("position") TraineeshipPosition position, Model theModel) {
        studentService.saveLogBook(position);
        return "redirect:/dashboard"; // Redirects to dashboard after saving logbook
    }

}
