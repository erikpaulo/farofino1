package com.softb.farofino.patrimony.model;

import com.softb.system.repository.BaseEntity;
import lombok.Data;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe que representa o patrimônio total do user
 * @author Erik Lacerda 
 *
 */
@Data
public class Fundamentus extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date releaseDate;
	private Long marketValue;
	private Elements valueSectionItems;
	private Element valueSection;
	private Long sharesQuantity;

	private Elements indicatorItems;
	private Double indPL;
	private Double indLPA;
	private Double indPVP;
	private Double indVPA;
	private Double indPEBIT;
	private Double indMargemBruta;
	private Double indPSR;
	private Double indMargemEBIT;
	private Double indPAtivos;
	private Double indMargemLiquida;
	private Double indPCapGiro;
	private Double indPEBITAtivo;
	private Double indPAtivoCirculanteLiq;
	private Double indROIC;
	private Double indDividendYeld;
	private Double indROE;
	private Double indEVEBIT;
	private Double indLiquidezCorrente;
	private Double indGiroAtivos;
	private Double indDBPL;
	private Double indCrescimentoRecita5a;

	private Long bsAtivos;
	private Long bsDividaBruta;
	private Long bsDisponibilidade;
	private Long bsDividaLiquida;
	private Long bsAtivoCirculante;
	private Long bsPatrimonioLiquido;

	private Long resReceitaLiquida12M;
	private Long resReceitaLiquida3M;
	private Long resEBIT12M;
	private Long resEBIT3M;
	private Long resLucroLiquido12M;
	private Long resLucroLiquido3M;


	public Fundamentus (Elements sections) throws ParseException {
		NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));

		//            Element generalSection = sections.get(0);
		//            Elements generalSecItems = generalSection.select("td");
		//            Double price = format.parse(generalSecItems.get(3).child(0).text()).doubleValue();
		//            String type = generalSecItems.get(5).child(0).text();
		//            String name = generalSecItems.get(9).child(0).text();
		//            Double min52Weeks = format.parse(generalSecItems.get(11).child(0).text()).doubleValue();
		//            Double max52Weeks = format.parse(generalSecItems.get(15).child(0).text()).doubleValue();
		//            String sector = generalSecItems.get(13).child(0).text();
		//            String subSector = generalSecItems.get(17).child(0).text();
		//            Long averageVolNegotiated = format.parse(generalSecItems.get(19).child(0).text().replace(".", "")).longValue();

		valueSection = sections.get(1);
		valueSectionItems = valueSection.select("td");
		marketValue = format.parse(valueSectionItems.get(1).child(0).text().replace(".", "")).longValue();
		releaseDate = new SimpleDateFormat("dd/MM/yyyy").parse(valueSectionItems.get(3).child(0).text());
		sharesQuantity = format.parse(valueSectionItems.get(7).child(0).text().replace(".", "")).longValue();

		Element variationSection = sections.get(2);
		Elements variationItems = variationSection.select("td");
		//            Double varday = format.parse(variationItems.get(3).child(0).text()).doubleValue();
		//            Double varMonth = format.parse(variationItems.get(9).child(0).text()).doubleValue();
		//            Double var30Days = format.parse(variationItems.get(15).child(0).text()).doubleValue();
		//            Double var12Months = format.parse(variationItems.get(21).child(0).text()).doubleValue();
		//            Double varThisYear = format.parse(variationItems.get(27).child(0).text()).doubleValue();
		//            Double varLastYear = format.parse(variationItems.get(33).child(0).text()).doubleValue();
		//            Double varPrevYear1 = format.parse(variationItems.get(39).child(0).text()).doubleValue();
		//            Double varPrevYear2 = format.parse(variationItems.get(45).child(0).text()).doubleValue();
		//            Double varPrevYear3 = format.parse(variationItems.get(51).child(0).text()).doubleValue();
		//            Double varPrevYear4 = format.parse(variationItems.get(57).child(0).text()).doubleValue();
		//            Double varPrevYe4 = format.parse(variationItems.get(57).child(0).text()).doubleValue();

		indicatorItems = variationSection.select("td");
		indPL = format.parse(variationItems.get(5).child(0).text()).doubleValue();
		indLPA = format.parse(variationItems.get(7).child(0).text()).doubleValue();
		indPVP = format.parse(variationItems.get(11).child(0).text()).doubleValue();
		indVPA = format.parse(variationItems.get(13).child(0).text()).doubleValue();
		indPEBIT = format.parse(variationItems.get(17).child(0).text()).doubleValue();
		indMargemBruta = format.parse(variationItems.get(19).child(0).text()).doubleValue();
		indPSR = format.parse(variationItems.get(23).child(0).text()).doubleValue();
		indMargemEBIT = format.parse(variationItems.get(25).child(0).text()).doubleValue();
		indPAtivos = format.parse(variationItems.get(29).child(0).text()).doubleValue();
		indMargemLiquida = format.parse(variationItems.get(31).child(0).text()).doubleValue();
		indPCapGiro = format.parse(variationItems.get(35).child(0).text()).doubleValue();
		indPEBITAtivo = format.parse(variationItems.get(37).child(0).text()).doubleValue();
		indPAtivoCirculanteLiq = format.parse(variationItems.get(41).child(0).text()).doubleValue();
		indROIC = format.parse(variationItems.get(43).child(0).text()).doubleValue();
		indDividendYeld = format.parse(variationItems.get(47).child(0).text()).doubleValue();
		indROE = format.parse(variationItems.get(49).child(0).text()).doubleValue();
		indEVEBIT = format.parse(variationItems.get(53).child(0).text()).doubleValue();
		indLiquidezCorrente = format.parse(variationItems.get(55).child(0).text()).doubleValue();
		indGiroAtivos = format.parse(variationItems.get(59).child(0).text()).doubleValue();
		indDBPL = format.parse(variationItems.get(61).child(0).text()).doubleValue();
		indCrescimentoRecita5a = format.parse(variationItems.get(65).child(0).text()).doubleValue();



		Element BalanceSheetSection = sections.get(3);
		Elements BalanceSheetItems = BalanceSheetSection.select("td");
		bsAtivos = format.parse(BalanceSheetItems.get(2).child(0).text().replace(".", "")).longValue();
		bsDividaBruta = format.parse(BalanceSheetItems.get(4).child(0).text().replace(".", "")).longValue();
		bsDisponibilidade = format.parse(BalanceSheetItems.get(6).child(0).text().replace(".", "")).longValue();
		bsDividaLiquida = format.parse(BalanceSheetItems.get(8).child(0).text().replace(".", "")).longValue();
		bsAtivoCirculante = format.parse(BalanceSheetItems.get(10).child(0).text().replace(".", "")).longValue();
		bsPatrimonioLiquido = format.parse(BalanceSheetItems.get(12).child(0).text().replace(".", "")).longValue();

		Element ResultsSection = sections.get(4);
		Elements ResultsItems = ResultsSection.select("td");
		resReceitaLiquida12M = format.parse(ResultsItems.get(4).child(0).text().replace(".", "")).longValue();
		resReceitaLiquida3M = format.parse(ResultsItems.get(6).child(0).text().replace(".", "")).longValue();
		resEBIT12M = format.parse(ResultsItems.get(8).child(0).text().replace(".", "")).longValue();
		resEBIT3M = format.parse(ResultsItems.get(10).child(0).text().replace(".", "")).longValue();
		resLucroLiquido12M = format.parse(ResultsItems.get(12).child(0).text().replace(".", "")).longValue();
		resLucroLiquido3M = format.parse(ResultsItems.get(14).child(0).text().replace(".", "")).longValue();

	}

}
