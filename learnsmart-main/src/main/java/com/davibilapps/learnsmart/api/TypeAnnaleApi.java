package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.TypeAnnaleRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.TypeAnnaleResponse;
import com.davibilapps.learnsmart.services.TypeAnnaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Type Annale ", description = "Endpoint of Type Annale")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TypeAnnaleApi {

    private final TypeAnnaleService typeAnnaleService;


    @PostMapping("/typeAnnale")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Type Annale", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<TypeAnnaleResponse> saveTypeAnnale(@RequestBody TypeAnnaleRequest request) {
        return ResponseEntity.ok(typeAnnaleService.save(request));
    }

    @PutMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Type Annale by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<TypeAnnaleResponse> updateTypeAnnale(@PathVariable UUID trackingId, @RequestBody TypeAnnaleRequest request) {
        return ResponseEntity.ok(typeAnnaleService.update(trackingId, request));
    }

    @DeleteMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Type Annale  by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteTypeAnnaleByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.typeAnnaleService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/epreuve/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Exercice  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<TypeAnnaleResponse> getOneTypeAnnale(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(typeAnnaleService.getOne(trackingId));
    }

    @GetMapping("/epreuve/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Exercice  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<TypeAnnaleResponse>> update() {
        return ResponseEntity.ok(typeAnnaleService.listAll());
    }


}
