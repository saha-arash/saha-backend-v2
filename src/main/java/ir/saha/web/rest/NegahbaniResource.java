package ir.saha.web.rest;

import ir.saha.service.NegahbaniService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.NegahbaniDTO;

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
 * REST controller for managing {@link ir.saha.domain.Negahbani}.
 */
@RestController
@RequestMapping("/api")
public class NegahbaniResource {

    private final Logger log = LoggerFactory.getLogger(NegahbaniResource.class);

    private static final String ENTITY_NAME = "negahbani";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NegahbaniService negahbaniService;

    public NegahbaniResource(NegahbaniService negahbaniService) {
        this.negahbaniService = negahbaniService;
    }

    /**
     * {@code POST  /negahbanis} : Create a new negahbani.
     *
     * @param negahbaniDTO the negahbaniDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new negahbaniDTO, or with status {@code 400 (Bad Request)} if the negahbani has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/negahbanis")
    public ResponseEntity<NegahbaniDTO> createNegahbani(@RequestBody NegahbaniDTO negahbaniDTO) throws URISyntaxException {
        log.debug("REST request to save Negahbani : {}", negahbaniDTO);
        if (negahbaniDTO.getId() != null) {
            throw new BadRequestAlertException("A new negahbani cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NegahbaniDTO result = negahbaniService.save(negahbaniDTO);
        return ResponseEntity.created(new URI("/api/negahbanis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /negahbanis} : Updates an existing negahbani.
     *
     * @param negahbaniDTO the negahbaniDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated negahbaniDTO,
     * or with status {@code 400 (Bad Request)} if the negahbaniDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the negahbaniDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/negahbanis")
    public ResponseEntity<NegahbaniDTO> updateNegahbani(@RequestBody NegahbaniDTO negahbaniDTO) throws URISyntaxException {
        log.debug("REST request to update Negahbani : {}", negahbaniDTO);
        if (negahbaniDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NegahbaniDTO result = negahbaniService.save(negahbaniDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, negahbaniDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /negahbanis} : get all the negahbanis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of negahbanis in body.
     */
    @GetMapping("/negahbanis")
    public ResponseEntity<List<NegahbaniDTO>> getAllNegahbanis(Pageable pageable) {
        log.debug("REST request to get a page of Negahbanis");
        Page<NegahbaniDTO> page = negahbaniService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /negahbanis/:id} : get the "id" negahbani.
     *
     * @param id the id of the negahbaniDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the negahbaniDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/negahbanis/{id}")
    public ResponseEntity<NegahbaniDTO> getNegahbani(@PathVariable Long id) {
        log.debug("REST request to get Negahbani : {}", id);
        Optional<NegahbaniDTO> negahbaniDTO = negahbaniService.findOne(id);
        return ResponseUtil.wrapOrNotFound(negahbaniDTO);
    }

    /**
     * {@code DELETE  /negahbanis/:id} : delete the "id" negahbani.
     *
     * @param id the id of the negahbaniDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/negahbanis/{id}")
    public ResponseEntity<Void> deleteNegahbani(@PathVariable Long id) {
        log.debug("REST request to delete Negahbani : {}", id);
        negahbaniService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
