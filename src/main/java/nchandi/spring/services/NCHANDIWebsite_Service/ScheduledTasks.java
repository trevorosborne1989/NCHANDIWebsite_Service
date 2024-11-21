package nchandi.spring.services.NCHANDIWebsite_Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.studio.v2.flow.Execution;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Panel;
import nchandi.spring.services.NCHANDIWebsite_Service.services.PanelService;

@Component
public class ScheduledTasks {

  @Autowired
	PanelService panelService;

  @Value("${twilio.sid}")
  private String twilioSID;

  @Value("${twilio.token}")
  private String twilioToken;

  @Value("${twilio.phone}")
  private String twilioPhone;

  @Value("${twilio.notification.flow}")
  private String twilioNotificationFlow;

  @Value("${twilio.feedback.flow}")
  private String twilioFeedbackFlow;

  Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.ScheduledTasks");

   //  Collect phone numbers of volunteers for each panel
  private ArrayList<String> collectPhoneNumbers(Panel panel) {
    ArrayList<String> phoneNumbers = new ArrayList<String>();
    if (panel.getPanelCoordinator() != null) {
      phoneNumbers.add(panel.getPanelCoordinator().getPhone());
    }
    if (panel.getPanelLeader() != null) {
      phoneNumbers.add(panel.getPanelLeader().getPhone());
    }
    if (panel.getPanelMember1() != null) {
      phoneNumbers.add(panel.getPanelMember1().getPhone());
    }
    if (panel.getPanelMember2() != null) {
      phoneNumbers.add(panel.getPanelMember2().getPhone());
    }
    if (panel.getPanelMember3() != null) {
      phoneNumbers.add(panel.getPanelMember3().getPhone());
    }
    if (panel.getPanelMember4() != null) {
      phoneNumbers.add(panel.getPanelMember4().getPhone());
    }
    if (panel.getPanelMember5() != null) {
      phoneNumbers.add(panel.getPanelMember5().getPhone());
    }
    return phoneNumbers;
  }

  private String convertDayOfWeek(int dayOfWeek) {
    switch(dayOfWeek) {
      case 1:
        return "Sunday";
      case 2:
        return "Monday";
      case 3:
        return "Tuesday";
      case 4:
        return "Wednesday";
      case 5:
        return "Thursday";
      case 6:
        return "Friday";
      case 7:
        return "Saturday";
      default:
        return "";
    }
  }

  @Scheduled(cron = ("1 * * * * *" ))
  public void sendPanelNotifications() {
    logger.info("Sending panel notifications...");

    // Get all panels that will occure tommorow
    Calendar ca1endar = Calendar.getInstance();
    TimeZone time_zone = TimeZone.getTimeZone("PST");
    ca1endar.setTimeZone(time_zone);
    ca1endar.setMinimalDaysInFirstWeek(1);
    ca1endar.add(Calendar.DAY_OF_WEEK, 1);
    int week = ca1endar.get(Calendar.WEEK_OF_MONTH);
    int day = ca1endar.get(Calendar.DAY_OF_WEEK);
    String dayString = convertDayOfWeek(day);
    List<Panel> panels = panelService.getPanelsForNotifications(week, dayString);

    // for (Panel panel : panels) {
    //   ArrayList<String> phoneNumbers = collectPhoneNumbers(panel);
    //   // API call to our Twilio Flow service for notifications for each number using a customized flow.
    //   for (String phonenumber : phoneNumbers) {
    //     Twilio.init(twilioSID, twilioToken);
    //     System.out.println(twilioSID + "----" + twilioToken);
    //     Execution.creator(
    //       twilioNotificationFlow,
    //       new com.twilio.type.PhoneNumber("+" + phonenumber),
    //       new com.twilio.type.PhoneNumber(twilioPhone))
    //     .setParameters(new HashMap<String, Object>() {
    //       {
    //         put("eventTime", panel.getEventTime()); put("facility", panel.getFacility().getName());
    //         put("panelCoordinatorName", panel.getPanelCoordinator().getFirstName() + " " + panel.getPanelCoordinator().getLastName());
    //         put("panelCoordinatorPhone", panel.getPanelCoordinator().getPhone().substring(1));
    //       }
    //     })
    //     .create();
    //   }
    // }

    // Test if service grabs the right panels.
    System.out.println("The Panels for tommorrow... \n ");
    for (Panel panel : panels) {
      System.out.println("week of month: " + panel.getWeekOfMonth() + ", day of week: " + panel.getDayOfWeek()+ ", panel id: " + panel.getId() + "\n");
    }

  }

  // @Scheduled(cron = ("1 * * * * *" ))
  // public void requestPanelFeeback() {
  //   logger.info("Requesting panel feedback...");

  //   // Get all panels that occured yesterday
  //   Calendar ca1endar = Calendar.getInstance();
  //   TimeZone time_zone = TimeZone.getTimeZone("PST");
  //   ca1endar.setTimeZone(time_zone);
  //   ca1endar.setMinimalDaysInFirstWeek(1);
  //   ca1endar.add(Calendar.DAY_OF_WEEK, -1);
  //   int week = ca1endar.get(Calendar.WEEK_OF_MONTH);
  //   int day = ca1endar.get(Calendar.DAY_OF_WEEK);
  //   String dayString = convertDayOfWeek(day);
  //   List<Panel> panels = panelService.getPanelsForNotifications(week, dayString);

  //   for (Panel panel : panels) {
  //     ArrayList<String> phoneNumbers = collectPhoneNumbers(panel);
  //     // API call to our Twilio Flow service for panel feedback for yesterday's meetings using custom flow
  //     for (String phonenumber : phoneNumbers) {
  //       Twilio.init(twilioSID, twilioToken);
  //       System.out.println(twilioSID + "----" + twilioToken);
  //       Execution.creator(
  //         twilioFeedbackFlow,
  //         new com.twilio.type.PhoneNumber("+" + phonenumber),
  //         new com.twilio.type.PhoneNumber(twilioPhone))
  //       .setParameters(new HashMap<String, Object>() {
  //         {
  //           put("eventTime", panel.getEventTime()); put("facility", panel.getFacility().getName());
  //         }
  //       })
  //       .create();
  //     }
  //   }
  // }
}
