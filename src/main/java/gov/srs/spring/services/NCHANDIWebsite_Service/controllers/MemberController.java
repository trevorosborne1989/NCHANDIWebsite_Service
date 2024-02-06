package gov.srs.spring.services.NCHANDIWebsite_Service.controllers;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Member;
import gov.srs.spring.services.NCHANDIWebsite_Service.services.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public List<Member> getAllMembers(HttpServletRequest request) {
		return memberService.getMembers();
	}

	@RequestMapping(value = "/members/{memberId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Member> getMembersById(
			@PathVariable String memberId,
			HttpServletRequest request) {
		return memberService.getMemberById(memberId);
	}

	@RequestMapping(value = "/members", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Member saveMember(
			@RequestBody Member member,
			HttpServletRequest request) {
		return memberService.saveMember(member);
	}

	@RequestMapping(value = "/members/{memberId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Member updateMember(
			@RequestBody Member member,
			@PathVariable String memberId,
			HttpServletRequest request) {
		return memberService.updateMember(memberId, member);
	}

	@RequestMapping(value = "/members/{memberId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteMember(
			@PathVariable String memberId,
			HttpServletRequest request) {
		memberService.deleteMember(memberId);
	}
}
