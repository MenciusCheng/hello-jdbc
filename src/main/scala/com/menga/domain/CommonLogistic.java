package com.menga.domain;

import java.util.Objects;

/**
 * Excel 运价表
 *
 * Created by Marvel on 2019/9/20.
 */
public class CommonLogistic {

    private String sourceCodeProvince;
    private String sourceCodeCity;
    private String sourceCodeZone;
    private String destinationCodeProvince;
    private String destinationCodeCity;
    private String destinationCodeZone;
    private String price1st;
    private String price2nd;
    private String price3nd;
    private String price4th;
    private String price5th;
    private String price6th;
    private String price7th;
    private String price8th;
    private String sourceCodeName;
    private String destinationCodeName;

    public String getSourceCodeName() {
        return sourceCodeName;
    }

    public void setSourceCodeName(String sourceCodeName) {
        this.sourceCodeName = sourceCodeName;
    }

    public String getDestinationCodeName() {
        return destinationCodeName;
    }

    public void setDestinationCodeName(String destinationCodeName) {
        this.destinationCodeName = destinationCodeName;
    }

    public CommonLogistic() {
    }

    public String getSourceCodeProvince() {
        return sourceCodeProvince;
    }

    public void setSourceCodeProvince(String sourceCodeProvince) {
        this.sourceCodeProvince = sourceCodeProvince;
    }

    public String getSourceCodeCity() {
        return sourceCodeCity;
    }

    public void setSourceCodeCity(String sourceCodeCity) {
        this.sourceCodeCity = sourceCodeCity;
    }

    public String getSourceCodeZone() {
        return sourceCodeZone;
    }

    public void setSourceCodeZone(String sourceCodeZone) {
        this.sourceCodeZone = sourceCodeZone;
    }

    public String getDestinationCodeProvince() {
        return destinationCodeProvince;
    }

    public void setDestinationCodeProvince(String destinationCodeProvince) {
        this.destinationCodeProvince = destinationCodeProvince;
    }

    public String getDestinationCodeCity() {
        return destinationCodeCity;
    }

    public void setDestinationCodeCity(String destinationCodeCity) {
        this.destinationCodeCity = destinationCodeCity;
    }

    public String getDestinationCodeZone() {
        return destinationCodeZone;
    }

    public void setDestinationCodeZone(String destinationCodeZone) {
        this.destinationCodeZone = destinationCodeZone;
    }

    public String getPrice1st() {
        return price1st;
    }

    public void setPrice1st(String price1st) {
        this.price1st = price1st;
    }

    public String getPrice2nd() {
        return price2nd;
    }

    public void setPrice2nd(String price2nd) {
        this.price2nd = price2nd;
    }

    public String getPrice3nd() {
        return price3nd;
    }

    public void setPrice3nd(String price3nd) {
        this.price3nd = price3nd;
    }

    public String getPrice4th() {
        return price4th;
    }

    public void setPrice4th(String price4th) {
        this.price4th = price4th;
    }

    public String getPrice5th() {
        return price5th;
    }

    public void setPrice5th(String price5th) {
        this.price5th = price5th;
    }

    public String getPrice6th() {
        return price6th;
    }

    public void setPrice6th(String price6th) {
        this.price6th = price6th;
    }

    public String getPrice7th() {
        return price7th;
    }

    public void setPrice7th(String price7th) {
        this.price7th = price7th;
    }

    public String getPrice8th() {
        return price8th;
    }

    public void setPrice8th(String price8th) {
        this.price8th = price8th;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommonLogistic{");
        sb.append("sourceCodeProvince='").append(sourceCodeProvince).append('\'');
        sb.append(", sourceCodeCity='").append(sourceCodeCity).append('\'');
        sb.append(", sourceCodeZone='").append(sourceCodeZone).append('\'');
        sb.append(", destinationCodeProvince='").append(destinationCodeProvince).append('\'');
        sb.append(", destinationCodeCity='").append(destinationCodeCity).append('\'');
        sb.append(", destinationCodeZone='").append(destinationCodeZone).append('\'');
        sb.append(", price1st='").append(price1st).append('\'');
        sb.append(", price2nd='").append(price2nd).append('\'');
        sb.append(", price3nd='").append(price3nd).append('\'');
        sb.append(", price4th='").append(price4th).append('\'');
        sb.append(", price5th='").append(price5th).append('\'');
        sb.append(", price6th='").append(price6th).append('\'');
        sb.append(", price7th='").append(price7th).append('\'');
        sb.append(", price8th='").append(price8th).append('\'');
        sb.append(", sourceCodeName='").append(sourceCodeName).append('\'');
        sb.append(", destinationCodeName='").append(destinationCodeName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonLogistic that = (CommonLogistic) o;
        return Objects.equals(sourceCodeProvince, that.sourceCodeProvince) &&
                Objects.equals(sourceCodeCity, that.sourceCodeCity) &&
                Objects.equals(sourceCodeZone, that.sourceCodeZone) &&
                Objects.equals(destinationCodeProvince, that.destinationCodeProvince) &&
                Objects.equals(destinationCodeCity, that.destinationCodeCity) &&
                Objects.equals(destinationCodeZone, that.destinationCodeZone) &&
                Objects.equals(price1st, that.price1st) &&
                Objects.equals(price2nd, that.price2nd) &&
                Objects.equals(price3nd, that.price3nd) &&
                Objects.equals(price4th, that.price4th) &&
                Objects.equals(price5th, that.price5th) &&
                Objects.equals(price6th, that.price6th) &&
                Objects.equals(price7th, that.price7th) &&
                Objects.equals(price8th, that.price8th) &&
                Objects.equals(sourceCodeName, that.sourceCodeName) &&
                Objects.equals(destinationCodeName, that.destinationCodeName);
    }
}
