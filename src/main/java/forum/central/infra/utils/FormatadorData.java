package forum.central.infra.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorData {
    public static String formatarDataPadraoBrasil(LocalDateTime data) {
        @SuppressWarnings("deprecation")
        var padrao = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", new Locale("pt", "BR"));
        return data.format(padrao);
    }
}
