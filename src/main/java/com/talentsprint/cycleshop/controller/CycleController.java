package com.talentsprint.cycleshop.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talentsprint.cycleshop.business.LoggedInUser;
import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.service.CycleService;


@Controller
@RequestMapping("/cycle")
public class CycleController {

    @Autowired
    private CycleService cycleService;

    @Autowired
    private LoggedInUser loggedInUser;
    
    /*
     * For example, /1/borrow?count=3 borrows 3 cycles of id 1.
     */
    @GetMapping("/{id}/borrow")
    public String borrowCycle(@PathVariable long id, @RequestParam(required=false, defaultValue="1") int count) {
        cycleService.borrowCycle(id, count);
        //just a comment
        return "redirect:/cycle/list"; //TODO: redirect to List handler
    }

    @GetMapping("/{id}/return")
    public String returnCycle(@PathVariable long id, @RequestParam(required=false, defaultValue="1") int count) {
        cycleService.returnCycle(id, count);
        return "redirect:/cycle/list"; //TODO: redirect to list handler
    }

    @GetMapping("/{id}/restock")
    public String restockCycle(@PathVariable long id, @RequestParam(required=false, defaultValue="1") int count) {
        cycleService.restockBy(id, count);
        return "redirect:/cycle/list";
    }
    
    @GetMapping("/list")
    public String listAvailableCyclesView() {
        return "cycleList";
    }

    @GetMapping("/list-data")
    @ResponseBody 	
    public List<Cycle> listAvailableCycles(Model model) {
//        if (this.loggedInUser.getLoggedInUser() == null) {
//            return "redirect:/loginpage";
//        	return Collections.emptyList();
//        }
//        var allCycles = cycleService.listAvailableCycles();
//        model.addAttribute("allCycles", allCycles);
//        return "cycleList";
        return cycleService.listAvailableCycles();
    }

    @GetMapping("/{id}")
    public String cycleDetail(@PathVariable long id, Model model) {
        var cycle = cycleService.findByIdOrThrow404(id);
        model.addAttribute("cycle", cycle);
        return "cycleDetail";
    }

}
