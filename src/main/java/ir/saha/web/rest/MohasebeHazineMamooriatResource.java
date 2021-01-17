package ir.saha.web.rest;

import ir.saha.service.MohasebeHazineMamooriatService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.MohasebeHazineMamooriatDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link ir.saha.domain.MohasebeHazineMamooriat}.
 */
@RestController
@RequestMapping("/api")
public class MohasebeHazineMamooriatResource {

    private final Logger log = LoggerFactory.getLogger(MohasebeHazineMamooriatResource.class);

    private static final String ENTITY_NAME = "mohasebeHazineMamooriat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MohasebeHazineMamooriatService mohasebeHazineMamooriatService;

    public MohasebeHazineMamooriatResource(MohasebeHazineMamooriatService mohasebeHazineMamooriatService) {
        this.mohasebeHazineMamooriatService = mohasebeHazineMamooriatService;
    }

    /**
     * {@code POST  /mohasebe-hazine-mamooriats} : Create a new mohasebeHazineMamooriat.
     *
     * @param mohasebeHazineMamooriatDTO the mohasebeHazineMamooriatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mohasebeHazineMamooriatDTO, or with status {@code 400 (Bad Request)} if the mohasebeHazineMamooriat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mohasebe-hazine-mamooriats")
    public ResponseEntity<MohasebeHazineMamooriatDTO> createMohasebeHazineMamooriat(@RequestBody MohasebeHazineMamooriatDTO mohasebeHazineMamooriatDTO) throws URISyntaxException {
        log.debug("REST request to save MohasebeHazineMamooriat : {}", mohasebeHazineMamooriatDTO);
        if (mohasebeHazineMamooriatDTO.getId() != null) {
            throw new BadRequestAlertException("A new mohasebeHazineMamooriat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MohasebeHazineMamooriatDTO result = mohasebeHazineMamooriatService.save(mohasebeHazineMamooriatDTO);
        return ResponseEntity.created(new URI("/api/mohasebe-hazine-mamooriats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mohasebe-hazine-mamooriats} : Updates an existing mohasebeHazineMamooriat.
     *
     * @param mohasebeHazineMamooriatDTO the mohasebeHazineMamooriatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mohasebeHazineMamooriatDTO,
     * or with status {@code 400 (Bad Request)} if the mohasebeHazineMamooriatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mohasebeHazineMamooriatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mohasebe-hazine-mamooriats")
    public ResponseEntity<MohasebeHazineMamooriatDTO> updateMohasebeHazineMamooriat(@RequestBody MohasebeHazineMamooriatDTO mohasebeHazineMamooriatDTO) throws URISyntaxException {
        log.debug("REST request to update MohasebeHazineMamooriat : {}", mohasebeHazineMamooriatDTO);
        if (mohasebeHazineMamooriatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MohasebeHazineMamooriatDTO result = mohasebeHazineMamooriatService.save(mohasebeHazineMamooriatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mohasebeHazineMamooriatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mohasebe-hazine-mamooriats} : get all the mohasebeHazineMamooriats.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mohasebeHazineMamooriats in body.
     */
    @GetMapping("/mohasebe-hazine-mamooriats")
    public ResponseEntity<List<MohasebeHazineMamooriatDTO>> getAllMohasebeHazineMamooriats(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all MohasebeHazineMamooriats where hesabResi is null");
            return new ResponseEntity<>(mohasebeHazineMamooriatService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of MohasebeHazineMamooriats");
        Page<MohasebeHazineMamooriatDTO> page = mohasebeHazineMamooriatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mohasebe-hazine-mamooriats/:id} : get the "id" mohasebeHazineMamooriat.
     *
     * @param id the id of the mohasebeHazineMamooriatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mohasebeHazineMamooriatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mohasebe-hazine-mamooriats/{id}")
    public ResponseEntity<MohasebeHazineMamooriatDTO> getMohasebeHazineMamooriat(@PathVariable Long id) {
        log.debug("REST request to get MohasebeHazineMamooriat : {}", id);
        Optional<MohasebeHazineMamooriatDTO> mohasebeHazineMamooriatDTO = mohasebeHazineMamooriatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mohasebeHazineMamooriatDTO);
    }

    /**
     * {@code DELETE  /mohasebe-hazine-mamooriats/:id} : delete the "id" mohasebeHazineMamooriat.
     *
     * @param id the id of the mohasebeHazineMamooriatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mohasebe-hazine-mamooriats/{id}")
    public ResponseEntity<Void> deleteMohasebeHazineMamooriat(@PathVariable Long id) {
        log.debug("REST request to delete MohasebeHazineMamooriat : {}", id);
        mohasebeHazineMamooriatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
