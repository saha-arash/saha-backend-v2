package ir.saha.web.rest;

import ir.saha.service.YeganTypeService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.YeganTypeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ir.saha.domain.YeganType}.
 */
@RestController
@RequestMapping("/api")
public class YeganTypeResource {

    private final Logger log = LoggerFactory.getLogger(YeganTypeResource.class);

    private static final String ENTITY_NAME = "yeganType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final YeganTypeService yeganTypeService;

    public YeganTypeResource(YeganTypeService yeganTypeService) {
        this.yeganTypeService = yeganTypeService;
    }

    /**
     * {@code POST  /yegan-types} : Create a new yeganType.
     *
     * @param yeganTypeDTO the yeganTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new yeganTypeDTO, or with status {@code 400 (Bad Request)} if the yeganType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/yegan-types")
    public ResponseEntity<YeganTypeDTO> createYeganType(@RequestBody YeganTypeDTO yeganTypeDTO) throws URISyntaxException {
        log.debug("REST request to save YeganType : {}", yeganTypeDTO);
        if (yeganTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new yeganType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        YeganTypeDTO result = yeganTypeService.save(yeganTypeDTO);
        return ResponseEntity.created(new URI("/api/yegan-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /yegan-types} : Updates an existing yeganType.
     *
     * @param yeganTypeDTO the yeganTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated yeganTypeDTO,
     * or with status {@code 400 (Bad Request)} if the yeganTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the yeganTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/yegan-types")
    public ResponseEntity<YeganTypeDTO> updateYeganType(@RequestBody YeganTypeDTO yeganTypeDTO) throws URISyntaxException {
        log.debug("REST request to update YeganType : {}", yeganTypeDTO);
        if (yeganTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        YeganTypeDTO result = yeganTypeService.save(yeganTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, yeganTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /yegan-types} : get all the yeganTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of yeganTypes in body.
     */
    @GetMapping("/yegan-types")
    public ResponseEntity<List<YeganTypeDTO>> getAllYeganTypes(Pageable pageable) {
        log.debug("REST request to get a page of YeganTypes");
        Page<YeganTypeDTO> page = yeganTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /yegan-types/:id} : get the "id" yeganType.
     *
     * @param id the id of the yeganTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the yeganTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/yegan-types/{id}")
    public ResponseEntity<YeganTypeDTO> getYeganType(@PathVariable Long id) {
        log.debug("REST request to get YeganType : {}", id);
        Optional<YeganTypeDTO> yeganTypeDTO = yeganTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(yeganTypeDTO);
    }

    /**
     * {@code DELETE  /yegan-types/:id} : delete the "id" yeganType.
     *
     * @param id the id of the yeganTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/yegan-types/{id}")
    public ResponseEntity<Void> deleteYeganType(@PathVariable Long id) {
        log.debug("REST request to delete YeganType : {}", id);
        yeganTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
