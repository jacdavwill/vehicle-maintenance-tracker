package com.example.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.example.exceptions.InternalServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class EmailService {

    public void sendEmail(String to, String subjectText, String bodyText) throws InternalServiceException {
        try {
            AmazonSimpleEmailService emailService =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_WEST_2).build();
            Content subject = new Content(subjectText);
            Body body = new Body();
            body.setText(new Content(bodyText));
            Message message = new Message(subject, body);

            List<String> addresses = new ArrayList<>();
            addresses.add(to);
            Destination destination = new Destination(addresses);

            SendEmailRequest sendEmailRequest = new SendEmailRequest("jasonmcannon3@gmail.com", destination, message);
            emailService.sendEmail(sendEmailRequest);
        } catch (Exception e) {
            log.error("Error sending reset link", e);
            throw new InternalServiceException("Error sending reset link");
        }
    }

}
