package com.load.address.search;

import com.load.address.search.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Test
    public void test(){
        String result_1 = searchService.findRoadAddress("성남, 분당 백 현 로 265, 푸른마을 아파트로 보내주세요!!");
        assert result_1.contains("백현로");

        String result_2 = searchService.findRoadAddress("마포구 도화-2길 코끼리분식");
        assert result_2.contains("도화2길");
    }
}
