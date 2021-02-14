package ir.saha.web.rest;

import ir.saha.service.HesabResiService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.HesabResiDTO;

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

/**
 * REST controller for managing {@link ir.saha.domain.HesabResi}.
 */
@RestController
@RequestMapping("/api")
public class HesabResiResource {

    private final Logger log = LoggerFactory.getLogger(HesabResiResource.class);

    private static final String ENTITY_NAME = "hesabResi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HesabResiService hesabResiService;

    public HesabResiResource(HesabResiService hesabResiService) {
        this.hesabResiService = hesabResiService;
    }

    /**
     * {@code POST  /hesab-resis} : Create a new hesabResi.
     *
     * @param hesabResiDTO the hesabResiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hesabResiDTO, or with status {@code 400 (Bad Request)} if the hesabResi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hesab-resis")
    public ResponseEntity<HesabResiDTO> createHesabResi(@RequestBody HesabResiDTO hesabResiDTO) throws URISyntaxException {
        log.debug("REST request to save HesabResi : {}", hesabResiDTO);
        if (hesabResiDTO.getId() != null) {
            throw new BadRequestAlertException("A new hesabResi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HesabResiDTO result = hesabResiService.save(hesabResiDTO);
        return ResponseEntity.created(new URI("/api/hesab-resis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hesab-resis} : Updates an existing hesabResi.
     *
     * @param hesabResiDTO the hesabResiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hesabResiDTO,
     * or with status {@code 400 (Bad Request)} if the hesabResiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hesabResiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hesab-resis")
    public ResponseEntity<HesabResiDTO> updateHesabResi(@RequestBody HesabResiDTO hesabResiDTO) throws URISyntaxException {
        log.debug("REST request to update HesabResi : {}", hesabResiDTO);
        if (hesabResiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HesabResiDTO result = hesabResiService.update(hesabResiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hesabResiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /hesab-resis} : get all the hesabResis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hesabResis in body.
     */
    @GetMapping("/hesab-resis")
    public ResponseEntity<List<HesabResiDTO>> getAllHesabResis(Pageable pageable) {
        log.debug("REST request to get a page of HesabResis");
        Page<HesabResiDTO> page = hesabResiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hesab-resis/:id} : get the "id" hesabResi.
     *
     * @param id the id of the hesabResiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hesabResiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hesab-resis/{id}")
    public ResponseEntity<HesabResiDTO> getHesabResi(@PathVariable Long id) {
        log.debug("REST request to get HesabResi : {}", id);
        Optional<HesabResiDTO> hesabResiDTO = hesabResiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hesabResiDTO);
    }

    /**
     * {@code DELETE  /hesab-resis/:id} : delete the "id" hesabResi.
     *
     * @param id the id of the hesabResiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hesab-resis/{id}")
    public ResponseEntity<Void> deleteHesabResi(@PathVariable Long id) {
        log.debug("REST request to delete HesabResi : {}", id);
        hesabResiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
