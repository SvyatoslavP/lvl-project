package ru.panifidkin.lvlproject.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panifidkin.lvlproject.converters.TeamToTeamDtoConverter;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByIdRq;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByNameRq;
import ru.panifidkin.lvlproject.rs.ErrorInfo;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByIdRs;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByNameRs;
import ru.panifidkin.lvlproject.rs.Status;
import ru.panifidkin.lvlproject.rs.TeamDto;
import ru.panifidkin.lvlproject.serializers.FrontendTeamSerializer;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FrontendTeamService {

    public static final String STATUS_ERROR = "1";
    public static final String STATUS_SUCCESS = "0";
    public static final String STATUS_NOT_FOUND = "7";
    public static final String NOT_FOUND_BY_ID_MESSAGE = "Команда с id : %s не найдена";
    public static final String NOT_FOUND_BY_NAME_MESSAGE = "Команда с именем : %s не найдена";

    private final FrontendTeamSerializer serializer;
    private final TeamService teamService;
    private final TeamToTeamDtoConverter converter;

    public String findTeamById(String requestBody) {
        String rs;
        try {
            SrvGetTeamByIdRq rq = serializer.idDeserialize(requestBody);
            rs = Optional.ofNullable(rq.getTeamId())
                    .map(teamService::findById)
                    .map(converter::convert)
                    .map(this::createIdSuccess)
                    .map(serializer::idSerialize)
                    .orElseGet(() -> serializeIdError(String.format(NOT_FOUND_BY_ID_MESSAGE, rq.getTeamId()), STATUS_NOT_FOUND));
        } catch (Exception e) {
            log.error("getTeamById: {}", requestBody);
            rs = serializeIdError(e.getMessage(), STATUS_ERROR);
        }
        return rs;
    }

    public String getTeamByName(String requestBody) {
        String rs;
        try {
            SrvGetTeamByNameRq rq = serializer.nameDeserialize(requestBody);
            rs = Optional.ofNullable(rq.getTeamName())
                    .map(teamService::findByName)
                    .map(converter::convert)
                    .map(this::createNameSuccess)
                    .map(serializer::nameSerialize)
                    .orElseGet(() -> serializeNameError(String.format(NOT_FOUND_BY_NAME_MESSAGE, rq.getTeamName()), STATUS_NOT_FOUND));
        } catch (Exception e) {
            log.error("getTeamByName: {}", requestBody);
            rs = serializeIdError(e.getMessage(), STATUS_ERROR);
        }
        return rs;
    }

    private String serializeIdError(String message, String code) {
        return serializer.idSerialize(createErrorIdRs(message, code));
    }

    private String serializeNameError(String message, String code) {
        return serializer.nameSerialize(createErrorNameRs(message, code));
    }

    private SrvGetTeamByIdRs createIdSuccess(TeamDto team) {
        return SrvGetTeamByIdRs.builder()
                .team(team)
                .status(successStatus())
                .build();
    }

    private SrvGetTeamByNameRs createNameSuccess(TeamDto team) {
        return SrvGetTeamByNameRs.builder()
                .team(team)
                .status(successStatus())
                .build();
    }

    private SrvGetTeamByIdRs createErrorIdRs(String message, String code) {
        return SrvGetTeamByIdRs.builder()
                .status(errorStatus(message, code))
                .build();
    }

    private SrvGetTeamByNameRs createErrorNameRs(String message, String code) {
        return SrvGetTeamByNameRs.builder()
                .status(errorStatus(message, code))
                .build();
    }

    private Status errorStatus(String value, String code) {
        return Status.builder()
                .statusCode(code)
                .errorInfo(ErrorInfo.builder()
                        .value(value)
                        .build())
                .build();
    }

    private Status successStatus() {
        return Status.builder()
                .statusCode(STATUS_SUCCESS)
                .build();
    }
}
