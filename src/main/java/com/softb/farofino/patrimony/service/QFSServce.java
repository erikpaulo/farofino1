package com.softb.farofino.patrimony.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.softb.farofino.patrimony.model.CompanyUnderCover;
import com.softb.farofino.patrimony.model.QFSReleaseCalendar;
import com.softb.farofino.patrimony.model.QuarterlyFinancialStatement;
import com.softb.farofino.patrimony.repository.CompanyUnderCoverRepository;
import com.softb.farofino.patrimony.repository.QFSResultCalendarRepository;
import com.softb.farofino.patrimony.repository.QuarterlyFinancialStatementRepository;
import com.softb.farofino.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class QFSServce {
    private static final Logger log = LoggerFactory.getLogger(QFSServce.class);

    @Autowired private FundamentusScraper fundamentusScraper;
    @Autowired private InfomoneyScraper infomoneyScraper;
    @Autowired private MailerService mailerService;

    @Autowired private QFSResultCalendarRepository qfsResultCalendarRepository;
    @Autowired private QuarterlyFinancialStatementRepository quarterlyFinancialStatementRepository;
    @Autowired private CompanyUnderCoverRepository companyUnderCoverRepository;

    @PostConstruct
    public void updateBenchmarksOnStartUp(){
        updateQFS();
        updateReleaseCalendar();
    }

    @Scheduled(cron = "0 0 3 * * MON-FRI", zone = Constants.TIMEZONE_PTBR)
    @Transactional
    public void updateQFS(){
        List<QuarterlyFinancialStatement> QFSProcessed = new ArrayList<>();

        List<QFSReleaseCalendar> calendar = qfsResultCalendarRepository.findByNotProcessedUntilDate(new Date());
        for (QFSReleaseCalendar calItem: calendar){
            QuarterlyFinancialStatement newQFS = fundamentusScraper.getReleaseNumbers(calItem);
            QFSProcessed.add(newQFS);
            calItem.setProcessed(true);

            quarterlyFinancialStatementRepository.save(newQFS);
            qfsResultCalendarRepository.save(calItem);
        }

        mailerService.sendMailQFSUpdated(QFSProcessed);
    }

    @Scheduled(cron = "0 0 3 * * SAT", zone = Constants.TIMEZONE_PTBR)
    @Transactional
    public void updateReleaseCalendar(){
        List<QFSReleaseCalendar> calendar = new ArrayList<>();

        Map<String, JsonNode> infoCompanies = infomoneyScraper.getReleaseCalendar();
        List<CompanyUnderCover> companiesUnderCover = companyUnderCoverRepository.findAll();

        for (CompanyUnderCover companyUnderCover:  companiesUnderCover){

            if ( infoCompanies.containsKey(companyUnderCover.getNickname()) ){
                QFSReleaseCalendar newCal = new QFSReleaseCalendar(companyUnderCover, infoCompanies.get(companyUnderCover.getNickname()));
                QFSReleaseCalendar currentCal = qfsResultCalendarRepository.findByQuarter(companyUnderCover.getId(), newCal.getQuarter());

                if (currentCal != null){
                    currentCal.setDate(newCal.getDate());
                    qfsResultCalendarRepository.save(currentCal);
                    calendar.add(currentCal);
                } else {
                    qfsResultCalendarRepository.save(newCal);
                    calendar.add(newCal);
                }

            }
        }

        mailerService.sendMailCalendarUpdate(calendar);
    }
}


