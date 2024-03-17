package ee.mikkelsaar.inbankapi.profile;

import ee.mikkelsaar.inbankapi.model.SEGMENT;

public interface ExternalUserProfileService {

  SEGMENT getUserSegment(String personalCode);
}
