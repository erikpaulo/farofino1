package com.softb.farofino.patrimony.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softb.farofino.patrimony.model.CompanyUnderCover;
import com.softb.farofino.patrimony.model.QFSReleaseCalendar;
import com.softb.farofino.patrimony.repository.CompanyUnderCoverRepository;
import com.softb.farofino.patrimony.repository.QFSResultCalendarRepository;
import com.softb.farofino.utils.Constants;
import com.softb.system.errorhandler.exception.SystemException;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Recupera algumas informações do Infomoney
 */
@Service
public class InfomoneyScraper {
    protected static final String INFOMONEY = "https://www.infomoney.com.br/webservices/services.asmx/GetSchedule?hash=cfcd208495d565ef66e7dff9f98764da";

    private static final Logger log = LoggerFactory.getLogger(InfomoneyScraper.class);

    public Map<String, JsonNode> getReleaseCalendar() {
        Map<String, JsonNode> infoCompanies = new HashMap<>();

        try {
            String response = Jsoup.connect(INFOMONEY)
                    .timeout(30 * 1000)
                    .ignoreContentType(true)
                    .method(Connection.Method.POST)
                    .requestBody("{\"Reference\": \"219\", \"from\": \"\", \"iDisplayLength\": 300, \"iDisplayStart\": 0, \"iSortCol_0\": 1, \"itens\": \"300\", \"sEcho\": 2, \"sSearch\": \"\", \"sSortDir_0\": \"asc\", \"to\": \"\"}")
                    .header("Sec-Fetch-Mode", "cors")
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .header("Origin", "https://www.infomoney.com.br")
                    .execute()
                    .body();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(response);
            JsonNode infoCalendar = mapper.readTree(actualObj.get("d").asText());
            infoCompanies = iteratorToMap(infoCalendar.get("aaData").elements());

        } catch (HttpStatusException h){
            log.error(h.getMessage(), h);
        } catch (IOException e1) {
            log.error("Connection error, trying again in 1 minute", e1);
            try {
                TimeUnit.MINUTES.sleep(1);
            }  catch (InterruptedException e2){
                throw new SystemException(e1.getMessage());
            }
        }

        return infoCompanies;
    }

    private Map iteratorToMap(Iterator<JsonNode> is){
        Map<String, JsonNode> map = new HashMap<>();

        while(is.hasNext()){
            JsonNode i = is.next();

            map.put(i.get(0).asText(), i);
        }

        return map;
    }
}

