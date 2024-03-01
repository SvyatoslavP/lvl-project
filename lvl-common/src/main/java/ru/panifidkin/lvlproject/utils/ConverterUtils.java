package ru.panifidkin.lvlproject.utils;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.panifidkin.lvlproject.utils.Objects.applyIfNotNull;

@UtilityClass
public class ConverterUtils {

    public static String convertDateToString(@Nullable LocalDate date) {
        return applyIfNotNull(date, it-> dateToIsoStr(date));
    }

    private static String dateToIsoStr(@Nonnull LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
