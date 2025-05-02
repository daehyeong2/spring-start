package study.lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.lecture.domain.Member;
import study.lecture.service.MemberService;

@Controller
public class MemberController {
  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService){
    this.memberService = memberService;
  }

  @GetMapping("/signup")
  public String signupForm() {
    return "members/createMemberForm";
  }

  @PostMapping("/members/signup")
  public String create(MemberForm form){
    Member member = new Member();
    member.setName(form.getUsername());

    System.out.println("member = " + member.getName());
    memberService.join(member);

    return "redirect:/";
  }
}
