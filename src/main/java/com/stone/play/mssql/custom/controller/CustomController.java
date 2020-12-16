package com.stone.play.mssql.custom.controller;

import com.stone.play.common.response.ResponseDto;
import com.stone.play.mssql.custom.dto.CustomDto;
import com.stone.play.mssql.custom.service.CustomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customs")
public class CustomController {

    private final CustomService customService;

    @GetMapping("custom/categoryname/{categoryName}/brandname/{brandName}")
    public ResponseDto<?> productsByKeyword(@PathVariable final String categoryName, @PathVariable final String brandName) {
        List<CustomDto> products = customService.getProductsByCategoryNameAndBrandName(categoryName, brandName);
        products.forEach(productDto -> log.info("@product=====>{}", productDto.getProductId()));
        return ResponseDto.of(customService.getProductsByCategoryNameAndBrandName(categoryName, brandName));
    }

}
