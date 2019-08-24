package com.softb.farofino.patrimony.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.softb.system.repository.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Agenda de resultados das empresas
 * @author Erik Lacerda 
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "QFS_RELEASE_CALENDAR")
public class QFSReleaseCalendar extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public QFSReleaseCalendar(CompanyUnderCover companyUnderCover, JsonNode e) {


		this.company =  companyUnderCover;
		try {
			this.date = new SimpleDateFormat("dd/MM/yy").parse(e.get(1).asText());
		} catch (ParseException ex) {
			this.date = getDefaultQuarterDate();
		}
		this.quarter = e.get(2).asText();
		this.confirmed = e.get(8).asText().equals("Confirmado");
		this.processed = false;
	}

	private Date getDefaultQuarterDate(){
		Calendar today = Calendar.getInstance();

		Integer month = today.get(Calendar.MONTH)+1;
		Integer quarter = Math.round(month/3)+1;

		today.set(today.get(Calendar.YEAR), (quarter*3+1), 20);
		return today.getTime();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
	protected CompanyUnderCover company;

	@Column(name = "DATE")
	@NotNull
	protected Date date;

	@Column(name = "QUARTER")
	@NotEmpty
	protected String quarter;

	@Column(name = "CONFIRMED")
	@NotNull
	protected Boolean confirmed;

	@Column(name = "PROCESSED")
	@NotNull
	protected Boolean processed;

}
