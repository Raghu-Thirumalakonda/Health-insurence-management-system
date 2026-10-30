package com.vendor.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PatientModel {
    private Integer patientId;
    private String patientName;
    private Integer age;
    private String gender;
    private String disease;
    private String phoneNumber;
    private String email;
    private LocalDate registeredAt;
    private LocalDate lastVisitDate;
    private String profileImageUrl;
    private String insurancePlan;
    private String policyNumber;
    private LocalDate coverageStartDate;
    private LocalDate coverageEndDate;
    private String notificationPreference;
    private String portalPassword;
    private boolean portalEnabled;

    public Integer getPatientId(){return patientId;} public void setPatientId(Integer v){patientId=v;}
    public String getPatientName(){return patientName;} public void setPatientName(String v){patientName=v;}
    public Integer getAge(){return age;} public void setAge(Integer v){age=v;}
    public String getGender(){return gender;} public void setGender(String v){gender=v;}
    public String getDisease(){return disease;} public void setDisease(String v){disease=v;}
    public String getPhoneNumber(){return phoneNumber;} public void setPhoneNumber(String v){phoneNumber=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public LocalDate getRegisteredAt(){return registeredAt;} public void setRegisteredAt(LocalDate v){registeredAt=v;}
    public LocalDate getLastVisitDate(){return lastVisitDate;} public void setLastVisitDate(LocalDate v){lastVisitDate=v;}
    public String getProfileImageUrl(){return profileImageUrl;} public void setProfileImageUrl(String v){profileImageUrl=v;}
    public String getInsurancePlan(){return insurancePlan;} public void setInsurancePlan(String v){insurancePlan=v;}
    public String getPolicyNumber(){return policyNumber;} public void setPolicyNumber(String v){policyNumber=v;}
    public LocalDate getCoverageStartDate(){return coverageStartDate;} public void setCoverageStartDate(LocalDate v){coverageStartDate=v;}
    public LocalDate getCoverageEndDate(){return coverageEndDate;} public void setCoverageEndDate(LocalDate v){coverageEndDate=v;}
    public String getNotificationPreference(){return notificationPreference;} public void setNotificationPreference(String v){notificationPreference=v;}
    public String getPortalPassword(){return portalPassword;} public void setPortalPassword(String v){portalPassword=v;}
    public boolean isPortalEnabled(){return portalEnabled;} public void setPortalEnabled(boolean v){portalEnabled=v;}
    public String getCoverageStatus(){
        if (policyNumber == null || policyNumber.isBlank()) return "Not enrolled";
        if (coverageEndDate != null && coverageEndDate.isBefore(LocalDate.now())) return "Expired";
        if (coverageStartDate != null && coverageStartDate.isAfter(LocalDate.now())) return "Scheduled";
        if (coverageStartDate == null && coverageEndDate == null) return "Dates missing";
        return "Active";
    }
    public long getDaysToExpiry(){return coverageEndDate == null ? -1 : ChronoUnit.DAYS.between(LocalDate.now(), coverageEndDate);}
}
