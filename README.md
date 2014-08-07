# jvinsights

A convenient interface for using New Relics Insights. Based off
of the PHP library by Sobit Akhmedov at https://raw.githubusercontent.com/sobit/insights/.

## Install

Get the jar in the out directory, and add it to your projects class path.

## Usage

### 1. Creating your event

Create your event which extends ```ly.cel.jvinsights.AbstractEvent``` class and set preferred event type.

``` java
public class MyEvent extends ly.cel.jvinsights.AbstractEvent {
    public static String EVENT_TYPE = "SomeEvent";

    public MyEvent() {
        super();
        this.setEventType(EVENT_TYPE);
    }

    @Override
    public AbstractEvent clone() {
        return new MyEvent();
    }
}
```

Populate this class with attributes you want to be submitted to Insights:

``` java
public class MyEvent extends ly.cel.jvinsights.AbstractEvent {
    public static String EVENT_TYPE = "SomeEvent";

    private String myAttribute;

    public MyEvent(String myAttribute) {
        super();
        this.setEventType(EVENT_TYPE);
        this.myAttribute = myAttribute;
    }

    @Override
    public AbstractEvent clone() {
        return new MyEvent(this.myAttribute);
    }
}
```

### 2. Submitting events

``` java
import ly.cel.jvinsights.client.Client;
import ly.cel.jvinsights.EventManager;

public class MyClass {
    public void main(String[] args) {
        // configuration values as per New Relic Insights
        String accountId = 'YOUR ACCOUNT ID';
        String insertKey = 'YOUR INSERT KEY';
        String queryKey  = 'YOUR QUERY KEY';

        // initialize core classes
        Client client = new Client(accountId, insertKey, queryKey);
        EventManager eventManager = new EventManager(client, "appName");

        // create your event
        MyEvent event = new MyEvent('some attribute');

        // submit event to Insights
        eventManager.persist(event);
        eventManager.flush();
    }
}
```

## To do

1. Add unit test for NRQL queries
2. Improve documentation for NRQL queries


## License

The MIT License (MIT). Please see COPYING for more information.
