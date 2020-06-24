# magic-progress

![build](https://github.com/lukfor/magic-progress/workflows/build/badge.svg)

> A framework for simple progress monitoring on the console.

## Getting started


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

Create a task and report progress:

```java
ITaskWithProgress task = new ITaskWithProgress() {

	@Override
	public void run(IProgressBar monitor) {
		monitor.beginTask("Downloading Data......", 100);
		for (int i = 0; i < 100; i++) {
			monitor.worked(1);
			try {
				Thread.sleep(10);
			} catch (Exception e) {}
		}

		monitor.done();

	}
};
```

Start your task using the `ProgressMonitor` class:

```java
ProgressMonitor.run(task3, ProgressBarBuilder.MINIMAL);
```

Relax and enjoy the beatiful ANSI animiation :) 


## Examples
 
`magic-progress` provides several build-in tasks and layouts for common tasks.

### File download

```java
String url = "http://speedtest.tele2.net/1GB.zip";
String file = "test.zip";

DownloadTask task = new DownloadTask(url, file);

ProgressMonitor.run(task, ProgressBarBuilder.DOWNLOAD);
```
