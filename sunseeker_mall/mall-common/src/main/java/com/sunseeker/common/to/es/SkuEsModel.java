package com.sunseeker.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuEsModel {
    private Long skuId;

    private Long spuId;

    private String skuTitle;

    private BigDecimal skuPrice;

    private String skuImg;

    private Long saleCount;

    private Boolean hasStock;

    private Long hotScore;

    private Long brandId;

    private Long catalogId;

    private String brandName;

    private String brandImg;

    private String catalogName;

    private List<Attrs> attrs;

    private String seller;

    @Data
    //为了第三方工具能对它序列化反序列化，设置为可访问的权限
    public static class Attrs {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }

}
