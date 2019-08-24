package com.softb.farofino.patrimony.repository;

import com.softb.farofino.patrimony.model.QFSReleaseCalendar;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("AppQFSResultCalendarRepository")
public interface QFSResultCalendarRepository extends JpaRepository<QFSReleaseCalendar, Integer> {

    @Query("select qfs from QFSReleaseCalendar qfs, CompanyUnderCover cuc where qfs.company.id = :companyId and qfs.quarter =:quarter")
    QFSReleaseCalendar findByQuarter(@Param("companyId") Integer companyId, @Param("quarter") String quarter) throws DataAccessException;

    @Query("select qfs from QFSReleaseCalendar qfs where qfs.date < :date AND  qfs.processed = false")
    List<QFSReleaseCalendar> findByNotProcessedUntilDate(@Param("date") Date date) throws DataAccessException;
}
