package com.softb.farofino.patrimony.model;

import com.softb.system.repository.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Resultado e indicadores fundamentalistas da empresa em um determinado trimestre
 * @author Erik Lacerda 
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "QUARTERLY_FINANCIAL_STATEMENT")
public class QuarterlyFinancialStatement extends BaseEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
	protected CompanyUnderCover company;

	@Column(name = "QUARTER")
	@NotEmpty
	protected String quarter;

	@Column(name = "MARKET_VALUE")
	@NotNull
	protected Long marketValue;

	@Column(name = "RELEASE_DATE")
	@NotNull
	protected Date releaseDate;

    @Column(name="SHARES_QUANTITY")
    @NotNull
    protected Long sharesQuantity;

	@Column(name="P_L")
	@NotNull
	protected Double pL;

	@Column(name="L_PA")
	@NotNull
	protected Double lPa;

	@Column(name="P_VP")
	@NotNull
	protected Double pVp;

	@Column(name="VP_A")
	@NotNull
	protected Double vpA;

	@Column(name="P_EBIT")
	@NotNull
	protected Double pEBIT;

	@Column(name="MARGEM_BRUTA")
	@NotNull
	protected Double margemBruta;

	@Column(name="PSR")
	@NotNull
	protected Double pSR;

	@Column(name="MARGEM_EBIT")
	@NotNull
	protected Double margemEBIT;

	@Column(name="P_ATIVOS")
	@NotNull
	protected Double pAtivos;

	@Column(name="MARGEM_LIQUIDA")
	@NotNull
	protected Double margemLiquida;

	@Column(name="P_CAPITAL_GIRO")
	@NotNull
	protected Double PCapitalGiro;

	@Column(name="EBIT_ATIVO")
//	@NotNull
	protected Double EBITAtivo;

	@Column(name="P_ATIVO_CIRCULANTE_LIQ")
	@NotNull
	protected Double pAtivoCirculanteLiq;

	@Column(name="ROIC")
	@NotNull
	protected Double ROIC;

	@Column(name="DIVIDEND_YELD")
	@NotNull
	protected Double dividendYeld;

	@Column(name="ROE")
	@NotNull
	protected Double ROE;

	@Column(name="EV_EBIT")
	@NotNull
	protected Double evEBIT;

	@Column(name="LIQUIDEZ_CORRENTE")
	@NotNull
	protected Double liquidezCorrente;

	@Column(name="GIRO_ATIVOS")
	@NotNull
	protected Double giroAtivos;

	@Column(name="DB_PL")
	@NotNull
	protected Double dbPL;

	@Column(name="CRESCIMENTO_RECEITA_5A")
	@NotNull
	protected Double crescimentoReceita5a;

	@Column(name="ATIVOS")
	@NotNull
	protected Long ativos;

	@Column(name="DIVIDA_BRUTA")
	@NotNull
	protected Long dividaBruta;

	@Column(name="CAIXA")
	@NotNull
	protected Long caixa;

	@Column(name="DIVIDA_LIQUIDA")
	@NotNull
	protected Long dividaLiquida;

	@Column(name="ATIVO_CIRCULANTE")
	@NotNull
	protected Long ativoCirculante;

	@Column(name="PATRIMONIO_LIQUIDO")
	@NotNull
	protected Long patrimonioLiquido;

	@Column(name="RECEITA_LIQUIDA_12M")
	@NotNull
	protected Long receitaLiquida12M;

	@Column(name="RECEITA_LIQUIDA_3M")
	@NotNull
	protected Long receitaLiquida3M;

	@Column(name="EBIT_12M")
	@NotNull
	protected Long EBIT12M;

	@Column(name="EBIT_3M")
	@NotNull
	protected Long EBIT3M;

	@Column(name="LUCRO_LIQUIDO_12M")
	@NotNull
	protected Long lucroLiquido12M;

	@Column(name="LUCRO_LIQUIDO_3M")
	@NotNull
	protected Long lucroLiquido3M;

}
