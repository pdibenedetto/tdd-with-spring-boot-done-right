package de.rieckpil;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeUtilTests {

  @Mock private TimeProvider timeProvider;

  @InjectMocks private TimeUtil cut;

  @BeforeEach
  void init() {}

  @Test
  void shouldThrowExceptionWhenDateIsInFuture() {
    // given
    LocalDate creationDateInTheFuture = LocalDate.now().plusDays(1);

    // when
    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          cut.getDiffBetweenCreationDate(creationDateInTheFuture);
        });
  }

  @Test
  void shouldReturnTodayWhenCompletionDateIsWithinToday() {
    // given
    LocalDate creationDate = LocalDate.now();

    // when
    when(timeProvider.getCurrentDate()).thenReturn(LocalDate.now());

    // then
    assertEquals("today", cut.getDiffBetweenCreationDate(creationDate));
  }
}
