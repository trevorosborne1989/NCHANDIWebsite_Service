package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Panel;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.Pending;
import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PendingRepository;
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PeopleRepository;

@Service
public class PendingService {

	@Autowired
	PendingRepository pendingRepo;

	@Autowired
	PeopleRepository peopleRepo;

	@Autowired
	PeopleService peopleService;

	@Autowired
	PanelService panelService;

	Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.PendingService");

	public List<Pending> getPendings() {
		List<Pending> pendings = pendingRepo.findAll();
			return pendings;
	}

	public Optional<Pending> getPendingById(String pendingId) {
		Optional<Pending> pending = pendingRepo.findById(pendingId);
		if (pending == null) {
			throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
		}
		return pending;
	}

	public Pending savePending(Pending pending) {
		return pendingRepo.save(pending);
	}

	public Pending updatePending(String pendingId, Pending pending) {
		Optional<Pending> existingPending = pendingRepo.findById(pendingId);
		if (!pending.getId().equals(pendingId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		} else if (existingPending == null) {
			throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
		}
		return pendingRepo.save(pending);
	}

	public void deletePending(String pendingId) {
		Optional<Pending> pending = pendingRepo.findById(pendingId);
		if (pending == null) {
			throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
		}
		pendingRepo.delete(pending.get());
	}

	public void approvePending(String pendingId, Pending pending) throws CloneNotSupportedException {
		Optional<Pending> existingPending = pendingRepo.findById(pendingId);
		if (!pending.getId().equals(pendingId)) {
			throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
		} else if (existingPending == null) {
			throw new ResourceNotFoundException("Pending with ID:" + pendingId + " not found.");
		}

		People approvedPerson = new People();
		approvedPerson.setFirstName(pending.getFirstName());
		approvedPerson.setLastName(pending.getLastName());
		approvedPerson.setPhone(pending.getPhone());
		approvedPerson.setEmail(pending.getEmail());
		approvedPerson.setPreferredContactMethod(pending.getPreferredContactMethod());
		approvedPerson = peopleService.savePerson(approvedPerson);

		Panel approvedPanel = panelService.getPanelById(pending.getPanelId()).get();
		Panel updatedPanel = (Panel) approvedPanel.clone();
		if (updatedPanel.getPanelMember1() == null) {
			updatedPanel.setPanelMember1(approvedPerson);
		} else if (updatedPanel.getPanelMember2() == null) {
			updatedPanel.setPanelMember2(approvedPerson);
		} else if (updatedPanel.getPanelMember3() == null) {
			updatedPanel.setPanelMember3(approvedPerson);
		} else if (updatedPanel.getPanelMember4() == null) {
			updatedPanel.setPanelMember4(approvedPerson);
		} else if (updatedPanel.getPanelMember5() == null) {
			updatedPanel.setPanelMember5(approvedPerson);
		} else {
			throw new IllegalStateException("Panel with ID:" + approvedPanel.getId() + " is already at max for members (5)");
		}

		deletePending(pendingId);
		panelService.savePanel(updatedPanel);
	}
}
