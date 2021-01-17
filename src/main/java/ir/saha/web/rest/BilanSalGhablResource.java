package ir.saha.web.rest;

import ir.saha.service.BilanSalGhablService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.BilanSalGhablDTO;

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
 * REST controller for managing {@link ir.saha.domain.BilanSalGhabl}.
 */
@RestController
@RequestMapping("/api")
public class BilanSalGhablResource {

    private final Logger log = LoggerFactory.getLogger(BilanSalGhablResource.class);

    private static final String ENTITY_NAME = "bilanSalGhabl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BilanSalGhablService bilanSalGhablService;

    public BilanSalGhablResource(BilanSalGhablService bilanSalGhablService) {
        this.bilanSalGhablService = bilanSalGhablService;
    }

    /**
     * {@code POST  /bilan-sal-ghabls} : Create a new bilanSalGhabl.
     *
     * @param bilanSalGhablDTO the bilanSalGhablDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bilanSalGhablDTO, or with status {@code 400 (Bad Request)} if the bilanSalGhabl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bilan-sal-ghabls")
    public ResponseEntity<BilanSalGhablDTO> createBilanSalGhabl(@RequestBody BilanSalGhablDTO bilanSalGhablDTO) throws URISyntaxException {
        log.debug("REST request to save BilanSalGhabl : {}", bilanSalGhablDTO);
        if (bilanSalGhablDTO.getId() != null) {
            throw new BadRequestAlertException("A new bilanSalGhabl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BilanSalGhablDTO result = bilanSalGhablService.save(bilanSalGhablDTO);
        return ResponseEntity.created(new URI("/api/bilan-sal-ghabls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bilan-sal-ghabls} : Updates an existing bilanSalGhabl.
     *
     * @param bilanSalGhablDTO the bilanSalGhablDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bilanSalGhablDTO,
     * or with status {@code 400 (Bad Request)} if the bilanSalGhablDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bilanSalGhablDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bilan-sal-ghabls")
    public ResponseEntity<BilanSalGhablDTO> updateBilanSalGhabl(@RequestBody BilanSalGhablDTO bilanSalGhablDTO) throws URISyntaxException {
        log.debug("REST request to update BilanSalGhabl : {}", bilanSalGhablDTO);
        if (bilanSalGhablDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BilanSalGhablDTO result = bilanSalGhablService.save(bilanSalGhablDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bilanSalGhablDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bilan-sal-ghabls} : get all the bilanSalGhabls.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bilanSalGhabls in body.
     */
    @GetMapping("/bilan-sal-ghabls")
    public ResponseEntity<List<BilanSalGhablDTO>> getAllBilanSalGhabls(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all BilanSalGhabls where hesabResi is null");
            return new ResponseEntity<>(bilanSalGhablService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of BilanSalGhabls");
        Page<BilanSalGhablDTO> page = bilanSalGhablService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bilan-sal-ghabls/:id} : get the "id" bilanSalGhabl.
     *
     * @param id the id of the bilanSalGhablDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bilanSalGhablDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bilan-sal-ghabls/{id}")
    public ResponseEntity<BilanSalGhablDTO> getBilanSalGhabl(@PathVariable Long id) {
        log.debug("REST request to get BilanSalGhabl : {}", id);
        Optional<BilanSalGhablDTO> bilanSalGhablDTO = bilanSalGhablService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bilanSalGhablDTO);
    }

    /**
     * {@code DELETE  /bilan-sal-ghabls/:id} : delete the "id" bilanSalGhabl.
     *
     * @param id the id of the bilanSalGhablDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bilan-sal-ghabls/{id}")
    public ResponseEntity<Void> deleteBilanSalGhabl(@PathVariable Long id) {
        log.debug("REST request to delete BilanSalGhabl : {}", id);
        bilanSalGhablService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
