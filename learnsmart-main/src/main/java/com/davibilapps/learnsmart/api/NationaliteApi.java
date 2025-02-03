package com.davibilapps.learnsmart.api;


import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.NationaliteRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.NationaliteResponse;
import com.davibilapps.learnsmart.services.NationaliteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Nationalite ", description = "Endpoint of Nationalite")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NationaliteApi {

    private final NationaliteService nationaliteService;

    @PostMapping("/nationalite")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Nationalite", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<NationaliteResponse> saveNationalite(@RequestBody NationaliteRequest epreuveRequest) {
        return ResponseEntity.ok(nationaliteService.save(epreuveRequest));
    }

    @PutMapping("/nationalite/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Nationalite by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<NationaliteResponse> updateNationalite(@PathVariable UUID trackingId, @RequestBody NationaliteRequest request) {
        return ResponseEntity.ok(nationaliteService.update(trackingId, request));
    }

    @DeleteMapping("/nationalite/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Nationalite by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteNationaliteByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.nationaliteService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/nationalite/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Nationalite  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<NationaliteResponse> getOneNationalite(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(nationaliteService.getOne(trackingId));
    }

    @GetMapping("/nationalite/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Nationalite by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<NationaliteResponse>> allNationalite() {
        return ResponseEntity.ok(nationaliteService.listAll());
    }


}
