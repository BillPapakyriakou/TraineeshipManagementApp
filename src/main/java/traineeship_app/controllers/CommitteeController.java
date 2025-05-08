package traineeship_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import traineeship_app.domainmodel.*;
import traineeship_app.services.CommitteeService;

@Controller
@RequestMapping("/committees")
public class CommitteeController {


    private final CommitteeService committeeService;


    @Autowired
    public CommitteeController(CommitteeService committeeService ){
        this.committeeService = committeeService;

    }

    @GetMapping("/home")
    public String commiteeHome(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("role", "ROLE_COMMITTEE");
        return "committee/home";  // This maps to templates/committee/home.html
    }

    @PostMapping("/register")
    public String registerCommittee(
            @ModelAttribute("committee_member") Committee committee,
            BindingResult result,
            RedirectAttributes redirectAttributes) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.committee", result);
            redirectAttributes.addFlashAttribute("committee_member", committee);
            return "redirect:/users/register?role=COMMITTEE_MEMBER";
        }

        try {
            committeeService.SaveProfile(committee);
            redirectAttributes.addFlashAttribute("success", "COMMITTEE registration successful!");
            return "redirect:/users/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("committee_member", committee);
            return "redirect:/users/register?role=COMMITTEE_MEMBER";
        }
    }



    /*
    private final CommitteeService committeeService;

    @Autowired
    public CommitteeController(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

    @GetMapping("/committee/dashboard")
    public String getCommitteeDashboard() {
        return "committee/applications";
    }

    @GetMapping("/committee/traineeship-applications")
    public String listTraineeshipApplications(Model model) {
        List<Student> applicants = committeeService.retrieveTraineeshipApplications();

        model.addAttribute("applicants", applicants);

        return "committee/applications";
    }

    @GetMapping("/committee/find-positions")
    public String findPositions(@RequestParam("studentUsername") String studentUsername,
                                @RequestParam("strategy") String strategy, Model model) {
        List<TraineeshipPosition> positions = committeeService.retrievePositionsForApplicant(studentUsername, strategy);
        model.addAttribute("positions", positions);
        return "committee/positions";
    }

    @PostMapping("/committee/assign-position")
    public String assignPosition(@RequestParam("positionId") int positionId,
                                 @RequestParam("studentUsername") String studentUsername,
                                 Model model) {
        committeeService.assignPosition(positionId, studentUsername);
        return "redirect:/committee/assigned-positions";
    }

    @PostMapping("/committee/assign-supervisor")
    public String assignSupervisor(@RequestParam("positionId") int positionId,
                                   @RequestParam("strategy") String strategy,
                                   Model model) {
        committeeService.assignSupervisor(positionId, strategy);
        return "redirect:/committee/assigned-positions";
    }

    @GetMapping("/committee/assigned-traineeships")
    public String listAssignedTraineeships(Model model) {
        List<TraineeshipPosition> assignedPositions = committeeService.listAssignedTraineeships();
        model.addAttribute("assignedPositions", assignedPositions);
        return "committee/assigned-positions";
    }

    @PostMapping("/committee/complete-traineeship")
    public String completeAssignedTraineeships(@RequestParam("positionId") int positionId, Model model) {
        committeeService.completeAssignedTraineeships(positionId);
        return "redirect:/committee/assigned-positions";
    }
    *
     */

}
