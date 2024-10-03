package nchandi.spring.services.NCHANDIWebsite_Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.twilio.type.PhoneNumber;
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

  @Value("${twilio.flow}")
  private String twilioFlow;

  Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.ScheduledTasks");

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  //  For testing.
  // @Scheduled(cron = ("0 30 7 * * *" ))
  public void reportCurrentTime() {
    logger.info("The time is now {}", dateFormat.format(new Date()));
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
    Calendar ca1 = Calendar.getInstance();
    TimeZone time_zone = TimeZone.getTimeZone("PST");
    ca1.setTimeZone(time_zone);
    ca1.setMinimalDaysInFirstWeek(1);
    ca1.add(Calendar.DAY_OF_WEEK, 1);
    int week = ca1.get(Calendar.WEEK_OF_MONTH);
    int day = ca1.get(Calendar.DAY_OF_WEEK);
    String dayString = convertDayOfWeek(day);
    List<Panel> panels = panelService.getPanelsForNotifications(week, dayString);

    //  Collect phone numbers of volunteers for each panel
    ArrayList<String> phoneNumbers = new ArrayList<String>();
    for (Panel panel : panels) {
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
    }

    // Test if service grabs the right numbers. Check against data.sql file with panels to see if the right panels where selected and correct panel members to be notified.
    System.out.println("The numbers to be notified: ");
    for (Panel panel : panels) {
      System.out.println("week of month: " + panel.getWeekOfMonth() + ", day of week: " + panel.getDayOfWeek()+ ", panel id: " + panel.getId());
    }
    System.out.println(phoneNumbers);

    // API call to our Twilio Flow service for notifications for each number
    // To Check logs and see if flow is working go here: https://console.twilio.com/us1/monitor/logs/sms and here: https://console.twilio.com/us1/monitor/logs/debugger/errors
    // for (String phonenumber : phoneNumbers) {
    //   Twilio.init(twilioSID, twilioToken);
    //   Execution execution = Execution
    //     .creator(
    //       twilioFlow,
    //       new com.twilio.type.PhoneNumber("+" + phonenumber),
    //       new com.twilio.type.PhoneNumber(twilioPhone))
    //     .create();
    //   System.out.println(execution.getSid());
    // }



  }

  // @Scheduled(cron = ("1 * * * * *" ))
  public void requestPanelFeebacks() {
    logger.info("Requesting panel feedback...");


  }

}
