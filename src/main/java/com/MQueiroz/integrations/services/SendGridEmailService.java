package com.MQueiroz.integrations.services;

import com.MQueiroz.integrations.DTO.EmailDTO;
import com.MQueiroz.integrations.services.exceptions.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;



public class SendGridEmailService implements EmailService{

    private static Logger LOG = LoggerFactory.getLogger(SendGridEmailService.class);

    @Autowired
    private SendGrid sendGrid;

    public void sendEmail (EmailDTO email){
        Email from = new Email(email.getFromEmail(), email.getFromName());
        Email to = new Email(email.getTo());
        Content content = new Content(email.getContentType(), email.getBody());
        Mail mail = new Mail(from, email.getSubject(), to, content);

        Request request = new Request();
        try{
           request.setMethod(Method.POST);
           request.setEndpoint("mail/send");
           request.setBody(mail.build());
           LOG.info("Sending email to " + email.getTo());
           Response response = sendGrid.api(request);
           if(response.getStatusCode() >= 400 && response.getStatusCode() <= 500){
               LOG.error("Error sending email " + response.getBody());
               throw new EmailException(response.getBody());
           }
        }
        catch(IOException e){
            throw new EmailException(e.getMessage());
        }
    }
}
