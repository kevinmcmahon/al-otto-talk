
# _Event Aggregator Pattern featuring Otto_
---

![fit](/Users/kevin/Projects/android/al-otto-ex/slides/al-otto-example-app.png)

--- 

# _Code_

---

![fit](/Users/kevin/Projects/android/al-otto-ex/slides/tightcoupling.png)

---

# Problems

- Tight coupling  

- Testability

- Refactoring

- ~~Open/Closed Principle~~

---

> Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.
-- Bertrand Meyer

---

##[fit] What about listeners?
---

# Listener Drawbacks

- 1:1 relationship between class and response
- Conflicting interface methods
- Naming conventions (e.g. handleChangeEvent vs. recordChangeInJournal).
- Each event usually has its own interface.

^ Event handlers usually name after event rather than purpose. Events usually have own interface w/o common parent

---



---
## Terminology

- Event

- Subscribing

- Listener

- Handler method

- Posting an event

---

## Adding Otto
```ruby



dependencies {
    compile 'com.squareup:otto:1.3.+'
 }
 
 
```

---
## Introducing the Bus

```java
public final class BusProvider {
  private static final Bus BUS = new Bus();

  public static Bus getInstance() {
    return BUS;
  }

  private BusProvider() {
    // No instances.
  }
}
```

---

## Registration
```java
  @Override protected void onResume() {
    super.onResume();

    // Register ourselves so that we can provide the initial value.
    BusProvider.getInstance().register(this);
  }

  @Override protected void onPause() {
    super.onPause();

    // Always unregister when an object no longer should be on the bus.
    BusProvider.getInstance().unregister(this);
  }
```

---

## Publish Events

### Method
```java
      public void post(Object event) { /*...*/ }
```

### Example

```java
    BusProducer.getInstance().post(new LocationClearEvent());
```

---

## Create Events

Event objects are just POJOs that represent the types of events that occur in the app.

```java
    public class LocationClearEvent { /*...*/ }
    
    public class LocationChangedEvent { /*...*/ }    
```

---

## Subscribe to Events

1. @Subscribe annotation

1. Method parameter of the event being subscribed to

--- 

```java




  
  @Subscribe public void onLocationChanged(LocationChangedEvent event) {
   // Action goes here
  }

  @Subscribe public void onLocationCleared(LocationClearEvent event) {
   // Action goes here
  }
  
```
---
```java
public class AndroidBus extends Bus {
    private final Handler mainThread = new Handler(Looper.getMainLooper());
 
    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    AndroidBus.super.post(event);
                }
            });
        }
    }
}
```

--- 

# [fit] @klmcmahon

---
