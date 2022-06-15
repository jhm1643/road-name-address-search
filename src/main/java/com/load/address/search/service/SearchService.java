package com.load.address.search.service;

import com.load.address.search.dto.KakaoAddressResponse;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private static final String KAKAO_API_KEY = "abeb874a895f679ebd682dcb8b69b51e";
    private static final String KAKAO_ADDRESS_API_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    public String findRoadAddress(String query){

        String result = "";

        char[] queryCharArr = remakeQuery(query).toCharArray();
        for(int i=0; i < queryCharArr.length; i++){

            if(isRoadAddressLastValue(queryCharArr[i])){
                String roadName = "";
                for(int j = i - 1; j >= 0; j--){

                    //이미 검색된 부분이 나올경우 해당 검색을 종료한다.
                    if(queryCharArr[i] == queryCharArr[j])
                        break;

                    roadName = queryCharArr[j] + roadName;

                    if(Character.isDigit(queryCharArr[j]))
                        continue;

                    KakaoAddressResponse response = getAddressInfo(roadName + queryCharArr[i]);
                    if(response.getMeta().getTotal_count() > 0)
                        result = response.getDocuments().get(0).getRoad_address().getAddress_name();
                }
            }
        }

        return result;
    }

    public KakaoAddressResponse getAddressInfo(String query){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "KakaoAK " + KAKAO_API_KEY);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(
                UriComponentsBuilder.fromUriString(KAKAO_ADDRESS_API_URL)
                        .queryParam("query", query)
                        .queryParam("size", 1)
                        .build().encode().toUri(),
                HttpMethod.GET,
                new HttpEntity<>("", httpHeaders),
                KakaoAddressResponse.class
        ).getBody();
    }

    private Boolean isRoadAddressLastValue(char value){
        if('로' ==value || '길' == value)
            return true;

        return false;
    }

    private String remakeQuery(String query){
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        query = query.replaceAll(match, "");
        query.trim();

        return query;
    }
}