package com.davibilapps.learnsmart.api;


import com.davibilapps.learnsmart.dto.request.CoursRequest;
import com.davibilapps.learnsmart.dto.response.CoursResponse;
import com.davibilapps.learnsmart.services.CoursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Cours ", description = "Endpoint of Cours")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CoursApi {

    private final CoursService coursService ;

    @PostMapping("/cours")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new cours", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CoursResponse> saveCours(@RequestBody CoursRequest coursRequest) {
        return ResponseEntity.ok(coursService.save(coursRequest));
    }

    @GetMapping("/cours")
    @Operation(description = "This endpoint require a valid JWT", summary = "List of cours", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CoursResponse>> getAllcours() {
        return ResponseEntity.ok(coursService.listAll());
    }

    @PutMapping("/cours/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CoursResponse> updateCategorie(@PathVariable UUID trackingId, @RequestBody CoursRequest request) {
        return ResponseEntity.ok(coursService.update(trackingId, request));
    }

    @DeleteMapping("/cours/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deleteCoursByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.coursService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/cours/all/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Matiere  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CoursResponse>> updateCategorie(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(coursService.listAllByMatiere(trackingId));
    }

    @GetMapping("/cours/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Matiere  Cours by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CoursResponse> getOneCategorie(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(coursService.getOne(trackingId));
    }

    @GetMapping("/cours/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Matiere  Cours by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CoursResponse>> updateCategorie() {
        return ResponseEntity.ok(coursService.listAll());
    }



}
