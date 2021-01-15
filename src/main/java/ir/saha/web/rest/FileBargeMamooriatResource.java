package ir.saha.web.rest;

import ir.saha.domain.FileBargeMamooriat;
import ir.saha.repository.FileBargeMamooriatRepository;
import ir.saha.service.FileBargeMamooriatService;
import ir.saha.web.rest.errors.BadRequestAlertException;
import ir.saha.service.dto.FileBargeMamooriatDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ir.saha.domain.FileBargeMamooriat}.
 */
@RestController
@RequestMapping("/api")
public class FileBargeMamooriatResource {

    private final Logger log = LoggerFactory.getLogger(FileBargeMamooriatResource.class);

    private static final String ENTITY_NAME = "fileBargeMamooriat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileBargeMamooriatService fileBargeMamooriatService;
    private final FileBargeMamooriatRepository fileBargeMamooriatRepository;



    public FileBargeMamooriatResource(FileBargeMamooriatService fileBargeMamooriatService, FileBargeMamooriatRepository fileBargeMamooriatRepository) {
        this.fileBargeMamooriatService = fileBargeMamooriatService;
        this.fileBargeMamooriatRepository = fileBargeMamooriatRepository;
    }

    /**
     * {@code POST  /file-barge-mamooriats} : Create a new fileBargeMamooriat.
     *
     * @param fileBargeMamooriatDTO the fileBargeMamooriatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileBargeMamooriatDTO, or with status {@code 400 (Bad Request)} if the fileBargeMamooriat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-barge-mamooriats")
    public ResponseEntity<FileBargeMamooriatDTO> createFileBargeMamooriat(@RequestBody FileBargeMamooriatDTO fileBargeMamooriatDTO) throws URISyntaxException {
        log.debug("REST request to save FileBargeMamooriat : {}", fileBargeMamooriatDTO);
        if (fileBargeMamooriatDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileBargeMamooriat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileBargeMamooriatDTO result = fileBargeMamooriatService.save(fileBargeMamooriatDTO);
        return ResponseEntity.created(new URI("/api/file-barge-mamooriats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/barge-mamooriats/file/{id}")
    public ResponseEntity<InputStreamResource> getBargeMamooriat(@PathVariable Long id) throws FileNotFoundException {
        log.debug("REST request to get BargeMamooriat : {}", id);
        FileBargeMamooriat file = fileBargeMamooriatRepository.findById(id).get();
        InputStreamResource resource = new InputStreamResource( new ByteArrayInputStream(file.getMadarek()) );

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getFileName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
       return ResponseEntity.ok()
           .headers(header)
            .contentLength(file.getMadarek().length)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }


    /**
     * {@code PUT  /file-barge-mamooriats} : Updates an existing fileBargeMamooriat.
     *
     * @param fileBargeMamooriatDTO the fileBargeMamooriatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileBargeMamooriatDTO,
     * or with status {@code 400 (Bad Request)} if the fileBargeMamooriatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileBargeMamooriatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-barge-mamooriats")
    public ResponseEntity<FileBargeMamooriatDTO> updateFileBargeMamooriat(@RequestBody FileBargeMamooriatDTO fileBargeMamooriatDTO) throws URISyntaxException {
        log.debug("REST request to update FileBargeMamooriat : {}", fileBargeMamooriatDTO);
        if (fileBargeMamooriatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileBargeMamooriatDTO result = fileBargeMamooriatService.save(fileBargeMamooriatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileBargeMamooriatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-barge-mamooriats} : get all the fileBargeMamooriats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileBargeMamooriats in body.
     */
    @GetMapping("/file-barge-mamooriats")
    public ResponseEntity<List<FileBargeMamooriatDTO>> getAllFileBargeMamooriats(Pageable pageable) {
        log.debug("REST request to get a page of FileBargeMamooriats");
        Page<FileBargeMamooriatDTO> page = fileBargeMamooriatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/file-barge-mamooriats/barge-mamooriat/{barge-mamooriat-id}")
    public ResponseEntity<List<FileBargeMamooriatDTO>> getBargemamooriatFileBargeMamooriats(@PathVariable(name = "barge-mamooriat-id")Long idbargeMamooriate ,Pageable pageable) {
        log.debug("REST request to get a page of FileBargeMamooriats");
        Page<FileBargeMamooriatDTO> page = fileBargeMamooriatService.fileBargeMamooriat(idbargeMamooriate,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-barge-mamooriats/:id} : get the "id" fileBargeMamooriat.
     *
     * @param id the id of the fileBargeMamooriatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileBargeMamooriatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-barge-mamooriats/{id}")
    public ResponseEntity<FileBargeMamooriatDTO> getFileBargeMamooriat(@PathVariable Long id) {
        log.debug("REST request to get FileBargeMamooriat : {}", id);
        Optional<FileBargeMamooriatDTO> fileBargeMamooriatDTO = fileBargeMamooriatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileBargeMamooriatDTO);
    }

    /**
     * {@code DELETE  /file-barge-mamooriats/:id} : delete the "id" fileBargeMamooriat.
     *
     * @param id the id of the fileBargeMamooriatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-barge-mamooriats/{id}")
    public ResponseEntity<Void> deleteFileBargeMamooriat(@PathVariable Long id) {
        log.debug("REST request to delete FileBargeMamooriat : {}", id);
        fileBargeMamooriatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
