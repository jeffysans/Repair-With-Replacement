package com.repairreplace.service;
import com.repairreplace.model.RepairRequest;
import com.repairreplace.repository.RepairRequestRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RepairRequestService {
 private final RepairRequestRepository repository;
 public RepairRequestService(RepairRequestRepository repository){this.repository=repository;}
 public RepairRequest create(RepairRequest r){
   if(r.getCustomerName()==null||r.getCustomerName().isBlank()||r.getEmail()==null||r.getEmail().isBlank()||r.getProductName()==null||r.getProductName().isBlank()) throw new IllegalArgumentException("Name, email and product name are required.");
   if(r.getRepairCost()<0||r.getReplacementCost()<0)throw new IllegalArgumentException("Costs cannot be negative.");
   if(r.getProductAgeYears()<0 || r.getPreviousRepairs()<0) throw new IllegalArgumentException("Product age and previous repairs cannot be negative.");
   boolean repairCostFavourable = r.getReplacementCost()>0 && r.getRepairCost() < r.getReplacementCost()*0.6;
   boolean conditionFavourable = "Excellent".equalsIgnoreCase(r.getOverallCondition()) || "Good".equalsIgnoreCase(r.getOverallCondition());
   boolean repair = repairCostFavourable && conditionFavourable && r.getProductAgeYears()<=5;
   r.setRecommendation(repair ? "REPAIR RECOMMENDED" : "REPLACEMENT REVIEW RECOMMENDED");
   r.setRecommendationReason(repair ? "Repair is suggested because the estimated repair cost is below 60% of replacement cost, the product is relatively new, and its reported condition is good." : "Review replacement because one or more factors (cost, age, or reported condition) do not strongly favour repair. Ask a technician to confirm the damage and final costs.");
   r.setStatus("Submitted"); return repository.save(r);
 }
 public RepairRequest save(RepairRequest request){return repository.save(request);}
 public List<RepairRequest> all(){return repository.findAll();}
 public RepairRequest byId(Long id){return repository.findById(id).orElseThrow(()->new IllegalArgumentException("Request not found."));}
 public RepairRequest updateStatus(Long id,String status){
   if(!List.of("Submitted","Under Review","Repair in Progress","Replacement Approved","Completed","Rejected").contains(status))throw new IllegalArgumentException("Invalid status.");
   RepairRequest r=byId(id);r.setStatus(status);return repository.save(r);
 }
}
