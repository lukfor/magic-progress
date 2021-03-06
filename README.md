# magic-progress

![build](https://github.com/lukfor/magic-progress/workflows/build/badge.svg)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.lukfor/magic-progress.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.lukfor%22%20AND%20a:%22magic-progress%22)

> A java library for simple progress monitoring using animated progress bars on the console.

![Showcase](examples/showcase.gif)

## Installation

Add the following dependency to your Maven Project:

    <dependency>
      <groupId>com.github.lukfor</groupId>
      <artifactId>magic-progress</artifactId>
      <version>0.3.2</version>
    </dependency>

0r include it as a dependency in your Gradle project:

    compile 'com.github.lukfor:magic-progress:0.3.2'


## Run your first Task

First, implement the `ITaskRunnable` interface and put your code into the `run` method. You can use the provided `ITaskMonitor` to report progress:

-   Use the `begin(..)` method to start a new task and to set the total amoumt of work

-   Use the `worked(..)` method to notify the monitor about the progress.

-   Finally, use `done()`

Example:

```java
ITaskRunnable task = new ITaskRunnable() {

	@Override
	public void run(ITaskMonitor monitor) {

	monitor.begin("Task Name", 100);

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

Next, use the `TaskService` API to execute the task:

```java
TaskService.run(task);
```

Relax and enjoy the beautiful ANSI animation:

![Example](examples/example.gif)

See [Example.java](https://github.com/lukfor/magic-progress/tree/master/examples/Example.java).

## Take Advantage of Built-In Tasks

`magic-progress` provides several built-in tasks to process files or collections in an easy and reusable way. The following examples demonstrate some of the most common use cases.

### Iterators and Collections

Use the class `AbstractCollectionTask` to process collections or iterators. No manual progress notification is needed since everything is handled automatically by `AbstractCollectionTask`.

```java
static class SumTask extends AbstractCollectionTask<Integer> {

		private int sum = 0;

		public SumTask(Vector<Integer> list) {
			super(list);
		}

		@Override
		public void process(Iterator<Integer> iterator) {
			while (iterator.hasNext()) {
				sum += iterator.next();
			}
		}

		public int getSum() {
			return sum;
		}

}
```

Create a new instance of the task an execute it using the `TaskService` API:

```java
Vector<Integer> list = new Vector<Integer>();
for (int i = 0; i < 10000000; i++) {
	list.add(5);
}

SumTask task = new SumTask(list);
TaskService.run(task);

System.out.println("Sum: " + task.getSum());
```

### Files and Downloads

```java
String url = "http://speedtest.tele2.net/1GB.zip";
String file = "download.zip";

DownloadTask task = new DownloadTask(url, file);

TaskService.monitor(Components.DOWNLOAD).run(task);
```

## Execute Multiple Tasks in Parallel

```java
TaskService.setThreads(3);
TaskService.setFailureStrategy(TaskFailureStrategy.CANCEL_TASKS);

ITaskRunnable task1 = createTask("task1", 500, 100);
ITaskRunnable task2 = createTask("task2", 600, 200);
ITaskRunnable task3 = createTask("task3", 800, -1);

TaskService.monitor(SPINNER, TASK_NAME,  DEFAULT).run(task1, task2, task3);
```

TODO: submit at once, multiple worker threads, how to implement task cancellation, task failure strategies

## Customizing the Appearance of the Progress Bar

Sometimes it can be necessary to customize the progress bar appearance. For example, you want to change labels/units or want adapt the style or layout of the bar it self. To enable full customization, `magic-progress` provides the possibility to combine different components like labels and bars to build complex and fancy animations.

### Labels

### Progress Bars and Spinners

### Creating a Custom Component

Components themselves can be created by implementing the `IProgressIndicator` interface.

## ANSI support

Out of the box, all progress bars are using ANSI colors to display beautiful and animated bars and print their content to stdout. However, if you prefer to print the bar to stderr or to disable colors, you can change the default behavior of `TaskService` with the following flags:

```java
TaskService.setAnimated(false);
TaskService.setAnsiColors(true);
TaskService.setTarget(System.err);
```

Tip: Use this static methods and implement a `--no-ansi` flag and let the user decide to use it.

## More examples

`magic-progress` can easily be combined with [jbang](https://jbang.dev/) and [picocli](https://picocli.info/). There are several [examples](https://github.com/lukfor/magic-progress/tree/master/examples) available to get an overview about its key features and usage.

## License

`magic-progress` is MIT Licensed.
