package ir.saha.web.rest;

import ir.saha.service.BankEtelaatiService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.BankEtelaatiDTO;

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
 * REST controller for managing {@link ir.saha.domain.BankEtelaati}.
 */
@RestController
@RequestMapping("/api")
public class BankEtelaatiResource {

    private final Logger log = LoggerFactory.getLogger(BankEtelaatiResource.class);

    private static final String ENTITY_NAME = "bankEtelaati";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankEtelaatiService bankEtelaatiService;

    public BankEtelaatiResource(BankEtelaatiService bankEtelaatiService) {
        this.bankEtelaatiService = bankEtelaatiService;
    }

    /**
     * {@code POST  /bank-etelaatis} : Create a new bankEtelaati.
     *
     * @param bankEtelaatiDTO the bankEtelaatiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankEtelaatiDTO, or with status {@code 400 (Bad Request)} if the bankEtelaati has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bank-etelaatis")
    public ResponseEntity<BankEtelaatiDTO> createBankEtelaati(@RequestBody BankEtelaatiDTO bankEtelaatiDTO) throws URISyntaxException {
        log.debug("REST request to save BankEtelaati : {}", bankEtelaatiDTO);
        if (bankEtelaatiDTO.getId() != null) {
            throw new BadRequestAlertException("A new bankEtelaati cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankEtelaatiDTO result = bankEtelaatiService.save(bankEtelaatiDTO);
        return ResponseEntity.created(new URI("/api/bank-etelaatis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bank-etelaatis} : Updates an existing bankEtelaati.
     *
     * @param bankEtelaatiDTO the bankEtelaatiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankEtelaatiDTO,
     * or with status {@code 400 (Bad Request)} if the bankEtelaatiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankEtelaatiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bank-etelaatis")
    public ResponseEntity<BankEtelaatiDTO> updateBankEtelaati(@RequestBody BankEtelaatiDTO bankEtelaatiDTO) throws URISyntaxException {
        log.debug("REST request to update BankEtelaati : {}", bankEtelaatiDTO);
        if (bankEtelaatiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankEtelaatiDTO result = bankEtelaatiService.save(bankEtelaatiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankEtelaatiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bank-etelaatis} : get all the bankEtelaatis.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankEtelaatis in body.
     */
    @GetMapping("/bank-etelaatis")
    public ResponseEntity<List<BankEtelaatiDTO>> getAllBankEtelaatis(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("hesabresi-is-null".equals(filter)) {
            log.debug("REST request to get all BankEtelaatis where hesabResi is null");
            return new ResponseEntity<>(bankEtelaatiService.findAllWhereHesabResiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of BankEtelaatis");
        Page<BankEtelaatiDTO> page = bankEtelaatiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-etelaatis/:id} : get the "id" bankEtelaati.
     *
     * @param id the id of the bankEtelaatiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankEtelaatiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-etelaatis/{id}")
    public ResponseEntity<BankEtelaatiDTO> getBankEtelaati(@PathVariable Long id) {
        log.debug("REST request to get BankEtelaati : {}", id);
        Optional<BankEtelaatiDTO> bankEtelaatiDTO = bankEtelaatiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankEtelaatiDTO);
    }

    /**
     * {@code DELETE  /bank-etelaatis/:id} : delete the "id" bankEtelaati.
     *
     * @param id the id of the bankEtelaatiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bank-etelaatis/{id}")
    public ResponseEntity<Void> deleteBankEtelaati(@PathVariable Long id) {
        log.debug("REST request to delete BankEtelaati : {}", id);
        bankEtelaatiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
