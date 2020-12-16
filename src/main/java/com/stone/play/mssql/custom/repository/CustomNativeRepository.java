package com.stone.play.mssql.custom.repository;

import com.stone.play.mssql.custom.dto.CustomDto;

import java.util.List;

public interface CustomNativeRepository {

    List<CustomDto> findProductsByCategoryAndBrand(String categoryName, String brandName);

}
