package ir.saha.web.rest;

import ir.saha.service.PayamService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.PayamDTO;

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
 * REST controller for managing {@link ir.saha.domain.Payam}.
 */
@RestController
@RequestMapping("/api")
public class PayamResource {

    private final Logger log = LoggerFactory.getLogger(PayamResource.class);

    private static final String ENTITY_NAME = "payam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayamService payamService;

    public PayamResource(PayamService payamService) {
        this.payamService = payamService;
    }

    /**
     * {@code POST  /payams} : Create a new payam.
     *
     * @param payamDTO the payamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payamDTO, or with status {@code 400 (Bad Request)} if the payam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payams")
    public ResponseEntity<PayamDTO> createPayam(@RequestBody PayamDTO payamDTO) throws URISyntaxException {
        log.debug("REST request to save Payam : {}", payamDTO);
        if (payamDTO.getId() != null) {
            throw new BadRequestAlertException("A new payam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayamDTO result = payamService.save(payamDTO);
        return ResponseEntity.created(new URI("/api/payams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payams} : Updates an existing payam.
     *
     * @param payamDTO the payamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payamDTO,
     * or with status {@code 400 (Bad Request)} if the payamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payams")
    public ResponseEntity<PayamDTO> updatePayam(@RequestBody PayamDTO payamDTO) throws URISyntaxException {
        log.debug("REST request to update Payam : {}", payamDTO);
        if (payamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PayamDTO result = payamService.save(payamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, payamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payams} : get all the payams.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payams in body.
     */
    @GetMapping("/payams")
    public ResponseEntity<List<PayamDTO>> getAllPayams(Pageable pageable) {
        log.debug("REST request to get a page of Payams");
        Page<PayamDTO> page = payamService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payams/:id} : get the "id" payam.
     *
     * @param id the id of the payamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payams/{id}")
    public ResponseEntity<PayamDTO> getPayam(@PathVariable Long id) {
        log.debug("REST request to get Payam : {}", id);
        Optional<PayamDTO> payamDTO = payamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payamDTO);
    }

    /**
     * {@code DELETE  /payams/:id} : delete the "id" payam.
     *
     * @param id the id of the payamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payams/{id}")
    public ResponseEntity<Void> deletePayam(@PathVariable Long id) {
        log.debug("REST request to delete Payam : {}", id);
        payamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
