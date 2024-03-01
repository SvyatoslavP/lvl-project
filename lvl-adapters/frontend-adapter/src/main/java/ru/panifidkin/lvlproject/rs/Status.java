package ru.panifidkin.lvlproject.rs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Status {
    String statusCode;
    ErrorInfo errorInfo;
}
