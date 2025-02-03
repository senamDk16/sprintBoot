package com.davibilapps.learnsmart.api;


import com.davibilapps.learnsmart.dto.request.CategorieRequest;
import com.davibilapps.learnsmart.dto.response.CategorieResponse;
import com.davibilapps.learnsmart.dto.response.CoursResponse;
import com.davibilapps.learnsmart.services.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Categorie ", description = "Endpoint of Categorie")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategorieApi {

    private final CategorieService categorieService;

    @PostMapping("/categorie")
    @Operation(description = "This endpoint require a valid JWT", summary = "Add new Categorie", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CategorieResponse> savecategorie(@RequestBody CategorieRequest categorieRequest) {
        return ResponseEntity.ok(categorieService.save(categorieRequest));
    }

    @GetMapping("/categorie")
    @Operation(description = "This endpoint require a valid JWT", summary = "List of categorie", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CategorieResponse>> getAllCategorie() {
        return ResponseEntity.ok(categorieService.listAll());
    }

    @PutMapping("/categorie/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Update a categorie by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<CategorieResponse> updateCategorie(@PathVariable UUID trackingId, @RequestBody CategorieRequest categorieRequest) {
        return ResponseEntity.ok(categorieService.update(trackingId, categorieRequest));
    }

    @DeleteMapping("/categorie/{trackingId}")
    @Operation(description = "This endpoint require a valid JWT", summary = "Delete  Categorie by TrackingId", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "401")})
    public ResponseEntity<Void> deletecategorieByTrackingId(@PathVariable("trackingId") UUID trackingId) {
        this.categorieService.delete(trackingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cours/all/")
    @Operation(description = "This endpoint require a valid JWT", summary = "Liste Matiere  Categorie by All", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / InvaltrackingId Token", responseCode = "401")})
    public ResponseEntity<List<CategorieResponse>> updateCategorie() {
        return ResponseEntity.ok(categorieService.listAll());
    }

}
