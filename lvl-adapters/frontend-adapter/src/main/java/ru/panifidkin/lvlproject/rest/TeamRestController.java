package ru.panifidkin.lvlproject.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.panifidkin.lvlproject.services.FrontendTeamService;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static ru.panifidkin.lvlproject.utils.LvlProjectUtils.REQUEST_TRANSPORT_ID_HEADER;
import static ru.panifidkin.lvlproject.utils.Objects.isNullOrEmpty;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TeamRestController {

    private final FrontendTeamService service;

    @PostMapping(path = "/getTeamById",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTeamById(@RequestHeader HttpHeaders headers,
                                              @RequestBody String requestBody) {
        log.info("getTeamById: {}", requestBody);
        String rqUuid = headers.getFirst(REQUEST_TRANSPORT_ID_HEADER);
        if (isNullOrEmpty(rqUuid) || isNullOrEmpty(requestBody)) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return ResponseEntity.ok()
                .body(service.findTeamById(requestBody));
    }

    @PostMapping(path = "/getTeamByName",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTeamByName(@RequestHeader HttpHeaders headers,
                                              @RequestBody String requestBody) {
        log.info("getTeamByName: {}", requestBody);
        String rqUuid = headers.getFirst(REQUEST_TRANSPORT_ID_HEADER);
        if (isNullOrEmpty(rqUuid) || isNullOrEmpty(requestBody)) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
        return ResponseEntity.ok()
                .body(service.getTeamByName(requestBody));
    }
}