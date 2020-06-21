# magic-progress

![build](https://github.com/lukfor/magic-progress/workflows/build/badge.svg)

> A framework for simple progress monitoring on the console.

## Getting started

### Dependency

Add the following repository to your pom.xml or gradle file:

```
<repository>
  <id>bintray-lukfor-maven</id>
  <name>bintray</name>
  <url>https://dl.bintray.com/lukfor/maven</url>
</repository>
```

Add the following dependency:

```
<dependency>
  <groupId>lukfor</groupId>
  <artifactId>magic-progress</artifactId>
  <version>0.1.0</version>
</dependency>
```

### InteractiveProgressBar

````java
ITaskWithProgress task = new ITaskWithProgress() {
  
  @Override
  public void run(IProgressMonitor monitor) {
    monitor.beginTask("Downloading Data......", 800);
    for (int i = 0; i < 800; i++) {
      monitor.worked(1);
    }
    monitor.done();
  }
     
};

ProgressMonitor.run(task);
````
