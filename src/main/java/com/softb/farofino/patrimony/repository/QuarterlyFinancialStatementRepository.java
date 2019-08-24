package com.softb.farofino.patrimony.repository;

import com.softb.farofino.patrimony.model.QuarterlyFinancialStatement;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("AppQuarterlyFinancialStatementRepository")
public interface QuarterlyFinancialStatementRepository extends JpaRepository<QuarterlyFinancialStatement, Integer> {

    @Query("select qfs from QuarterlyFinancialStatement qfs, CompanyUnderCover c where c.id = qfs.company.id and c.code =:code and qfs.releaseDate = :releaseDate")
    QuarterlyFinancialStatement findByReleaseDateStock(@Param("releaseDate") Date releaseDate, @Param("code") String code) throws DataAccessException;
}
