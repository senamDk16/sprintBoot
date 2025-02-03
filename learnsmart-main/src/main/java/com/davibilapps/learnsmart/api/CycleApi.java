package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.CoursRequest;
import com.davibilapps.learnsmart.dto.request.CycleRequest;
import com.davibilapps.learnsmart.dto.response.CoursResponse;
import com.davibilapps.learnsmart.dto.response.CycleResponse;
import com.davibilapps.learnsmart.services.CycleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Cycle ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CycleApi {

    private final CycleService cycleService;


    @PostMapping("/cycle")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Cycle", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CycleResponse> saveCycle(@RequestBody CycleRequest cycleRequest) {
        return ResponseEntity.ok(cycleService.save(cycleRequest));
    }

    @PutMapping("/cycle/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Cycle by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CycleResponse> updateCycle(@PathVariable UUID trackingId, @RequestBody CycleRequest request) {
        return ResponseEntity.ok(cycleService.update(trackingId, request));
    }

    @DeleteMapping("/cycle/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  cycle by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteCycleByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.cycleService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/cours/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Etablissement  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CycleResponse>> updateCycle(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(cycleService.listAllByEtablissement(trackingId));
    }

    @GetMapping("/cours/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Cycle by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CycleResponse> getOneCycle(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(cycleService.getOne(trackingId));
    }

    @GetMapping("/cours/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste All  Cycle by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CycleResponse>> updateCycle() {
        return ResponseEntity.ok(cycleService.listAll());
    }


}
