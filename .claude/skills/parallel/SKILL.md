---
name: parallelization
description: Conventions for using parallelization in all tests using Jupiter
---

# Add a JUnit properties file to all modules to make sure parallelization is enabled

Filename should be: `junit-platform.properties` and it should be placed in `src/test/resources` of each module.

Its content should be:

```properties
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.config.fixed.parallelism = 4
junit.jupiter.execution.parallel.config.dynamic.factor = 4.0
junit.jupiter.execution.parallel.mode.default = concurrent
junit.jupiter.execution.parallel.mode.classes.default = concurrent
```

Only add this file, and if the file doesn't exist. Do not override existing files.

Check if tests run successfully for every module where you add this file. If it fails, then remove the file.
