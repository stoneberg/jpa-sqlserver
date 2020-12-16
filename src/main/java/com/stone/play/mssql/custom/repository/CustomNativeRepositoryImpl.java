package com.stone.play.mssql.custom.repository;

import com.stone.play.mssql.custom.dto.CustomDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Slf4j
@Repository
public class CustomNativeRepositoryImpl implements CustomNativeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomDto> findProductsByCategoryAndBrand(String categoryName, String brandName) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("production.getProductsByCategoryAndBrand", "CustomDto");
        query.registerStoredProcedureParameter("categoryName", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("brandName", String.class, ParameterMode.IN);

        query.setParameter("categoryName", categoryName);
        query.setParameter("brandName", brandName);
        query.execute();

        return query.getResultList();
    }

//    public List<CustomDto> findProductsByCategoryAndBrand(String categoryName, String brandName) {
//        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("production.getProductsByCategoryAndBrand");
//
//        storedProcedure.registerStoredProcedureParameter("categoryName", String.class, ParameterMode.IN);
//        storedProcedure.registerStoredProcedureParameter("brandName", String.class, ParameterMode.IN);
//
//        storedProcedure.setParameter("categoryName", categoryName);
//        storedProcedure.setParameter("brandName", brandName);
//
//        // execute SP
//        storedProcedure.execute();
//
//        List resultList = storedProcedure.getResultList();
//
//
//
//        log.info("@resultList=====>{}", resultList);
//        return null;
//
//    }

}
