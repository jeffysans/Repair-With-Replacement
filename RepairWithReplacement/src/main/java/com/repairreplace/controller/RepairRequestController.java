package com.repairreplace.controller;
import com.repairreplace.model.RepairRequest;
import com.repairreplace.service.RepairRequestService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins="*")
public class RepairRequestController {
 private final RepairRequestService service;
 public RepairRequestController(RepairRequestService service){this.service=service;}
 @PostMapping public ResponseEntity<?> create(@Valid @RequestBody RepairRequest request){try{return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));}catch(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
 @PostMapping(value="/{id}/photo", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
 public ResponseEntity<?> uploadPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo) {
  try {
   RepairRequest request=service.byId(id);
   if(photo.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error","Please choose an image."));
   String contentType=photo.getContentType();
   if(contentType==null || !contentType.startsWith("image/")) return ResponseEntity.badRequest().body(Map.of("error","Only image files are allowed."));
   if(photo.getSize()>5*1024*1024) return ResponseEntity.badRequest().body(Map.of("error","Image must be 5 MB or smaller."));
   String original=photo.getOriginalFilename()==null?"image":Paths.get(photo.getOriginalFilename()).getFileName().toString();
   String ext=original.contains(".")?original.substring(original.lastIndexOf('.')).replaceAll("[^.a-zA-Z0-9]",""):".img";
   String filename="request-"+id+"-"+UUID.randomUUID()+ext;
   Path dir=Paths.get("uploads").toAbsolutePath().normalize(); Files.createDirectories(dir);
   Files.copy(photo.getInputStream(),dir.resolve(filename),StandardCopyOption.REPLACE_EXISTING);
   request.setPhotoPath("/uploads/"+filename);
   return ResponseEntity.ok(service.save(request));
  } catch(IllegalArgumentException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
    catch(IOException e){return ResponseEntity.status(500).body(Map.of("error","Could not save the image."));}
 }
 @GetMapping public List<RepairRequest> all(){return service.all();}
 @GetMapping("/{id}") public ResponseEntity<?> byId(@PathVariable Long id){try{return ResponseEntity.ok(service.byId(id));}catch(IllegalArgumentException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}}
 @PatchMapping("/{id}/status") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Map<String,String> body){try{return ResponseEntity.ok(service.updateStatus(id,body.getOrDefault("status","")));}catch(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}}
}
