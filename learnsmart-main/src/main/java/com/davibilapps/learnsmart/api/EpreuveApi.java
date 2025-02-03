package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.EpreuveRequest;
import com.davibilapps.learnsmart.dto.response.EpreuveResponse;
import com.davibilapps.learnsmart.services.EpreuveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Epreuve ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EpreuveApi {

    private final EpreuveService epreuveService;


    @PostMapping("/epreuve")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Epreuve", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EpreuveResponse> saveEpreuve(@RequestBody EpreuveRequest epreuveRequest) {
        return ResponseEntity.ok(epreuveService.save(epreuveRequest));
    }

    @PutMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Epreuve by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EpreuveResponse> updateEpreuve(@PathVariable UUID trackingId, @RequestBody EpreuveRequest request) {
        return ResponseEntity.ok(epreuveService.update(trackingId, request));
    }

    @DeleteMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Epreuve by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteEpreuveByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.epreuveService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Epreuve  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<EpreuveResponse> getOneEpreuve(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(epreuveService.getOne(trackingId));
    }

    @GetMapping("/epreuve/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Epreuve  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<EpreuveResponse>> update() {
        return ResponseEntity.ok(epreuveService.listAll());
    }

    @GetMapping("/epreuve/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Etablissement  Annale by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<EpreuveResponse>> listAllByAnnale(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(epreuveService.listAllByAnnale(trackingId));
    }


}
