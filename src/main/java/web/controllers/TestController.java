package web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.TestModel;
import web.servie.TestService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/testing")
public class TestController {
    private final TestService testService;

    @GetMapping("/")
    public String main(Model model) {
        List<TestModel> list = testService.findAll();
        model.addAttribute("test", list);
        return "/test/create-test";
    }

    @GetMapping("/get/all")
    public String findAll(Model model) {
        model.addAttribute("prom", testService.findAll());
        return "/test/view-test";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        TestModel test = new TestModel();
        model.addAttribute("test", test);
        return "/test/create-test";
    }

    @PostMapping("/create")
    public String createProduct(TestModel test) {
        testService.saveTest(test);
        return "redirect:/testing/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TestModel test = testService.getTestById(id);
        model.addAttribute("test", test);
        return "/test/update-test";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("test") TestModel test) {
        testService.update(id, test);
        return "redirect:/testing/get/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        testService.deleteTest(id);
        return "redirect:/testing/get/all";
    }
}
