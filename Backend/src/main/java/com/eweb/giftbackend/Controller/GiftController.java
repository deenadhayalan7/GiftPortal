package com.eweb.giftbackend.Controller;

// package com.backend.yogesh.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eweb.giftbackend.Service.GiftService;
import com.eweb.giftbackend.dto.request.GiftRequest;
import com.eweb.giftbackend.dto.response.BasicResponse;
import com.eweb.giftbackend.dto.response.GiftResponse;
import com.eweb.giftbackend.utils.MyConstant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(MyConstant.GIFTS)
@RequiredArgsConstructor
public class GiftController {
    private final GiftService giftService;
    @GetMapping(MyConstant.SHOWGIFT)
    public ResponseEntity<BasicResponse<GiftResponse>> create(){
        BasicResponse<GiftResponse> response=new BasicResponse<>();
        try{
            response=giftService.getAllGifts();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch(Exception e){
            response.setMessage("Something went wrong!");
            return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
        }
    }

@PostMapping(MyConstant.ADDGIFT)
    public ResponseEntity<GiftResponse> createGift(@RequestBody GiftRequest request) {
        GiftResponse response = new GiftResponse();
        try {
            response = giftService.createGift(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(Exception e) {
            response.setMessage("Something went Wrong!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

@PutMapping(MyConstant.UPDATEGIFT + "/{giftid}")
public ResponseEntity<BasicResponse<GiftResponse>> updategift(@PathVariable String giftid, @RequestBody GiftRequest giftRequest) {
    BasicResponse<GiftResponse> response = new BasicResponse<>();
    try {
        GiftResponse updatedgiftResponse = giftService.updateGift(giftid, giftRequest);
        response.setMessage(updatedgiftResponse.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
        response.setMessage("Failed to update gift: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @DeleteMapping(MyConstant.DELETEGIFT+"/{giftid}")
    public ResponseEntity<BasicResponse<GiftResponse>> deleteBooking(@PathVariable String giftid) {
        BasicResponse<GiftResponse> response = new BasicResponse<>();
        try {
            BasicResponse<GiftResponse> deletedBookingResponse = giftService.deletegift(giftid);
            response.setMessage(deletedBookingResponse.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Failed to delete booking: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}