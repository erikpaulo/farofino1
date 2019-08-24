package com.softb.farofino.patrimony.model;

import com.softb.system.repository.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa o patrimônio total do user
 * @author Erik Lacerda 
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMPANY_UNDER_COVER")
public class CompanyUnderCover extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME")
	@NotEmpty
	private String name;

	@Column(name = "NICKNAME")
	@NotEmpty
	private String nickname;

	@Column(name = "CODE")
	@NotEmpty
	private String code;

	@Column(name = "SECTOR")
	@NotEmpty
    private String sector;

	@Column(name = "SUB_SECTOR")
	@NotEmpty
	private String subSector;
}
