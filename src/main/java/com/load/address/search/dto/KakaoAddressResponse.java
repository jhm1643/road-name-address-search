package com.load.address.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAddressResponse {

    private List<Document> documents;
    private Meta meta;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Document {

        private String address_name;
        private String address_type;
        private String x;
        private String y;
        private Address address;
        private RoadAddress road_address;

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Address {

            private String address_name;
            private String region_1depth_name;
            private String region_2depth_name;
            private String region_3depth_name;
            private String region_3depth_h_name;
            private String h_code;
            private String b_code;
            private String mountain_yn;
            private String main_address_no;
            private String sub_address_no;
            private String x;
            private String y;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class RoadAddress {

            private String address_name;
            private String region_1depth_name;
            private String region_2depth_name;
            private String region_3depth_name;
            private String road_name;
            private String underground_yn;
            private String main_building_no;
            private String sub_building_no;
            private String building_name;
            private String zone_no;
            private String x;
            private String y;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta {

        private Integer total_count;
        private Integer pageable_count;
        private Boolean is_end;
    }
}
