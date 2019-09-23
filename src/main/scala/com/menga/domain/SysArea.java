package com.menga.domain;

/**
 * Created by Marvel on 2019/9/23.
 */
public class SysArea {

    private Integer id;
    private String code;
    private String name;
    private Integer level;
    private String provinceCode;
    private String cityCode;
    private String zoneCode;
    private String streetCode;
    private String fullName;
    private Integer showLabel;
    private String parentCode;
    private String namePy;
    private String divisionCode;
    private Integer depArea;

    public SysArea() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getShowLabel() {
        return showLabel;
    }

    public void setShowLabel(Integer showLabel) {
        this.showLabel = showLabel;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public Integer getDepArea() {
        return depArea;
    }

    public void setDepArea(Integer depArea) {
        this.depArea = depArea;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysArea{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", level=").append(level);
        sb.append(", provinceCode='").append(provinceCode).append('\'');
        sb.append(", cityCode='").append(cityCode).append('\'');
        sb.append(", zoneCode='").append(zoneCode).append('\'');
        sb.append(", streetCode='").append(streetCode).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", showLabel=").append(showLabel);
        sb.append(", parentCode='").append(parentCode).append('\'');
        sb.append(", namePy='").append(namePy).append('\'');
        sb.append(", divisionCode='").append(divisionCode).append('\'');
        sb.append(", depArea=").append(depArea);
        sb.append('}');
        return sb.toString();
    }
}
