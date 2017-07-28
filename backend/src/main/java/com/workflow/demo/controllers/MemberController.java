package com.workflow.demo.controllers;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.demo.repository.MemberRepository;

import com.workflow.demo.exception.CustomerNotFoundException;
import com.workflow.demo.exception.InvalidCustomerRequestException;
import com.workflow.demo.models.Member;

@RestController
public class MemberController {

	private MemberRepository memberRepository;
	
	@Autowired
    public void setCustomerRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/members", method = RequestMethod.POST)
	public @ResponseBody Member createMember(            
            @RequestParam(value="name", required=true) String name) { 
			/*@RequestBody Member temp) {*/
        	Member mem = new Member(name);
        	
        	memberRepository.save(mem);
            return mem;               
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/members/{memberId}", method = RequestMethod.GET)
	public Member getMember(@PathVariable("memberId") Long memberId) {
		
		/* validate customer Id parameter */
		if (null==memberId) {
			throw new InvalidCustomerRequestException();
		}
		
		Member mem = memberRepository.findOne(memberId);
		
		if(null==mem){
			throw new CustomerNotFoundException();
		}
		
		return mem;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/members", method = RequestMethod.GET)
	public List<Member> getMembers() {
		
		return (List<Member>) memberRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/members/{memberId}", method = RequestMethod.DELETE)
	public void removeCustomer(@PathVariable("memberId") Long memberId, HttpServletResponse httpResponse) {

		if(memberRepository.exists(memberId)){
			Member member = memberRepository.findOne(memberId);
			memberRepository.delete(member);	
		}
		
		httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
