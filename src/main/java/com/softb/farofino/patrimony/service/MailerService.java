package com.softb.farofino.patrimony.service;

import com.softb.farofino.patrimony.model.QFSReleaseCalendar;
import com.softb.farofino.patrimony.model.QuarterlyFinancialStatement;
import com.softb.system.errorhandler.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Responsável pelo envio de e-mails sobre determinados pontos do processo.
 */
@Service
public class MailerService {
    @Autowired private JavaMailSender mailSender;

    public void sendMailQFSUpdated(List<QuarterlyFinancialStatement> qfsProcessed) {
        SimpleMailMessage message = new SimpleMailMessage();

        String textToMail = "";
        for (QuarterlyFinancialStatement qfs: qfsProcessed){
            textToMail += "\n"+ qfs.getCompany().getCode() +" :: "+ qfs.getCompany().getName() +" :: "+ qfs.getQuarter();
        }
        message.setText("Quarter Financial Statement Released for: " + textToMail);

        message.setTo("erikpaulo@ciandt.com");
        message.setFrom("erik.lacerda@gmail.com");

        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
    }

    public void sendMailCalendarUpdate(List<QFSReleaseCalendar> calendar){
        SimpleMailMessage message = new SimpleMailMessage();

        String textToMail = "";
        for (QFSReleaseCalendar calItem: calendar){
            textToMail += "\n"+ calItem.getCompany().getName() +" - "+ calItem.getQuarter() +" - "+ calItem.getDate();
        }
        message.setText("Release Calendar Updates: " + textToMail);

        message.setTo("erikpaulo@ciandt.com");
        message.setFrom("erik.lacerda@gmail.com");

        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
    }
}


