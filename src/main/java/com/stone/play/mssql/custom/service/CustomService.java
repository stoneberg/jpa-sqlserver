package com.stone.play.mssql.custom.service;

import com.stone.play.mssql.custom.dto.CustomDto;
import com.stone.play.mssql.custom.repository.CustomNativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CustomService {

    private final CustomNativeRepository customNativeRepository;

    public List<CustomDto> getProductsByCategoryNameAndBrandName(String categoryName, String brandName) {
        return customNativeRepository.findProductsByCategoryAndBrand(categoryName, brandName);
    }

}
