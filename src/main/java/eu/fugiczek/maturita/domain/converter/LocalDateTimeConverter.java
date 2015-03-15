package eu.fugiczek.maturita.domain.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements
		AttributeConverter<LocalDateTime, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDateTime dateTime) {
		if (dateTime == null) {
			return null;
		}
		
		final Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date dbData) {
		if (dbData == null) {
			return null;
		}
		
		final Instant instant = Instant.ofEpochMilli(dbData.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

}
