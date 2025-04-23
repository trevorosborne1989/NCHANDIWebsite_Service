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
import nchandi.spring.services.NCHANDIWebsite_Service.repositories.PanelRepository;

@Service
public class PanelService {

  @Autowired
  PanelRepository panelRepo;

  Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.PanelService");

  public List<Panel> getPanels() {
    List<Panel> panels = panelRepo.findAll();
    if (panels.size() > 0) {
      return panels;
    } else {
      throw new ResourceNotFoundException("No Records Found");
    }
  }

  public List<Panel> getOpenPanels() {
    List<Panel> panels = panelRepo.findByMarkAsMembersNeededTrueAndActiveTrue();
    if (panels.size() > 0) {
      return panels;
    } else {
      throw new ResourceNotFoundException("No Records Found");
    }
  }

  public List<Panel> getPanelsForNotifications(int weekOfMonth, String dayOfWeek) {
    List<Panel> panels = panelRepo.findByWeekOfMonthAndDayOfWeek(weekOfMonth, dayOfWeek);
    if (panels.size() > 0) {
      return panels;
    } else {
      throw new ResourceNotFoundException("No Records Found");
    }
  }

  public Optional<Panel> getPanelById(String panelId) {
    Optional<Panel> panel = panelRepo.findById(panelId);
    if (panel == null) {
      throw new ResourceNotFoundException("Panel with ID:" + panelId + " not found.");
    }
    return panel;
  }

  public Panel savePanel(Panel panel) {
    return panelRepo.save(panel);
  }

  public Panel updatePanel(String panelId, Panel panel) {
    Optional<Panel> existingPanel = panelRepo.findById(panelId);
    if (!panel.getId().equals(panelId)) {
      throw new DataIntegrityViolationException("Invalid ID was passed in the request body.");
    } else if (existingPanel == null) {
      throw new ResourceNotFoundException("Panel with ID:" + panelId + " not found.");
    }
    return panelRepo.save(panel);
  }

  public void deletePanel(String panelId) {
    Optional<Panel> panel = panelRepo.findById(panelId);
    if (panel == null) {
      throw new ResourceNotFoundException("Panel with ID:" + panelId + " not found.");
    }
    panelRepo.delete(panel.get());
  }
}
