package gov.srs.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import gov.srs.spring.services.NCHANDIWebsite_Service.domain.Member;
import gov.srs.spring.services.NCHANDIWebsite_Service.repositories.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepo;
	
	Logger logger = LoggerFactory.getLogger("gov.srs.spring.services.NCHANDIWebsite_Service.services.MemberService");
	
	public List<Member> getMembers() {
		
	   List<Member> members = memberRepo.findAll();
	   
		if (members.size() > 0) {
			return members;
		} else {
			throw new ResourceNotFoundException("No Records Found");
		} 
	}

	public Optional<Member> getMemberById(String memberId) {
		
		Optional<Member> member = memberRepo.findById(memberId);
		
		if (member == null) {
			throw new ResourceNotFoundException("Member with ID:" + memberId + " not found.");
		}
		
		return member;
	}

	public Member saveMember (Member member) {
	  
	  return memberRepo.save(member);
	}

	public Member updateMember(String memberId, Member member) {
	  
	  Optional<Member> existingMember = memberRepo.findById(memberId);
	  
	  if (!member.getId().equals(memberId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
	  } else if (existingMember == null) {
		throw new ResourceNotFoundException("Member with ID:" + memberId + " not found.");
	  }
	  
	  return memberRepo.save(member);
	}

	public void deleteMember(String memberId) {
	  
	  Optional<Member> member = memberRepo.findById(memberId);
	  
	  if (member == null) {
		  throw new ResourceNotFoundException("Member with ID:" + memberId + " not found.");
	  }
	  
	  memberRepo.delete(member.get());
	}
}
