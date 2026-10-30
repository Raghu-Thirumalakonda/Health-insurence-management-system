package com.vendor.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class PatientEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String passwordHash;
    private boolean portalEnabled;

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer value) { patientId = value; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String value) { patientName = value; }
    public Integer getAge() { return age; }
    public void setAge(Integer value) { age = value; }
    public String getGender() { return gender; }
    public void setGender(String value) { gender = value; }
    public String getDisease() { return disease; }
    public void setDisease(String value) { disease = value; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String value) { phoneNumber = value; }
    public String getEmail() { return email; }
    public void setEmail(String value) { email = value; }
    public LocalDate getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDate value) { registeredAt = value; }
    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate value) { lastVisitDate = value; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String value) { profileImageUrl = value; }
    public String getInsurancePlan() { return insurancePlan; }
    public void setInsurancePlan(String value) { insurancePlan = value; }
    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String value) { policyNumber = value; }
    public LocalDate getCoverageStartDate() { return coverageStartDate; }
    public void setCoverageStartDate(LocalDate value) { coverageStartDate = value; }
    public LocalDate getCoverageEndDate() { return coverageEndDate; }
    public void setCoverageEndDate(LocalDate value) { coverageEndDate = value; }
    public String getNotificationPreference() { return notificationPreference; }
    public void setNotificationPreference(String value) { notificationPreference = value; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String value) { passwordHash = value; }
    public boolean isPortalEnabled() { return portalEnabled; }
    public void setPortalEnabled(boolean value) { portalEnabled = value; }
}
