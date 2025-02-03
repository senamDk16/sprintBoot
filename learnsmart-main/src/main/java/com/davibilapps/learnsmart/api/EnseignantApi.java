package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.CycleRequest;
import com.davibilapps.learnsmart.dto.request.EnseignantRequest;
import com.davibilapps.learnsmart.dto.response.CycleResponse;
import com.davibilapps.learnsmart.dto.response.EnseignantResponse;
import com.davibilapps.learnsmart.services.EnseignantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Enseignant ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EnseignantApi {

    private final EnseignantService enseignantService;

    @PostMapping("/enseignant")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Enseignant", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EnseignantResponse> saveEnseignant(@RequestBody EnseignantRequest enseignantRequest) {
        return ResponseEntity.ok(enseignantService.save(enseignantRequest));
    }

    @PutMapping("/enseignant/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Enseignant by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EnseignantResponse> updateEnseignant(@PathVariable UUID trackingId, @RequestBody EnseignantRequest request) {
        return ResponseEntity.ok(enseignantService.update(trackingId, request));
    }

    @DeleteMapping("/enseignant/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Enseignant by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteEnseignantByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.enseignantService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/enseignant/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Matiere  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EnseignantResponse> getOneEnseignant(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(enseignantService.getOne(trackingId));
    }

    @GetMapping("/enseignant/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Matiere  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<EnseignantResponse>> update() {
        return ResponseEntity.ok(enseignantService.listAll());
    }

}
