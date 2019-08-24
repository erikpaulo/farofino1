package com.softb.farofino.patrimony.service;

import com.softb.farofino.patrimony.model.Fundamentus;
import com.softb.farofino.patrimony.model.QFSReleaseCalendar;
import com.softb.farofino.patrimony.model.QuarterlyFinancialStatement;
import com.softb.system.errorhandler.exception.SystemException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;


/**
 * Atualiza os preços de todas as ações registradas com posições ativas
 */
@Service
public class FundamentusScraper {
    protected static final String FUNDAMENTUS = "http://www.fundamentus.com.br/detalhes.php?papel=";

    private static final Logger log = LoggerFactory.getLogger(FundamentusScraper.class);

    public QuarterlyFinancialStatement getReleaseNumbers(QFSReleaseCalendar calItem){
        QuarterlyFinancialStatement newQFS = new QuarterlyFinancialStatement();

        try {
            Document doc = Jsoup.connect(FUNDAMENTUS + calItem.getCompany().getCode()).get();

            Elements sections = doc.select("table");
            Fundamentus data = new Fundamentus(sections);

            newQFS.setCompany(calItem.getCompany());
            newQFS.setQuarter(calItem.getQuarter());
            newQFS.setMarketValue(data.getMarketValue());
            newQFS.setReleaseDate(data.getReleaseDate());
            newQFS.setSharesQuantity(data.getSharesQuantity());

            newQFS.setPL(data.getIndPL());
            newQFS.setLPa(data.getIndLPA());
            newQFS.setPVp(data.getIndPVP());
            newQFS.setVpA(data.getIndVPA());
            newQFS.setPEBIT(data.getIndPEBIT());
            newQFS.setMargemBruta(data.getIndMargemBruta());
            newQFS.setPSR(data.getIndPSR());
            newQFS.setMargemEBIT(data.getIndMargemEBIT());
            newQFS.setPAtivos(data.getIndPAtivos());
            newQFS.setMargemLiquida(data.getIndMargemLiquida());
            newQFS.setPCapitalGiro(data.getIndPCapGiro());
            newQFS.setPEBIT(data.getIndPEBITAtivo());
            newQFS.setPAtivoCirculanteLiq(data.getIndPAtivoCirculanteLiq());
            newQFS.setROIC(data.getIndROIC());
            newQFS.setDividendYeld(data.getIndDividendYeld());
            newQFS.setROE(data.getIndROE());
            newQFS.setEvEBIT(data.getIndEVEBIT());
            newQFS.setLiquidezCorrente(data.getIndLiquidezCorrente());
            newQFS.setGiroAtivos(data.getIndGiroAtivos());
            newQFS.setDbPL(data.getIndDBPL());
            newQFS.setCrescimentoReceita5a(data.getIndCrescimentoRecita5a());

            newQFS.setAtivos(data.getBsAtivos());
            newQFS.setDividaBruta(data.getBsDividaBruta());
            newQFS.setCaixa(data.getBsDisponibilidade());
            newQFS.setDividaLiquida(data.getBsDividaLiquida());
            newQFS.setAtivoCirculante(data.getBsAtivoCirculante());
            newQFS.setPatrimonioLiquido(data.getBsPatrimonioLiquido());

            newQFS.setReceitaLiquida12M(data.getResReceitaLiquida12M());
            newQFS.setReceitaLiquida3M(data.getResReceitaLiquida3M());
            newQFS.setEBIT12M(data.getResEBIT12M());
            newQFS.setEBIT3M(data.getResEBIT3M());
            newQFS.setLucroLiquido12M(data.getResLucroLiquido12M());
            newQFS.setLucroLiquido3M(data.getResLucroLiquido3M());

        } catch (IOException e) {
            log.error("Connection error, trying again in 1 minute", e);
            try {
                TimeUnit.MINUTES.sleep(1);
            }  catch (InterruptedException e2){
                throw new SystemException(e.getMessage());
            }
        } catch (ParseException e) {
            throw new SystemException(e.getMessage());
        }

        return newQFS;
    }
}

