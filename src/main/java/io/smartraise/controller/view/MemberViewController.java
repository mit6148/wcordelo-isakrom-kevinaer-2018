package io.smartraise.controller.view;

import io.smartraise.model.accounts.Member;
import io.smartraise.service.MemberService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberViewController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/member/{id}")
    public String getMember(@PathVariable("id") String id, Model model){
        Member member = memberService.get(id);
        model.addAttribute("profile", member);
        model.addAttribute("orgs", organizationService.getFromMember(member));
        return "profile";
    }

    @GetMapping("/member/{id}/edit")
    public ModelAndView getEditMember(@PathVariable("id") String id){
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("user", memberService.get(id));
        return mav;
    }

    @RequestMapping(value = "/member/{id}/edit", method = RequestMethod.PUT)
    public ModelAndView loginProcess(@ModelAttribute("user") Member member) {
        memberService.update(member);
        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("user", memberService.get(member.getUsername()));
        return mav;
    }
}
