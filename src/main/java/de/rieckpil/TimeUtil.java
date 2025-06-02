package de.rieckpil;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

/**
 * Develop a feature to display information when a comment was made (one day ago, 3 days ago, 6
 * month ago, etc.) in a human-readable format: - A comment that is older than 365 days, should
 * return 'more than a year'. - A comment within today should return 'today'. - A date in the future
 * is invalid and should throw an exception.
 */
@Component
public class TimeUtil {

  private final TimeProvider timeProvider;

  public TimeUtil(TimeProvider timeProvider) {
    this.timeProvider = timeProvider;
  }

  public String getDiffBetweenCreationDate(LocalDate creationDate) {
    LocalDate currentDate = timeProvider.getCurrentDate();
    if (creationDate.isAfter(currentDate)) {
      throw new IllegalArgumentException("Date in the future is not valid");
    }

    if (creationDate.equals(timeProvider.getCurrentDate())) {
      return "today";
    }

    return null;
  }
}
