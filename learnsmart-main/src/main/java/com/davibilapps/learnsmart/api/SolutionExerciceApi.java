package com.davibilapps.learnsmart.api;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.request.SolutionExerciceRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.dto.response.SolutionExerciceResponse;
import com.davibilapps.learnsmart.services.SolutionExerciceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Solution Exercice ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SolutionExerciceApi {

    private final SolutionExerciceService solutionExerciceService;

    @PostMapping("/solutionExercice")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Exercice", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<SolutionExerciceResponse> saveSolutionExercice(@RequestBody SolutionExerciceRequest request) {
        return ResponseEntity.ok(solutionExerciceService.save(request));
    }

    @PutMapping("/solutionExercice/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<SolutionExerciceResponse> updateSolutionExercice(@PathVariable UUID trackingId, @RequestBody SolutionExerciceRequest request) {
        return ResponseEntity.ok(solutionExerciceService.update(trackingId, request));
    }

    @DeleteMapping("/solutionExercice/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteSolutionExerciceByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.solutionExerciceService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/solutionExercice/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = " Solution Exercice by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<SolutionExerciceResponse> getOneSolutionExercice(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(solutionExerciceService.getOne(trackingId));
    }

    @GetMapping("/solutionExercice/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Solution  Exercice   by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<SolutionExerciceResponse>> update() {
        return ResponseEntity.ok(solutionExerciceService.listAll());
    }


}
