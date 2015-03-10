package eu.fugiczek.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDateTime dateTime) {
		Instant instant = Instant.from(dateTime);
        return Date.from(instant);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date dbData) {
		Instant instant = dbData.toInstant();
        return LocalDateTime.from(instant);
	}

}
