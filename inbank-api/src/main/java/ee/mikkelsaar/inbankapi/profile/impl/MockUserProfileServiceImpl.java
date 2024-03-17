package ee.mikkelsaar.inbankapi.profile.impl;

import ee.mikkelsaar.inbankapi.model.SEGMENT;
import ee.mikkelsaar.inbankapi.profile.ExternalUserProfileService;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MockUserProfileServiceImpl implements ExternalUserProfileService {

  public static final String PERSONAL_CODE_IS_REQUIRED = "Personal code is required";
  public static final String USER_SEGMENT_NOT_FOUND_FOR_PERSONAL_CODE = "User segment not found for personal code: ";

  // Depending on the user profile response and actual usage throughout the system, the response might be cached
  // to avoid unnecessary calls to the external system.
  @Override
  public SEGMENT getUserSegment(final String personalCode) {
    if (StringUtils.isBlank(personalCode)) {
      throw new IllegalArgumentException(PERSONAL_CODE_IS_REQUIRED);
    } else if ("49002010965".equals(personalCode)) {
      return SEGMENT.DEBT;
    } else if ("49002010976".equals(personalCode)) {
      return SEGMENT.SEGMENT_1;
    } else if ("49002010987".equals(personalCode)) {
      return SEGMENT.SEGMENT_2;
    } else if ("49002010998".equals(personalCode)) {
      return SEGMENT.SEGMENT_3;
    } else {
      throw new IllegalArgumentException(USER_SEGMENT_NOT_FOUND_FOR_PERSONAL_CODE + personalCode);
    }
  }
}
