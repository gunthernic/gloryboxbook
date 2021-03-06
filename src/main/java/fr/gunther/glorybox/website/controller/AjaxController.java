package fr.gunther.glorybox.website.controller;

import fr.gunther.glorybox.website.dto.*;
import fr.gunther.glorybox.website.service.BoxService;
import fr.gunther.glorybox.website.service.CommandService;
import fr.gunther.glorybox.website.service.CountryDeliveryService;
import fr.gunther.glorybox.website.service.StaticDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class AjaxController {

    @Autowired
    private BoxService boxService;

    @Autowired
    private CommandService commandService;

    @Autowired
    private StaticDataService staticDataService;

    @Autowired
    private CountryDeliveryService countryDeliveryService;

    @ResponseBody
    @RequestMapping(value = "/update/description")
    public ResponseEntity<?> updateDescriptionBox(@RequestBody AjaxRequestDescriptionDTO request) {
        staticDataService.updateByKey("description",request.getDescription());
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value = "/update/price")
    public ResponseEntity<?> updatePriceBox(@RequestBody AjaxRequestPriceDTO request) {
        boxService.updatePrice(request.getPrice());
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value="/update/status/validate")
    public ResponseEntity<?> updateStatusToValidate(@RequestBody AjaxRequestStatusValidateDTO request) {
        commandService.updateStatusToValidate(Long.parseLong(request.getId()));
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value="/update/status/archived")
    public ResponseEntity<?> updateStatusToArchived(@RequestBody AjaxRequestStatusArchivedDTO request) {
        commandService.updateStatusToArchived(Long.parseLong(request.getId()), request.getLink());
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value="/delete")
    public ResponseEntity<?> deleteCommand(@RequestBody AjaxRequestDeleteDTO request) {
        commandService.deleteCommand(Long.parseLong(request.getId()));
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value="/create/box")
    public ResponseEntity<?> createBox(@RequestBody AjaxRequestCreateBoxDTO request) {
        BoxDTO box = boxService.createBox(request.getName(), request.getPrice(), new Date());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        request.setId(box.getId().toString());
        request.setCreationDate(now.format(formatter));
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value="/delete/box")
    public ResponseEntity<?> deleteBox(@RequestBody AjaxRequestDeleteDTO request) {
        boxService.deleteBox(request.getId());
        return ResponseEntity.ok(request);
    }

    @ResponseBody
    @RequestMapping(value="/price/country/{idcountry}")
    public ResponseEntity<?> getPriceCountryDelivery(@PathVariable("idcountry") Long idcountry) {
        Float price = countryDeliveryService.getPriceByCountryId(idcountry);
        AjaxResponsePriceCountry response = new AjaxResponsePriceCountry();
        response.setPrice(price);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping(value="/price/box/{idbox}")
    public ResponseEntity<?> getPriceBox(@PathVariable("idbox") Long idbox) {
        Float price = boxService.getPriceBox(idbox);
        AjaxResponsePriceCountry response = new AjaxResponsePriceCountry();
        response.setPrice(price);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping(value="/update/price/{idcountry}")
    public ResponseEntity<?> getPriceBox(@RequestBody AjaxResponsePriceCountry request,@PathVariable("idcountry") Long idcountry) {
        countryDeliveryService.updatePrice(request.getPrice(), idcountry);
        return ResponseEntity.ok(request);
    }
}
