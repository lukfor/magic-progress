# magic-progress

![build](https://github.com/lukfor/magic-progress/workflows/build/badge.svg)

> A java library for simple progress monitoring using animated progress bars on the console.

![Showcase](examples/showcase.gif)

## Installation


Add the following repository to your `pom.xml` or `gradle` file:

```
<repository>
  <id>bintray-lukfor-maven</id>
  <name>bintray</name>
  <url>https://dl.bintray.com/lukfor/maven</url>
</repository>
```

Include the following dependency:

```
<dependency>
  <groupId>lukfor</groupId>
  <artifactId>magic-progress</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Usage

First, implement the `ITaskRunnable` interface and put your long-running logic into the `run` method. You can use the provided `ITaskMonitor` to report progress:

- Use the `beginTask(..)` method to start a new task and to set the total amoumt of work

- Use the `worked(..)` to notify the monitor about the progress.

- Finally, use `done()`

Example:

```java
ITaskRunnable runnable = new ITaskRunnable() {

	@Override
	public void run(ITaskMonitor monitor) {
		monitor.beginTask("My heavy task", 100);
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

Next, execute your logic using the `TaskService` class:

```java
TaskService.run(runnable);
```

Relax and enjoy the beautiful ANSI animation:

![Example](examples/example.gif)

See [Example.java(https://github.com/lukfor/magic-progress/tree/master/examples/Example.java).

## More examples

`magic-progress` can easily be combined with [jbang](https://jbang.dev/) and [picocli](https://picocli.info/). There are several [examples](https://github.com/lukfor/magic-progress/tree/master/examples) available to get an overview about its key features and usage.

## ANSI support

Out of the box, all progress bars are using ANSI colors to display beautiful and animated bars and print their content to stdout. However, if you prefer to print the bar to stderr or to disable colors, you can change the default behavior of `TaskService` with the following flags:

```java
TaskService.setAnsiSupport(false);
TaskService.setTarget(System.err);
```

Tip: Use this static method and implement a `--no-ansi` flag and let the user decide to use it.

## Build-in taks

`magic-progress` provides several build-in tasks to process files or collections.

### Collections



### Download

```java
String url = "http://speedtest.tele2.net/1GB.zip";
String file = "download.zip";

DownloadTask task = new DownloadTask(url, file);

TakService.run(task, ProgressBarBuilder.DOWNLOAD);
```

### Files

### Streams

### Integrate existing libraries



## Customization

Sometimes it can be necessary to customize the progress bar appearance. For example, you want to change labels/units or want adapt the style or layout of the bar it self. To enable full customization, `magic-progress` provides the class `ProgressBarBuilder` that enables to combine different components like labels and bars to build complex and fancy animations.

### Combining different components

### Labels

### Bars

### Spinners

### Custom Components

Components themselves can be created by implementing the `IProgressContentProvider`.

## License

`magic-progress` is MIT Licensed.
