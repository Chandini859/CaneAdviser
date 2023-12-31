package com.cdac.caneadviser.controller;
import com.cdac.caneadviser.dao.Login;
import com.cdac.caneadviser.dao.Registration;
import com.cdac.caneadviser.dao.VerifyOTP;
import com.cdac.caneadviser.entity.FarmerDetail;
import com.cdac.caneadviser.service.CaneAdviserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/farmer-details")
public class FarmerDetailController {

	@Autowired
    private CaneAdviserService caneAdviserService;

    

    @GetMapping
    public List<FarmerDetail> getAllFarmerDetails() {
        return caneAdviserService.getAllFarmerDetails();
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<FarmerDetail> getFarmerDetailById(@PathVariable int farmId) {
        Optional<FarmerDetail> farmerDetail = caneAdviserService.getFarmerDetailById(farmId);
        return farmerDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<FarmerDetail> saveFarmerDetail(@RequestBody FarmerDetail farmerDetail) {
        FarmerDetail savedFarmerDetail = caneAdviserService.saveFarmerDetail(farmerDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFarmerDetail);
    }

    @DeleteMapping("/{farmId}")
    public ResponseEntity<Void> deleteFarmerDetail(@PathVariable int farmId) {
    	caneAdviserService.deleteFarmerDetail(farmId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<FarmerDetail> updateFarmerDetail(@RequestBody FarmerDetail farmerDetail) {
        FarmerDetail updatedFarmerDetail = caneAdviserService.updateFarmerDetail(farmerDetail);
        return ResponseEntity.ok(updatedFarmerDetail);
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<FarmerDetail> registerFarmer(@RequestBody Registration registration) {
        FarmerDetail farmerDetail = caneAdviserService.registerFarmer(registration);
        return ResponseEntity.ok(farmerDetail);
    }
    
    @PostMapping(value = "login")
    @ResponseBody
    public String farmerLogin(@Valid @RequestBody Login login) {
        return caneAdviserService.checkAuthentication(login).toString();
    }
    @PostMapping(value = "verifyOTP")
    @ResponseBody
    public FarmerDetail verifyOTP(@Valid @RequestBody VerifyOTP verifyOTP) {
        return caneAdviserService.verifyOTP(verifyOTP);
    }
    
//    @PostMapping("/generate-token")
//    public ResponseEntity<Void> generateToken(@RequestBody VerifyOTP verifyOTP) {
//        FarmerDetail farmerDetail = caneAdviserService.verifyOTP(verifyOTP);
//
//        if ("Success".equals(farmerDetail.getStatus())) {
//            String jwtToken = farmerDetail.getJwtToken();
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", "Bearer " + jwtToken);
//
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .build(); 
//        } else {
//            return ResponseEntity.badRequest().build(); 
//        }
//    }


//    @PostMapping(value = "registration")
//    @ResponseBody
//    public String registerFarmer(@Valid @RequestBody Registration farmerDetail) {
//        return caneAdviserService.registerFarmer(farmerDetail);
//    }

//    @GetMapping("/generate") 
//    public String generateToken() {
//        String token = caneAdviserService.generateToken(113); 
//        return token;
//    }
    
   
}
