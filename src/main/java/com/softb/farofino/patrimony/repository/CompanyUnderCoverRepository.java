package com.softb.farofino.patrimony.repository;


import com.softb.farofino.patrimony.model.CompanyUnderCover;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("AppCompanyUnderCoverRepository")
public interface CompanyUnderCoverRepository extends JpaRepository<CompanyUnderCover, Integer> {

    @Query("select cuc from CompanyUnderCover cuc where cuc.name = :name")
    CompanyUnderCover findByName(@Param("name") String name) throws DataAccessException;
}
