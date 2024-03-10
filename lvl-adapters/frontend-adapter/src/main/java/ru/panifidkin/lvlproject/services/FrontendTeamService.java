package ru.panifidkin.lvlproject.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panifidkin.lvlproject.converters.TeamToTeamDtoConverter;
import ru.panifidkin.lvlproject.rq.SrvGetTeamByIdRq;
import ru.panifidkin.lvlproject.rs.ErrorInfo;
import ru.panifidkin.lvlproject.rs.SrvGetTeamByIdRs;
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
    public static final String NOT_FOUND_MESSAGE = "Команда с id : %s не найден";

    private final FrontendTeamSerializer serializer;
    private final TeamService teamService;
    private final TeamToTeamDtoConverter converter;

    public String findTeamById(String requestBody) {
        String rs;
        try {
            SrvGetTeamByIdRq rq = serializer.deserialize(requestBody);
            rs = Optional.ofNullable(rq.getTeamId())
                    .map(teamService::findById)
                    .map(converter::convert)
                    .map(this::createSuccess)
                    .map(serializer::serialize)
                    .orElseGet(() -> serializeError(String.format(NOT_FOUND_MESSAGE, rq.getTeamId()), STATUS_NOT_FOUND));
        } catch (Exception e) {
            log.error("getTeamById: {}", requestBody);
            rs = serializeError(e.getMessage(), STATUS_ERROR);
        }
        return rs;
    }

    private String serializeError(String message, String code) {
        return serializer.serialize(createErrorRs(message, code));
    }

    private SrvGetTeamByIdRs createSuccess(TeamDto team) {
        return SrvGetTeamByIdRs.builder()
                .team(team)
                .status(successStatus())
                .build();
    }

    private SrvGetTeamByIdRs createErrorRs(String message, String code) {
        return SrvGetTeamByIdRs.builder()
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
