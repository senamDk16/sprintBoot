package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.TypeMatiereRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.TypeMatiereResponse;
import com.davibilapps.learnsmart.services.TypeMatiereService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Type Matiere ", description = "Endpoint of Type Matiere")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TypeMatiereApi {

    private final TypeMatiereService typeMatiereService;

    @PostMapping("/typeMatiere")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Type Matiere", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<TypeMatiereResponse> saveTypeMatiere(@RequestBody TypeMatiereRequest typeMatiereRequest) {
        return ResponseEntity.ok(typeMatiereService.save(typeMatiereRequest));
    }

    @PutMapping("/typeMatiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Type Matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<TypeMatiereResponse> updateTypeMatiere(@PathVariable UUID trackingId, @RequestBody TypeMatiereRequest request) {
        return ResponseEntity.ok(typeMatiereService.update(trackingId, request));
    }

    @DeleteMapping("/typeMatiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Type Matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteETypeMatiereByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.typeMatiereService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/typeMatiere/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Type Matiere by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<TypeMatiereResponse> getOneTypeMatiere(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(typeMatiereService.getOne(trackingId));
    }

    @GetMapping("/typeMatiere/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Type Matiere  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<TypeMatiereResponse>> update() {
        return ResponseEntity.ok(typeMatiereService.listAll());
    }


}
