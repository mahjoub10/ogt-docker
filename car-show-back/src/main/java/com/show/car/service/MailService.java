package com.show.car.service;

import com.show.car.config.CarProperties;
import com.show.car.dto.UserDTO;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * This service is responsible for sending emails .
 */
@Service
public class MailService {

    private static Logger log = LoggerFactory.getLogger(MailService.class);


    private SpringTemplateEngine templateEngine ;

    private JavaMailSender javaMailSender ;

    private LocalMessageResource messageSource ;

    private CarProperties properties ;

    public MailService(SpringTemplateEngine templateEngine, JavaMailSender javaMailSender, LocalMessageResource messageSource, CarProperties properties) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.properties = properties;
    }

    @Async("carAsyncExecutor")
    public void sendActivationEMail(UserDTO user) {

        log.info("Send welcome Email to new user : '{} {}' having email address : '{}'",user.getFirstName(),
                user.getLastName(), user.getEmail());

        Locale locale = Locale.forLanguageTag(user.getLangKey());
        Context context = new Context(locale);
        context.setVariable("user", user);
        String content = templateEngine.process("welcomeInscriptionUser", context);
        String subject = messageSource.getMessage("email.welcome.subject", null) ;
        sendEmail(user.getEmail(), subject, content, false, true);

    }



    @Async("fastAsyncExecutor")
    private void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {

        log.info("Sending  email to user  '{}' with multipart : {}",to,isMultipart);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to.trim());
            message.setFrom(properties.getEmail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Email has been sent  to User '{}'", to);
        } catch (MessagingException e) {
            log.error("E-mail could not be sent to user '{}', exception is: {}", to, e);
        }
    }

}
