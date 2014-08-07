package ly.cel.jvinsights.models;

import ly.cel.jvinsights.AbstractEvent;

public class TestEvent extends AbstractEvent {
  public static String EVENT_TYPE = "TEST_EVENT";
  
  public String testDataOne;
  public String testDataTwo;
  
  public TestEvent(String testDataOne, String testDataTwo) {
    super();
    this.setEventType(EVENT_TYPE);
    this.testDataOne = testDataOne;
    this.testDataTwo = testDataTwo;
  }

  @Override
  public AbstractEvent clone() {
    return new TestEvent(this.testDataOne, this.testDataTwo);
  }

}
