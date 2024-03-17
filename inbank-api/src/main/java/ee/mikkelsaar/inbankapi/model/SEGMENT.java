package ee.mikkelsaar.inbankapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum SEGMENT {

  DEBT(null),
  SEGMENT_1(100),
  SEGMENT_2(300),
  SEGMENT_3(1000),
  ;

  private final Integer creditModifier;
}
