package pl.wojcik.stripeinvoices.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceIdGenerator {

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
