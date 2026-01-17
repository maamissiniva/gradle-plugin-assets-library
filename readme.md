# Assets plugin

I could not get transitive resolution of dependencies with an "assets" classifier
so the simple solution is to use a standard jar.

This is, at the moment, nothing more than a java library with resources from
the `src/main/assets` directory.

## Gradle

```
plugins {
    id("io.github.maamissiniva.assets-library") version "0.1.1"
}
```
