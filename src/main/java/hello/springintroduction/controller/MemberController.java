package hello.springintroduction.controller;

import hello.springintroduction.domain.Member;
import hello.springintroduction.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired//
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")//url창에다 치는건 get 맵핑
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")//데이터를 폼같은데에 넣어서 전달할때 포스트맵핑
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈화면으로 돌아가게 하는것.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
