package com.load.address.search.controller;

import com.load.address.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load-name-address/search")
@Api(tags = "도로명 주소 검색 API")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "도로명 주소 검색")
    @GetMapping
    public ResponseEntity<String> findRoadAddress(
            @ApiParam(value = "query", required = true)
            @RequestParam String query
    ){
        return ResponseEntity.ok(searchService.findRoadAddress(query));
    }
}
