package ir.saha.web.rest;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import ir.saha.domain.BargeMamooriat;
import ir.saha.domain.Karbar;
import ir.saha.domain.Yegan;
import ir.saha.domain.enumeration.VaziatGozaresh;
import ir.saha.domain.enumeration.VaziateHesabResi;
import ir.saha.repository.BargeMamooriatRepository;
import ir.saha.repository.KarbarRepository;
import ir.saha.repository.YeganRepository;
import ir.saha.service.BargeMamooriatService;
import ir.saha.service.dto.BargeMamooriatDTO;
import ir.saha.service.dto.FilterBargeMamooriat;
import ir.saha.service.dto.TamamBargemamooriatHa;
import ir.saha.web.rest.errors.BadRequestAlertException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link ir.saha.domain.BargeMamooriat}.
 */
@RestController
@RequestMapping("/api")
public class BargeMamooriatResource {

    private final Logger log = LoggerFactory.getLogger(BargeMamooriatResource.class);

    private static final String ENTITY_NAME = "bargeMamooriat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BargeMamooriatService bargeMamooriatService;
    private final YeganRepository yeganRepository;
    private final KarbarRepository karbarRepository;
    private final BargeMamooriatRepository bargeMamooriatRepository;

    public BargeMamooriatResource(BargeMamooriatService bargeMamooriatService, YeganRepository yeganRepository, KarbarRepository karbarRepository, BargeMamooriatRepository bargeMamooriatRepository) {
        this.bargeMamooriatService = bargeMamooriatService;
        this.yeganRepository = yeganRepository;
        this.karbarRepository = karbarRepository;
        this.bargeMamooriatRepository = bargeMamooriatRepository;
    }

    /**
     * {@code POST  /barge-mamooriats} : Create a new bargeMamooriat.
     *
     * @param bargeMamooriatDTO the bargeMamooriatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bargeMamooriatDTO, or with status {@code 400 (Bad Request)} if the bargeMamooriat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/barge-mamooriats")
    public ResponseEntity<BargeMamooriatDTO> createBargeMamooriat(@RequestBody BargeMamooriatDTO bargeMamooriatDTO) throws URISyntaxException {
        log.debug("REST request to save BargeMamooriat : {}", bargeMamooriatDTO);
        if (bargeMamooriatDTO.getId() != null) {
            throw new BadRequestAlertException("A new bargeMamooriat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BargeMamooriatDTO result = bargeMamooriatService.save(bargeMamooriatDTO);
        return ResponseEntity.created(new URI("/api/barge-mamooriats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /barge-mamooriats} : Updates an existing bargeMamooriat.
     *
     * @param bargeMamooriatDTO the bargeMamooriatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bargeMamooriatDTO,
     * or with status {@code 400 (Bad Request)} if the bargeMamooriatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bargeMamooriatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/barge-mamooriats")
    public ResponseEntity<BargeMamooriatDTO> updateBargeMamooriat(@RequestBody BargeMamooriatDTO bargeMamooriatDTO) throws URISyntaxException {
        log.debug("REST request to update BargeMamooriat : {}", bargeMamooriatDTO);
        if (bargeMamooriatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BargeMamooriatDTO result = bargeMamooriatService.save(bargeMamooriatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bargeMamooriatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /barge-mamooriats} : get all the bargeMamooriats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bargeMamooriats in body.
     */
    @Secured({"ROLE_YEGAN","ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/barge-mamooriats/user")
    public ResponseEntity<Set<BargeMamooriatDTO>> getCurrentUserMamooriat(FilterBargeMamooriat bargeMamooriat) {
        log.debug("REST request to get a page of BargeMamooriats");
        Set<BargeMamooriatDTO> page = bargeMamooriatService.getCurrentUserMamooriat(bargeMamooriat);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/barge-mamooriats")
    public ResponseEntity<List<BargeMamooriatDTO>> getAllBargeMamooriats(Pageable pageable) {
        log.debug("REST request to get a page of BargeMamooriats");
        Page<BargeMamooriatDTO>  page = bargeMamooriatService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/barge-mamooriats/user/{user-id}")
    public ResponseEntity<List<BargeMamooriatDTO>> getAllByUserId(FilterBargeMamooriat bargeMamooriat, @PathVariable(name="user-id") Long userId) {
        log.debug("REST request to get a page of BargeMamooriats");
        List<BargeMamooriatDTO> page = bargeMamooriatService.getUserMamooriat(bargeMamooriat, userId);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /barge-mamooriats/:id} : get the "id" bargeMamooriat.
     *
     * @param id the id of the bargeMamooriatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bargeMamooriatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/barge-mamooriats/{id}")
    public ResponseEntity<BargeMamooriatDTO> getBargeMamooriat(@PathVariable Long id) {
        log.debug("REST request to get BargeMamooriat : {}", id);
        Optional<BargeMamooriatDTO> bargeMamooriatDTO = bargeMamooriatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bargeMamooriatDTO);
    }


    /**
     * {@code DELETE  /barge-mamooriats/:id} : delete the "id" bargeMamooriat.
     *
     * @param id the id of the bargeMamooriatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/barge-mamooriats/{id}")
    public ResponseEntity<Void> deleteBargeMamooriat(@PathVariable Long id) {
        log.debug("REST request to delete BargeMamooriat : {}", id);
        bargeMamooriatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/excel-file/{sal}")
    @Transactional
    public  ResponseEntity<StreamingResponseBody> gozareshMamooriat(@PathVariable(name = "sal") Integer sal) {
        List<BargeMamooriat> bySal = bargeMamooriatRepository.findAllBySaleMamooriat(sal);
        Map<String, List<BargeMamooriat>> groupByNiroo = bySal.stream()
            .collect(Collectors.groupingBy(b -> b.getYegan().getNirooCode().getName()));

        Workbook workBook = new HSSFWorkbook();
        Sheet sheet = workBook.createSheet("My Sheet");
        Row rowHeader= sheet.createRow(0);
        rowHeader.setHeight((short) 500);
        Cell cellHeader = getCellHead(IndexedColors.ROYAL_BLUE,40, workBook, rowHeader);
        cellHeader.setCellValue("شماره گزارش");
        Cell cellHeader2 = getCellHead(IndexedColors.ROYAL_BLUE,39, workBook, rowHeader);
        cellHeader2.setCellValue("تاریخ  مأموریت");
        Cell cellHeader3 = getCellHead(IndexedColors.ROYAL_BLUE,38, workBook, rowHeader);
        cellHeader3.setCellValue("مدت مأموریت");

        Cell cellHeader4 = getCellHead(IndexedColors.ROYAL_BLUE,37, workBook, rowHeader);
        cellHeader4.setCellValue("نام یگان");

        Cell cellHeader5 = getCellHead(IndexedColors.ROYAL_BLUE,36, workBook, rowHeader);
        cellHeader5.setCellValue("مدیریت");

        Cell cellHeader6 = getCellHead(IndexedColors.ROYAL_BLUE,35, workBook, rowHeader);
        cellHeader6.setCellValue("سرپرست تیم ");
        Cell cellHeader7 = getCellHead(IndexedColors.ROYAL_BLUE,34, workBook, rowHeader);
        cellHeader7.setCellValue("اعضا ");

        Cell cellHeader8 = getCellHead(IndexedColors.ROYAL_BLUE,33, workBook, rowHeader);
        cellHeader8.setCellValue("وضعیت گزارش ");
//        sheet.autoSizeColumn(40);
//        sheet.autoSizeColumn(39);
//        sheet.autoSizeColumn(38);
//        sheet.autoSizeColumn(37);
//        sheet.autoSizeColumn(36);
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(10);
        for (int i = 1; i <= bySal.size(); i++) {
            int j=40;
            BargeMamooriat bargeMamooriat = bySal.get(i-1);
            Row row = sheet.createRow(i);
            row.setHeight((short) 500);
            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setWrapText(true);
            row.setRowStyle(cellStyle);

            Cell cell0 = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            cell0.setCellValue(bargeMamooriat.getHesabResi().getGozaresh().getId());
            Cell cell = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            ULocale locale = new ULocale("fa_IR@calendar=persian");
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            cell.setCellValue(outputFormat.format(Date.from(bargeMamooriat.getShorooMamooriat())));

            if (bargeMamooriat.getPayanMamooriat()!=null) {
                Cell cell2 = getCell(j--,workBook, row);
                sheet.autoSizeColumn(j);
                cell2.setCellValue(getDifferenceDays(Date.from(bargeMamooriat.getShorooMamooriat()),Date.from(bargeMamooriat.getPayanMamooriat())));
            }
            Cell cell3 = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            Yegan yegan = bargeMamooriat.getYegan();
            cell3.setCellValue(yegan.getName());

            Cell cell4 = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            cell4.setCellValue(yegan.getNirooCode().getName());


            Karbar karbar = bargeMamooriat.getSarparast();
            Cell cell5 = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            cell5.setCellValue(karbar.getName());



            Cell cell6 = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            cell6.setCellValue(bargeMamooriat.getNafars().stream().map(Karbar::getName).collect(Collectors.joining("\n")));

            Cell cell7 = getCell(j--,workBook, row);
            sheet.autoSizeColumn(j);
            cell7.setCellValue(VaziatGozaresh.getByEnum(bargeMamooriat.getHesabResi().getGozaresh().getVaziat()));

        }
        Row rowH1 = sheet.createRow( bySal.size() + 5);
        Cell cellHeaderH1Down = getCellHead(IndexedColors.YELLOW,38, workBook, rowH1);
        cellHeaderH1Down.setCellValue("وضعیت مأموریتها");

        Cell cellHeaderH2Down = getCellHead(IndexedColors.PALE_BLUE,32, workBook, rowH1);
        cellHeaderH2Down.setCellValue("وضعیت گزارش ها");

        Row row = sheet.createRow( bySal.size() + 6);
        Cell cellHeaderDown = getCellHead(IndexedColors.PINK,40, workBook, row);
        cellHeaderDown.setCellValue("نیرو");
        Cell cellHeaderDown2 = getCellHead(IndexedColors.YELLOW,39, workBook, row);
        cellHeaderDown2.setCellValue("مأموریت های انجام شده");
        Cell cellHeaderDown3 = getCellHead(IndexedColors.YELLOW,38, workBook, row);
        cellHeaderDown3.setCellValue("در حال مأموریت");

        Cell cellHeaderDown4 = getCellHead(IndexedColors.YELLOW,37, workBook, row);
        cellHeaderDown4.setCellValue("در شرف اجرا");
        Cell cellHeaderDown44 = getCellHead(IndexedColors.YELLOW,36, workBook, row);
        cellHeaderDown44.setCellValue("صدور برگه مموریت");

        Cell cellHeaderDown5 = getCellHead(IndexedColors.PALE_BLUE,35, workBook, row);
        cellHeaderDown5.setCellValue("اولیه");

        Cell cellHeaderDown6 = getCellHead(IndexedColors.PALE_BLUE,34, workBook, row);
        cellHeaderDown6.setCellValue("ابلاغ گزارش به یگان ");
        Cell cellHeaderDown7 = getCellHead(IndexedColors.PALE_BLUE,33, workBook, row);
        cellHeaderDown7.setCellValue("هیت رییسه سازمان ");

        Cell cellHeaderDown8 = getCellHead(IndexedColors.PALE_BLUE,32, workBook, row);
        cellHeaderDown8.setCellValue("مدیر");

        Cell cellHeaderDown9= getCellHead(IndexedColors.PALE_BLUE,31, workBook, row);
        cellHeaderDown9.setCellValue("نهایی");

        Cell cellHeaderDown10= getCellHead(IndexedColors.PALE_BLUE,30, workBook, row);
        cellHeaderDown10.setCellValue("معاونت");

        AtomicInteger startRow = new AtomicInteger(bySal.size() + 7);
        groupByNiroo.forEach((k,v)->{
            Row rowdown = sheet.createRow(startRow.getAndIncrement());
            rowdown.setHeight((short) 500);
            AtomicInteger j= new AtomicInteger(40);
            Cell cell1 = getCell(j.getAndDecrement(),workBook, rowdown);
            sheet.autoSizeColumn(j.get());
            cell1.setCellValue(k);
            Map<VaziateHesabResi, List<BargeMamooriat>> vaziateBargeMamooriat = v.stream().collect(Collectors.groupingBy(bargeMamooriat -> bargeMamooriat.getHesabResi().getVaziateHesabResi()));
            if (vaziateBargeMamooriat.get(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN)!=null){
                int shorof = vaziateBargeMamooriat.get(VaziateHesabResi.ETMAM_MAMOORIAT_HOZOOR_DARSAZMAN).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(shorof);
            }
            if (vaziateBargeMamooriat.get(VaziateHesabResi.DAR_HALE_MAMOORIAT)!=null){
                int sodoor = vaziateBargeMamooriat.get(VaziateHesabResi.DAR_HALE_MAMOORIAT).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
            if (vaziateBargeMamooriat.get(VaziateHesabResi.DAR_SHOROF_MAMOORIAT)!=null){
                int sodoor = vaziateBargeMamooriat.get(VaziateHesabResi.DAR_HALE_MAMOORIAT).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
            if (vaziateBargeMamooriat.get(VaziateHesabResi.SODOOR_BARGE_MAMOORIAT)!=null){
                int sodoor = vaziateBargeMamooriat.get(VaziateHesabResi.SODOOR_BARGE_MAMOORIAT).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }

            Map<VaziatGozaresh, List<BargeMamooriat>> collect = v.stream().collect(Collectors.groupingBy(b -> b.getHesabResi().getGozaresh().getVaziat()));
            if (collect.get(VaziatGozaresh.AVALIE)!=null){
                int sodoor = collect.get(VaziatGozaresh.AVALIE).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
            if (collect.get(VaziatGozaresh.EBLAGH_GOZARESH_BEYEGAN_HESABRESI_SHAVANDE)!=null){
                int sodoor = collect.get(VaziatGozaresh.EBLAGH_GOZARESH_BEYEGAN_HESABRESI_SHAVANDE).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
            if (collect.get(VaziatGozaresh.HEYAT_RAESE_SAZMAN)!=null){
                int sodoor = collect.get(VaziatGozaresh.HEYAT_RAESE_SAZMAN).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
            if (collect.get(VaziatGozaresh.MODIR)!=null){
                int sodoor = collect.get(VaziatGozaresh.MODIR).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
            if (collect.get(VaziatGozaresh.HEYATRAESE_AJA_NAHAE)!=null){
                int sodoor = collect.get(VaziatGozaresh.HEYATRAESE_AJA_NAHAE).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }

            if (collect.get(VaziatGozaresh.MOAVENAT)!=null){
                int sodoor = collect.get(VaziatGozaresh.MOAVENAT).size();
                Cell cell2 = getCell(j.getAndDecrement(),workBook, rowdown);
                sheet.autoSizeColumn(j.get());
                cell2.setCellValue(sodoor);
            }
        });

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"myfilename.xlsx\"")
            .body(workBook::write);
    }

    private Cell getCell(int cellNumber,Workbook workBook, Row row) {
        Cell cell = row.createCell(cellNumber);
        Font font = workBook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("B Nazanin");
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setBold(true);
        font.setItalic(false);

        CellStyle style = workBook.createCellStyle();
//            style.setFillForegroundColor(IndexedColors.BROWN.getIndex());
//            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setWrapText(true);
        // Setting font to style
        style.setFillBackgroundColor(IndexedColors.BLUE1.getIndex());
        style.setFont(font);

        cell.setCellStyle(style);
        return cell;
    }
    private Cell getCellHead(IndexedColors color,int cellNumber, Workbook workBook, Row row) {
        Cell cell = row.createCell(cellNumber);
        Font font = workBook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("B Nazanin");
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setBold(true);
        font.setItalic(false);

        CellStyle style = workBook.createCellStyle();
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setWrapText(true);
        // Setting font to style
        style.setFillBackgroundColor(IndexedColors.BLUE1.getIndex());
        style.setFont(font);

        cell.setCellStyle(style);
        return cell;
    }

    public int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }
}
