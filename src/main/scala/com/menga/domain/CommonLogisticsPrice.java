package com.menga.domain;

/**
 * 数据库运价表
 *
 * Created by Marvel on 2019/9/23.
 */
public class CommonLogisticsPrice {

    private Integer id;
    private String sourceCode;
    private String destinationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommonLogisticsPrice{");
        sb.append("id=").append(id);
        sb.append(", sourceCode='").append(sourceCode).append('\'');
        sb.append(", destinationCode='").append(destinationCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
