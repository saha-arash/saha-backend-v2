package ir.saha.web.rest;

import ir.saha.service.BilanSeSalGhablService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.BilanSeSalGhablDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link ir.saha.domain.BilanSeSalGhabl}.
 */
@RestController
@RequestMapping("/api")
public class BilanSeSalGhablResource {

    private final Logger log = LoggerFactory.getLogger(BilanSeSalGhablResource.class);

    private static final String ENTITY_NAME = "bilanSeSalGhabl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BilanSeSalGhablService bilanSeSalGhablService;

    public BilanSeSalGhablResource(BilanSeSalGhablService bilanSeSalGhablService) {
        this.bilanSeSalGhablService = bilanSeSalGhablService;
    }

    /**
     * {@code POST  /bilan-se-sal-ghabls} : Create a new bilanSeSalGhabl.
     *
     * @param bilanSeSalGhablDTO the bilanSeSalGhablDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bilanSeSalGhablDTO, or with status {@code 400 (Bad Request)} if the bilanSeSalGhabl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bilan-se-sal-ghabls")
    public ResponseEntity<BilanSeSalGhablDTO> createBilanSeSalGhabl(@RequestBody BilanSeSalGhablDTO bilanSeSalGhablDTO) throws URISyntaxException {
        log.debug("REST request to save BilanSeSalGhabl : {}", bilanSeSalGhablDTO);
        if (bilanSeSalGhablDTO.getId() != null) {
            throw new BadRequestAlertException("A new bilanSeSalGhabl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BilanSeSalGhablDTO result = bilanSeSalGhablService.save(bilanSeSalGhablDTO);
        return ResponseEntity.created(new URI("/api/bilan-se-sal-ghabls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bilan-se-sal-ghabls} : Updates an existing bilanSeSalGhabl.
     *
     * @param bilanSeSalGhablDTO the bilanSeSalGhablDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bilanSeSalGhablDTO,
     * or with status {@code 400 (Bad Request)} if the bilanSeSalGhablDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bilanSeSalGhablDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bilan-se-sal-ghabls")
    public ResponseEntity<BilanSeSalGhablDTO> updateBilanSeSalGhabl(@RequestBody BilanSeSalGhablDTO bilanSeSalGhablDTO) throws URISyntaxException {
        log.debug("REST request to update BilanSeSalGhabl : {}", bilanSeSalGhablDTO);
        if (bilanSeSalGhablDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BilanSeSalGhablDTO result = bilanSeSalGhablService.save(bilanSeSalGhablDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bilanSeSalGhablDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bilan-se-sal-ghabls} : get all the bilanSeSalGhabls.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bilanSeSalGhabls in body.
     */
    @GetMapping("/bilan-se-sal-ghabls")
    public ResponseEntity<List<BilanSeSalGhablDTO>> getAllBilanSeSalGhabls(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all BilanSeSalGhabls where hesabResi is null");
            return new ResponseEntity<>(bilanSeSalGhablService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of BilanSeSalGhabls");
        Page<BilanSeSalGhablDTO> page = bilanSeSalGhablService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bilan-se-sal-ghabls/:id} : get the "id" bilanSeSalGhabl.
     *
     * @param id the id of the bilanSeSalGhablDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bilanSeSalGhablDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bilan-se-sal-ghabls/{id}")
    public ResponseEntity<BilanSeSalGhablDTO> getBilanSeSalGhabl(@PathVariable Long id) {
        log.debug("REST request to get BilanSeSalGhabl : {}", id);
        Optional<BilanSeSalGhablDTO> bilanSeSalGhablDTO = bilanSeSalGhablService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bilanSeSalGhablDTO);
    }

    /**
     * {@code DELETE  /bilan-se-sal-ghabls/:id} : delete the "id" bilanSeSalGhabl.
     *
     * @param id the id of the bilanSeSalGhablDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bilan-se-sal-ghabls/{id}")
    public ResponseEntity<Void> deleteBilanSeSalGhabl(@PathVariable Long id) {
        log.debug("REST request to delete BilanSeSalGhabl : {}", id);
        bilanSeSalGhablService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping(path = "/sesal/excel/{sal}")
    public ResponseEntity<byte[]> download(@PathVariable(name = "sal") String sal) throws IOException {
        // ...

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("2-بیلان3سال_گذشته.xlsx");

        //        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().contentLength(inputStream.available())
            .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
            .cacheControl(CacheControl.noCache())
            .header("Content-Disposition", "attachment; filename=" + "SYSTEM_GENERATED_FILE_NM")
            .body(new InputStreamResource(inputStream));
    }
}
