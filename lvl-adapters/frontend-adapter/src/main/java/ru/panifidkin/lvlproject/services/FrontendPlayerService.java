package ru.panifidkin.lvlproject.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.panifidkin.lvlproject.converters.PlayerToPlayerDtoConverter;
import ru.panifidkin.lvlproject.rq.SrvGetPlayerByIdRq;
import ru.panifidkin.lvlproject.rs.ErrorInfo;
import ru.panifidkin.lvlproject.rs.PlayerDto;
import ru.panifidkin.lvlproject.rs.SrvGetPlayerByIdRs;
import ru.panifidkin.lvlproject.rs.Status;
import ru.panifidkin.lvlproject.serializers.FrontendPlayerSerializer;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FrontendPlayerService {

    private static final String STATUS_ERROR = "1";
    private static final String STATUS_SUCCESS = "0";
    private static final String STATUS_NOT_FOUND = "7";
    private static final String NOT_FOUND_MESSAGE = "Игрок с id : %s не найден";

    private final FrontendPlayerSerializer serializer;
    private final PlayerService playerService;
    private final PlayerToPlayerDtoConverter converter;

    public String findPlayerById(String requestBody) {
        String rs;
        try {
            SrvGetPlayerByIdRq rq = serializer.deserialize(requestBody);
            rs = Optional.ofNullable(rq.getPlayerId())
                    .map(playerService::findById)
                    .map(converter::convert)
                    .map(this::createSuccess)
                    .map(serializer::serialize)
                    .orElse(serializeError(String.format(NOT_FOUND_MESSAGE, rq.getPlayerId()), STATUS_NOT_FOUND));
        } catch (Exception e) {
            log.error("getById: {}", requestBody);
            rs = serializeError(e.getMessage(), STATUS_ERROR);
        }
        return rs;
    }

    private String serializeError(String message, String code) {
        return serializer.serialize(createErrorRs(message, code));
    }

    private SrvGetPlayerByIdRs createSuccess(PlayerDto player) {
        return SrvGetPlayerByIdRs.builder()
                .player(player)
                .status(successStatus())
                .build();
    }

    private SrvGetPlayerByIdRs createErrorRs(String message, String code) {
        return SrvGetPlayerByIdRs.builder()
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
