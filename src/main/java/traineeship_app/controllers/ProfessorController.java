package traineeship_app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import traineeship_app.domainmodel.*;
import traineeship_app.services.ProfessorService;
import org.springframework.ui.Model;


import java.util.List;

@Controller
@RequestMapping("/professors")
public class ProfessorController{

    private final ProfessorService professorService;

    @PostMapping("/register")
    public String registerProfessor(
            @ModelAttribute("professor") Professor professor,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.professor", result);
            redirectAttributes.addFlashAttribute("professor", professor);
            return "redirect:/users/register?role=PROFESSOR";
        }

        try {
            professorService.SaveProfile(professor);
            redirectAttributes.addFlashAttribute("success", "Professor registration successful!");
            return "redirect:/users/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("professor", professor);
            return "redirect:/users/register?role=PROFESSOR";
        }



    }


    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @GetMapping("/professor/dashboard")
    public String getProfessorDashboard() {
        return "professor/dashboard";
    }

    @GetMapping("/profile")
    public String retrieveProfile(@RequestParam("username") String professorUsername, Model model) {
        // Fetch professor profile using the provided username
        Professor professor = professorService.retrieveProfile(professorUsername);

        // Add professor to the model
        model.addAttribute("professor", professor);

        return "professor/profile"; // Returns the profile view
    }

    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute("profile") Professor professor, Model theModel) {
        professorService.SaveProfile(professor);
        theModel.addAttribute("professor", professor);
        return "redirect:/profile?username=" + professor.getUsername();  // redirects back to the user's profile
    }

    @GetMapping("/professor/assigned-traineeships")
    public String listAssignedTraineeships(@RequestParam("username") String professorUsername, Model model) {
        // Retrieve list of traineeships assigned to the professor
        List<TraineeshipPosition> positions = professorService.retrieveAssignedPositions(professorUsername);

        // Add the positions to the model
        model.addAttribute("positions", positions);

        return "professor/assigned-positions"; // Return the view displaying assigned traineeships
    }

    @GetMapping("/professor/evaluate")
    public String evaluateAssignedTraineeship(@RequestParam("username") String professorUsername,
                                              @RequestParam("positionId") int positionId,
                                              Model model) {
        // Retrieve all the assigned positions for the professor
        List<TraineeshipPosition> positions = professorService.retrieveAssignedPositions(professorUsername);

        // Find the position with the given positionId
        TraineeshipPosition position = null;
        for (TraineeshipPosition p : positions) {
            if (p.getId() == positionId) {
                position = p;
                break; // Exit the loop once the position is found
            }
        }

        if (position == null) {
            // Handle the case where the position is not found
            model.addAttribute("error", "Position not found.");
            return "error"; // Redirect to an error page or display an error message
        }

        // Add the found position to the model for evaluation
        model.addAttribute("position", position);
        model.addAttribute("evaluation", new Evaluation()); // Empty Evaluation object to bind the form

        return "professor/evaluate-traineeship"; // Returns the evaluation form view
    }

    @PostMapping("/professor/evaluate/save")
    public String saveEvaluation(@RequestParam("positionId") int positionId,
                                 @ModelAttribute("evaluation") Evaluation evaluation,
                                 Model model) {
        // Save the evaluation for the given position
        professorService.SaveEvaluation(positionId, evaluation);

        return "redirect:/professor/dashboard"; // Redirects to the professor's dashboard after saving the evaluation
    }

}
