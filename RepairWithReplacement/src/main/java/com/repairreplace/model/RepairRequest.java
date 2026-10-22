package com.repairreplace.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="repair_requests")
public class RepairRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String customerName; private String email; private String phone;
 private String productType; private String productName;
 @Column(length=1500) private String problemDescription;
 private double repairCost; private double replacementCost;
 private int productAgeYears; private String overallCondition; private int previousRepairs; private String recommendationReason;
 private String recommendation; private String status; private String photoPath;
 private LocalDateTime createdAt;
 @PrePersist public void onCreate(){createdAt=LocalDateTime.now(); if(status==null)status="Submitted";}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getProductType(){return productType;} public void setProductType(String v){productType=v;}
 public String getProductName(){return productName;} public void setProductName(String v){productName=v;}
 public String getProblemDescription(){return problemDescription;} public void setProblemDescription(String v){problemDescription=v;}
 public double getRepairCost(){return repairCost;} public void setRepairCost(double v){repairCost=v;}
 public double getReplacementCost(){return replacementCost;} public void setReplacementCost(double v){replacementCost=v;}
 public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
 public String getPhotoPath(){return photoPath;} public void setPhotoPath(String v){photoPath=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
