package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.EpreuveRequest;
import com.davibilapps.learnsmart.dto.request.EtablissementRequest;
import com.davibilapps.learnsmart.dto.response.EpreuveResponse;
import com.davibilapps.learnsmart.dto.response.EtablissementResponse;
import com.davibilapps.learnsmart.services.EtablissementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Etablissement ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EtablissementApi {

    private final EtablissementService etablissementService;


    @PostMapping("/etablissement")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Etablissement", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EtablissementResponse> saveEtablissement(@RequestBody EtablissementRequest etablissementRequest) {
        return ResponseEntity.ok(etablissementService.save(etablissementRequest));
    }

    @PutMapping("/etablissement/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Etablissement by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EtablissementResponse> updateEtablissement(@PathVariable UUID trackingId, @RequestBody EtablissementRequest request) {
        return ResponseEntity.ok(etablissementService.update(trackingId, request));
    }

    @DeleteMapping("/etablissement/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Etablissement by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteEtablissementByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.etablissementService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/etablissement/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Etablissement   by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EtablissementResponse> getOneEtablissement(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(etablissementService.getOne(trackingId));
    }

    @GetMapping("/etablissement/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Etablissement   by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<EtablissementResponse>> update() {
        return ResponseEntity.ok(etablissementService.listAll());
    }


}
