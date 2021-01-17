package ir.saha.web.rest;

import ir.saha.service.ChekideGardeshKarService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.ChekideGardeshKarDTO;

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
 * REST controller for managing {@link ir.saha.domain.ChekideGardeshKar}.
 */
@RestController
@RequestMapping("/api")
public class ChekideGardeshKarResource {

    private final Logger log = LoggerFactory.getLogger(ChekideGardeshKarResource.class);

    private static final String ENTITY_NAME = "chekideGardeshKar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChekideGardeshKarService chekideGardeshKarService;

    public ChekideGardeshKarResource(ChekideGardeshKarService chekideGardeshKarService) {
        this.chekideGardeshKarService = chekideGardeshKarService;
    }

    /**
     * {@code POST  /chekide-gardesh-kars} : Create a new chekideGardeshKar.
     *
     * @param chekideGardeshKarDTO the chekideGardeshKarDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chekideGardeshKarDTO, or with status {@code 400 (Bad Request)} if the chekideGardeshKar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chekide-gardesh-kars")
    public ResponseEntity<ChekideGardeshKarDTO> createChekideGardeshKar(@RequestBody ChekideGardeshKarDTO chekideGardeshKarDTO) throws URISyntaxException {
        log.debug("REST request to save ChekideGardeshKar : {}", chekideGardeshKarDTO);
        if (chekideGardeshKarDTO.getId() != null) {
            throw new BadRequestAlertException("A new chekideGardeshKar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChekideGardeshKarDTO result = chekideGardeshKarService.save(chekideGardeshKarDTO);
        return ResponseEntity.created(new URI("/api/chekide-gardesh-kars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chekide-gardesh-kars} : Updates an existing chekideGardeshKar.
     *
     * @param chekideGardeshKarDTO the chekideGardeshKarDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chekideGardeshKarDTO,
     * or with status {@code 400 (Bad Request)} if the chekideGardeshKarDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chekideGardeshKarDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chekide-gardesh-kars")
    public ResponseEntity<ChekideGardeshKarDTO> updateChekideGardeshKar(@RequestBody ChekideGardeshKarDTO chekideGardeshKarDTO) throws URISyntaxException {
        log.debug("REST request to update ChekideGardeshKar : {}", chekideGardeshKarDTO);
        if (chekideGardeshKarDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChekideGardeshKarDTO result = chekideGardeshKarService.save(chekideGardeshKarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chekideGardeshKarDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chekide-gardesh-kars} : get all the chekideGardeshKars.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chekideGardeshKars in body.
     */
    @GetMapping("/chekide-gardesh-kars")
    public ResponseEntity<List<ChekideGardeshKarDTO>> getAllChekideGardeshKars(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all ChekideGardeshKars where hesabResi is null");
            return new ResponseEntity<>(chekideGardeshKarService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ChekideGardeshKars");
        Page<ChekideGardeshKarDTO> page = chekideGardeshKarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chekide-gardesh-kars/:id} : get the "id" chekideGardeshKar.
     *
     * @param id the id of the chekideGardeshKarDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chekideGardeshKarDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chekide-gardesh-kars/{id}")
    public ResponseEntity<ChekideGardeshKarDTO> getChekideGardeshKar(@PathVariable Long id) {
        log.debug("REST request to get ChekideGardeshKar : {}", id);
        Optional<ChekideGardeshKarDTO> chekideGardeshKarDTO = chekideGardeshKarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chekideGardeshKarDTO);
    }

    /**
     * {@code DELETE  /chekide-gardesh-kars/:id} : delete the "id" chekideGardeshKar.
     *
     * @param id the id of the chekideGardeshKarDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chekide-gardesh-kars/{id}")
    public ResponseEntity<Void> deleteChekideGardeshKar(@PathVariable Long id) {
        log.debug("REST request to delete ChekideGardeshKar : {}", id);
        chekideGardeshKarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
