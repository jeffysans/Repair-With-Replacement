package com.repairreplace.repository;
import com.repairreplace.model.RepairRequest;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RepairRequestRepository extends JpaRepository<RepairRequest,Long> { }
