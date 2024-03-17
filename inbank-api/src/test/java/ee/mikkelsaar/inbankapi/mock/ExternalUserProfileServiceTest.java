package ee.mikkelsaar.inbankapi.mock;

import static org.junit.jupiter.api.Assertions.*;

import ee.mikkelsaar.inbankapi.model.SEGMENT;
import ee.mikkelsaar.inbankapi.profile.impl.MockUserProfileServiceImpl;
import org.junit.jupiter.api.Test;

class ExternalUserProfileServiceTest {

  private final MockUserProfileServiceImpl externalUserProfileService = new MockUserProfileServiceImpl();

  @Test
  void getUserSegment_exception_null() {
    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> externalUserProfileService.getUserSegment(null));
    assertEquals(MockUserProfileServiceImpl.PERSONAL_CODE_IS_REQUIRED, exception.getMessage());
  }

  @Test
  void getUserSegment_exception_empty() {
    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> externalUserProfileService.getUserSegment(""));
    assertEquals(MockUserProfileServiceImpl.PERSONAL_CODE_IS_REQUIRED, exception.getMessage());
  }

  @Test
  void getUserSegment_exception_whitespace() {
    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> externalUserProfileService.getUserSegment(" "));
    assertEquals(MockUserProfileServiceImpl.PERSONAL_CODE_IS_REQUIRED, exception.getMessage());
  }

  @Test
  void getUserSegment_DEBT_49002010965() {
    final SEGMENT segment = externalUserProfileService.getUserSegment("49002010965");
    assertEquals(SEGMENT.DEBT, segment);
  }

  @Test
  void getUserSegment_SEGMENT_1_49002010976() {
    final SEGMENT segment = externalUserProfileService.getUserSegment("49002010976");
    assertEquals(SEGMENT.SEGMENT_1, segment);
  }

  @Test
  void getUserSegment_SEGMENT_2_49002010987() {
    final SEGMENT segment = externalUserProfileService.getUserSegment("49002010987");
    assertEquals(SEGMENT.SEGMENT_2, segment);
  }

  @Test
  void getUserSegment_SEGMENT_3_49002010998() {
    final SEGMENT segment = externalUserProfileService.getUserSegment("49002010998");
    assertEquals(SEGMENT.SEGMENT_3, segment);
  }

  @Test
  void getUserSegment_exception_00000000000() {
    final String personalCode = "00000000000";
    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> externalUserProfileService.getUserSegment(personalCode));
    assertEquals(MockUserProfileServiceImpl.USER_SEGMENT_NOT_FOUND_FOR_PERSONAL_CODE + personalCode, exception.getMessage());
  }
}