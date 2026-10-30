package com.vendor.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="insurance_claim")
public class ClaimEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
    private String claimantName;
    private String policyNumber;
    private String claimType;
    private BigDecimal claimAmount;
    private LocalDate incidentDate;
    private LocalDate submittedAt = LocalDate.now();
    private String status = "Pending";
    @Column(length=1200) private String notes;
    public Integer getId(){return id;} public void setId(Integer v){id=v;}
    public String getClaimantName(){return claimantName;} public void setClaimantName(String v){claimantName=v;}
    public String getPolicyNumber(){return policyNumber;} public void setPolicyNumber(String v){policyNumber=v;}
    public String getClaimType(){return claimType;} public void setClaimType(String v){claimType=v;}
    public BigDecimal getClaimAmount(){return claimAmount;} public void setClaimAmount(BigDecimal v){claimAmount=v;}
    public LocalDate getIncidentDate(){return incidentDate;} public void setIncidentDate(LocalDate v){incidentDate=v;}
    public LocalDate getSubmittedAt(){return submittedAt;} public void setSubmittedAt(LocalDate v){submittedAt=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}
