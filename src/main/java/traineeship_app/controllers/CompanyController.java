package traineeship_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import traineeship_app.domainmodel.Company;
import traineeship_app.domainmodel.Evaluation;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.services.CompanyService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public String registerCompany(
            @ModelAttribute("company") Company company,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);
            redirectAttributes.addFlashAttribute("company", company);
            return "redirect:/users/register?role=COMPANY";
        }
        try {
            companyService.SaveProfile(company);
            redirectAttributes.addFlashAttribute("success", "Company user registration successful!");
            return "redirect:/users/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            redirectAttributes.addFlashAttribute("company",company);
            return "redirect:/users/register?role=COMPANY";
        }

    }









/*
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/company/dashboard")
    public String getCompanyDashboard() {
        return "company/dashboard";
    }

    @GetMapping("/profile")
    public String retrieveProfile(@RequestParam("username") String companyUsername, Model model) {
        // Fetch company profile using the provided username
        Company company = companyService.retrieveProfile(companyUsername);

        // Add professor to the model
        model.addAttribute("company", company);

        return "company/profile"; // Returns the profile view
    }

    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute("profile") Company company, Model model) {
        companyService.SaveProfile(company);
        model.addAttribute("company", company);
        return "redirect:/profile?username=" + company.getUsername();  // redirects back to the user's profile
    }

    @GetMapping("/company/available-positions")
    public String listAvailablePositions(@RequestParam("username") String companyUsername, Model model) {
        List<TraineeshipPosition> positions = companyService.retrieveAvailablePositions(companyUsername);

        model.addAttribute("positions", positions);

        return "company/available-positions";
    }

    @GetMapping("/position-form")
    public String showPositionForm(Model model) {
        model.addAttribute("position", new TraineeshipPosition()); // Empty position object for form binding
        return "company/position-form";
    }

    @PostMapping("/position/save")
    public String savePosition(@RequestParam("username") String companyUsername,
                               @ModelAttribute("position") TraineeshipPosition position,
                               Model model) {
        companyService.addPosition(companyUsername, position);
        return "redirect:/company/available-positions?username=" + companyUsername;
    }

    @GetMapping("/company/assigned-positions")
    public String listAssignedPositions(@RequestParam("username") String companyUsername, Model model) {
        List<TraineeshipPosition> positions = companyService.retrieveAssignedPositions(companyUsername);

        model.addAttribute("positions", positions);

        return "company/assigned-positions";
    }

    @GetMapping("/company/evaluate")
    public String evaluateAssignedTraineeship(@RequestParam("positionId") int positionId, Model model) {
        companyService.evaluateAssignedPosition(positionId);
        model.addAttribute("evaluation", new Evaluation()); // Empty evaluation object for form binding
        return "company/evaluate-traineeship";
    }

    @PostMapping("/evaluation/save")
    public String saveEvaluation(@RequestParam("positionId") int positionId,
                                 @ModelAttribute("evaluation") Evaluation evaluation,
                                 Model model) {
        companyService.saveEvaluation(positionId, evaluation);
        return "redirect:/company/assigned-positions";
    }

    @PostMapping("/position/delete")
    public String deletePosition(@RequestParam("positionId") int positionId) {
        companyService.deletePosition(positionId);
        return "redirect:/company/available-positions";
    }
*/
}
