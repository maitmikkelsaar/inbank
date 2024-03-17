package ee.mikkelsaar.inbankapi.controller.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class ApiValidationErrors {
  private final List<String> errors = new ArrayList<>();
}
