package ir.saha.web.rest;

import ir.saha.service.MadarekService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.MadarekDTO;

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
 * REST controller for managing {@link ir.saha.domain.Madarek}.
 */
@RestController
@RequestMapping("/api")
public class MadarekResource {

    private final Logger log = LoggerFactory.getLogger(MadarekResource.class);

    private static final String ENTITY_NAME = "madarek";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MadarekService madarekService;

    public MadarekResource(MadarekService madarekService) {
        this.madarekService = madarekService;
    }

    /**
     * {@code POST  /madareks} : Create a new madarek.
     *
     * @param madarekDTO the madarekDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new madarekDTO, or with status {@code 400 (Bad Request)} if the madarek has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/madareks")
    public ResponseEntity<MadarekDTO> createMadarek(@RequestBody MadarekDTO madarekDTO) throws URISyntaxException {
        log.debug("REST request to save Madarek : {}", madarekDTO);
        if (madarekDTO.getId() != null) {
            throw new BadRequestAlertException("A new madarek cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MadarekDTO result = madarekService.save(madarekDTO);
        return ResponseEntity.created(new URI("/api/madareks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /madareks} : Updates an existing madarek.
     *
     * @param madarekDTO the madarekDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated madarekDTO,
     * or with status {@code 400 (Bad Request)} if the madarekDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the madarekDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/madareks")
    public ResponseEntity<MadarekDTO> updateMadarek(@RequestBody MadarekDTO madarekDTO) throws URISyntaxException {
        log.debug("REST request to update Madarek : {}", madarekDTO);
        if (madarekDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MadarekDTO result = madarekService.save(madarekDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, madarekDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /madareks} : get all the madareks.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of madareks in body.
     */
    @GetMapping("/madareks")
    public ResponseEntity<List<MadarekDTO>> getAllMadareks(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all Madareks where hesabResi is null");
            return new ResponseEntity<>(madarekService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Madareks");
        Page<MadarekDTO> page = madarekService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /madareks/:id} : get the "id" madarek.
     *
     * @param id the id of the madarekDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the madarekDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/madareks/{id}")
    public ResponseEntity<MadarekDTO> getMadarek(@PathVariable Long id) {
        log.debug("REST request to get Madarek : {}", id);
        Optional<MadarekDTO> madarekDTO = madarekService.findOne(id);
        return ResponseUtil.wrapOrNotFound(madarekDTO);
    }

    /**
     * {@code DELETE  /madareks/:id} : delete the "id" madarek.
     *
     * @param id the id of the madarekDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/madareks/{id}")
    public ResponseEntity<Void> deleteMadarek(@PathVariable Long id) {
        log.debug("REST request to delete Madarek : {}", id);
        madarekService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
