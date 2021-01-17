package ir.saha.web.rest;

import ir.saha.service.MostaKhrejeService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.MostaKhrejeDTO;

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
 * REST controller for managing {@link ir.saha.domain.MostaKhreje}.
 */
@RestController
@RequestMapping("/api")
public class MostaKhrejeResource {

    private final Logger log = LoggerFactory.getLogger(MostaKhrejeResource.class);

    private static final String ENTITY_NAME = "mostaKhreje";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MostaKhrejeService mostaKhrejeService;

    public MostaKhrejeResource(MostaKhrejeService mostaKhrejeService) {
        this.mostaKhrejeService = mostaKhrejeService;
    }

    /**
     * {@code POST  /mosta-khrejes} : Create a new mostaKhreje.
     *
     * @param mostaKhrejeDTO the mostaKhrejeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mostaKhrejeDTO, or with status {@code 400 (Bad Request)} if the mostaKhreje has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mosta-khrejes")
    public ResponseEntity<MostaKhrejeDTO> createMostaKhreje(@RequestBody MostaKhrejeDTO mostaKhrejeDTO) throws URISyntaxException {
        log.debug("REST request to save MostaKhreje : {}", mostaKhrejeDTO);
        if (mostaKhrejeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mostaKhreje cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MostaKhrejeDTO result = mostaKhrejeService.save(mostaKhrejeDTO);
        return ResponseEntity.created(new URI("/api/mosta-khrejes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mosta-khrejes} : Updates an existing mostaKhreje.
     *
     * @param mostaKhrejeDTO the mostaKhrejeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mostaKhrejeDTO,
     * or with status {@code 400 (Bad Request)} if the mostaKhrejeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mostaKhrejeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mosta-khrejes")
    public ResponseEntity<MostaKhrejeDTO> updateMostaKhreje(@RequestBody MostaKhrejeDTO mostaKhrejeDTO) throws URISyntaxException {
        log.debug("REST request to update MostaKhreje : {}", mostaKhrejeDTO);
        if (mostaKhrejeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MostaKhrejeDTO result = mostaKhrejeService.save(mostaKhrejeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mostaKhrejeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mosta-khrejes} : get all the mostaKhrejes.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mostaKhrejes in body.
     */
    @GetMapping("/mosta-khrejes")
    public ResponseEntity<List<MostaKhrejeDTO>> getAllMostaKhrejes(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all MostaKhrejes where hesabResi is null");
            return new ResponseEntity<>(mostaKhrejeService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of MostaKhrejes");
        Page<MostaKhrejeDTO> page = mostaKhrejeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mosta-khrejes/:id} : get the "id" mostaKhreje.
     *
     * @param id the id of the mostaKhrejeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mostaKhrejeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mosta-khrejes/{id}")
    public ResponseEntity<MostaKhrejeDTO> getMostaKhreje(@PathVariable Long id) {
        log.debug("REST request to get MostaKhreje : {}", id);
        Optional<MostaKhrejeDTO> mostaKhrejeDTO = mostaKhrejeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mostaKhrejeDTO);
    }

    /**
     * {@code DELETE  /mosta-khrejes/:id} : delete the "id" mostaKhreje.
     *
     * @param id the id of the mostaKhrejeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mosta-khrejes/{id}")
    public ResponseEntity<Void> deleteMostaKhreje(@PathVariable Long id) {
        log.debug("REST request to delete MostaKhreje : {}", id);
        mostaKhrejeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
