package com.vendor.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="insurance_policy")
public class PolicyEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
    private String policyName;
    private String coverageType;
    private BigDecimal premium;
    private BigDecimal coverageAmount;
    private Integer durationMonths;
    private String description;
    private Boolean active = true;
    private LocalDate createdAt = LocalDate.now();
    public Integer getId(){return id;} public void setId(Integer v){id=v;}
    public String getPolicyName(){return policyName;} public void setPolicyName(String v){policyName=v;}
    public String getCoverageType(){return coverageType;} public void setCoverageType(String v){coverageType=v;}
    public BigDecimal getPremium(){return premium;} public void setPremium(BigDecimal v){premium=v;}
    public BigDecimal getCoverageAmount(){return coverageAmount;} public void setCoverageAmount(BigDecimal v){coverageAmount=v;}
    public Integer getDurationMonths(){return durationMonths;} public void setDurationMonths(Integer v){durationMonths=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public Boolean getActive(){return active;} public void setActive(Boolean v){active=v;}
    public LocalDate getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDate v){createdAt=v;}
}
