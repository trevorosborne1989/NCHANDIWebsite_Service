package nchandi.spring.services.NCHANDIWebsite_Service.services;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import nchandi.spring.services.NCHANDIWebsite_Service.dto.ContactForm;
import nchandi.spring.services.NCHANDIWebsite_Service.dto.LiteratureRequest;

@Service
public class EmailService {

  Logger logger = LoggerFactory.getLogger("nchandi.spring.services.NCHANDIWebsite_Service.services.PeopleService");


  @Value("${zoho.username}")
  private String zohoUsername;

  @Value("${zoho.password}")
  private String zohoPassword;

  public LiteratureRequest emailLiteratureRequest (LiteratureRequest literatureRequest) throws MessagingException {

    Properties prop = new Properties();
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", "smtppro.zoho.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.ssl.trust", "smtppro.zoho.com");

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(zohoUsername, zohoPassword);
      }
    });

    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress("northcountyhandi@gmail.com"));
    message.setRecipients(
      Message.RecipientType.TO, InternetAddress.parse("northcountyhandi@gmail.com"));
    message.setSubject("NCHANDI New Literature Request");

    String msg =
    """
    <br/>
    ~A new literature request has been made.<br/><br/><br/>

    <b>First Name:</b> %s<br/>
    <b>Last Name:</b> %s<br/><br/>

    <b>H&I Commitment:</b> %s<br/><br/>

    <b>Facility:</b> %s<br/><br/>

    <b>Email:</b> %s<br/>
    <b>Phone Number:</b> %s<br/><br/>

    ---------------------------<br/><br/><br/>
    """.formatted(literatureRequest.getFirstName(), literatureRequest.getLastName(), literatureRequest.getCommitment(), 
    literatureRequest.getFacility(), literatureRequest.getEmail(), literatureRequest.getphone());

    String request = "";

    if (literatureRequest.getLivingSoberQty() > 0) {
      request = request + """
          <b>Living Sober Qty:</b> %d<br/><br/>
          """.formatted(literatureRequest.getLivingSoberQty());
    }
    if (literatureRequest.getStepsAndTraditions12x12Qty() > 0) {
      request = request + """
          <b>12 Steps and 12 Traditions Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getStepsAndTraditions12x12Qty());
    }
    if (literatureRequest.getAaPaperbackQty() > 0) {
      request = request + """
          <b>Alcoholics Anonymous paperback Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getAaPaperbackQty());
    }
    if (literatureRequest.getAaPocketSizeQty() > 0) {
      request = request + """
          <b>Alcoholics Anonymous pocket size Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getAaPocketSizeQty());
    }
    if (literatureRequest.getGrapevineQty() > 0) {
      request = request + """
          <b>Grapevine Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getGrapevineQty());
    }
    if (literatureRequest.getLaVinaQty() > 0) {
      request = request + """
          <b>La Vina Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getLaVinaQty());
    }
    if (literatureRequest.getNewcomerPacketsQty() > 0) {
      request = request + """
          <b>New Comer Packets Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getNewcomerPacketsQty());
    }
    if (literatureRequest.getLiteratureRackWithPamphletsQty() > 0) {
      request = request + """
          <b>Literature Rack with Pamphlets Qty:</b>  %d<br/><br/>
          """.formatted(literatureRequest.getLiteratureRackWithPamphletsQty());
    }
    if (literatureRequest.getOtherQty() > 0) {
      request = request + """
          <b>Other Qty:</b>  %d<br/>
          """.formatted(literatureRequest.getOtherQty());
    }
    if (literatureRequest.getComments() != null) {
      request = request + """
          <b>Special Instructions / Comments:</b>  %s<br/><br/>
          """.formatted(literatureRequest.getComments());
    }
    request = request + """
        <br/>
        ---------------------------<br/><br/><br/>
        ~End of request.
        """;;

    msg = msg + request;

    MimeBodyPart mimeBodyPart = new MimeBodyPart();
    mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(mimeBodyPart);

    message.setContent(multipart);

    Transport.send(message);

		return literatureRequest;
	}

  public ContactForm emailContactForm (ContactForm contactForm) throws MessagingException {

    Properties prop = new Properties();
    prop.put("mail.transport.protocol", "smtp");
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", "smtppro.zoho.com");
    prop.put("mail.smtp.port", "587");
    prop.put("mail.smtp.ssl.trust", "smtppro.zoho.com");

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(zohoUsername, zohoPassword);
      }
    });

    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress("technology@nchandi.org"));
    message.setRecipients(
      Message.RecipientType.TO, InternetAddress.parse("northcountyhandi@gmail.com"));
    message.setSubject("NCHANDI Contact Request");

    String msg =
    """
    <br/>
    ~A contact request has been made by the following individual via the nchandi.org website.<br/><br/><br/>

    ---------------------------<br/><br/><br/>

    <b>First Name:</b> %s<br/><br/>
    <b>Last Name:</b> %s<br/><br/>
    <b>Email:</b> %s<br/><br/>
    <b>Phone Number:</b> %s<br/><br/>
    <b>Message:</b> %s<br/><br/><br/>

    ---------------------------<br/><br/><br/>
    """.formatted(contactForm.getFirstName(), contactForm.getLastName(),
    contactForm.getEmail(), contactForm.getPhone(), contactForm.getMessage());

    MimeBodyPart mimeBodyPart = new MimeBodyPart();
    mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(mimeBodyPart);

    message.setContent(multipart);

    Transport.send(message);

		return contactForm;
	}
}
