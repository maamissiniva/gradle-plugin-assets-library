

plugins {
    id("eclipse")
    id("com.gradle.plugin-publish") version "2.0.0"
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

eclipse.project.name = "_github_maamissiniva_gradle-plugin-assets-library"

group   = "io.github.maamissiniva"
version = "0.1.1"

gradlePlugin {
    website = "https://github.com/maamissiniva/gradle-plugin-assets-library"
    vcsUrl  = "https://github.com/maamissiniva/gradle-plugin-assets-library.git"
    plugins {
        register("assets-library-plugin") {
            id          = "io.github.maamissiniva.assets-library"
	        displayName = "Creates an assets jar"
	        description = "Creates an assets jar"
	        tags.addAll("assets") 
            implementationClass = "maamissiniva.gradle.plugin.assets.AssetsLibraryPlugin"
        }
    }
}
